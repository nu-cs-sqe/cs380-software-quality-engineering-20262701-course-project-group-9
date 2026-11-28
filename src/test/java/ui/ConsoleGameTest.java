package ui;

import domain.Game;
import domain.Player;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(5)
class ConsoleGameTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private BufferedReader input;

    private ConsoleGame console(String script) {
        input = new BufferedReader(new StringReader(script));
        return new ConsoleGame(input, new PrintStream(output, true, StandardCharsets.UTF_8));
    }

    private String transcript() {
        return output.toString(StandardCharsets.UTF_8);
    }

    private Game game(List<String> firstHand, String... deck) {
        return new Game(List.of(new Player(0, "A", firstHand), new Player(1, "B")), List.of(deck), 0);
    }
    @Test
    void seeTheFutureClearsScreenAfterEnter() throws IOException {
    Game game = game(
        List.of("See the Future"),
        "Attack", "Shuffle", "Favor", "Skip"
    );

    console("future\n\n").play(game);

    String text = transcript();
    String clear = "\033[2J\033[H";

    assertTrue(text.contains(
        "Future cards: [Attack, Shuffle, Favor]"
    ));

    assertTrue(text.contains(
        "Press Enter to hide the cards."
    ));

    int revealPosition = text.indexOf("Future cards:");
    int clearPosition = text.indexOf(clear, revealPosition);

    assertTrue(clearPosition > revealPosition);

    assertEquals(0, game.getCurrentPlayer().getId());
    assertEquals(4, game.getDrawPileSize());
    assertTrue(game.getCurrentPlayer().getHand().isEmpty());
    }

    @Test
    void playingFavorLetsTheSelectedOpponentChooseAndTheActorContinue() throws IOException {
        Game game = new Game(List.of(new Player(10, "A", List.of("Favor")),
                new Player(20, "Twin", List.of("Secret B")),
                new Player(30, "Twin", List.of("Skip", "Defuse"))), List.of("Drawn"), 0);
        console("favor\n0\n2\n\n0\n2\ndraw\n").play(game);
        String text = transcript();
        assertTrue(text.contains("1. Twin (seat 2)"));
        assertTrue(text.contains("2. Twin (seat 3)"));
        int handoff = text.indexOf("Pass the keyboard to Twin (seat 3)");
        assertTrue(handoff >= 0);
        assertTrue(text.indexOf("1. Skip") > handoff);
        assertTrue(text.indexOf("2. Defuse") > handoff);
        assertTrue(text.indexOf("Pass the keyboard back to A") > text.indexOf("2. Defuse"));
        assertTrue(text.contains("Enter a number from 1 to 2"));
        assertFalse(text.contains("Secret B"));
        assertEquals(List.of("Defuse", "Drawn"), game.getPlayers().get(0).getHand());
        assertEquals(List.of("Secret B"), game.getPlayers().get(1).getHand());
        assertEquals(List.of("Skip"), game.getPlayers().get(2).getHand());
    }

    @ParameterizedTest
    @ValueSource(strings = {"favor\n", "favor\n1\n", "favor\n1\n\n", "favor\n1\n\nx\n"})
    void interruptedFavorPreservesBothHands(String script) throws IOException {
        Game game = new Game(List.of(new Player(0, "A", List.of("Favor")),
                new Player(1, "B", List.of("Defuse"))), List.of("Skip"), 0);
        console(script).play(game);
        assertTrue(transcript().contains("1. B (seat 2)"));
        assertEquals(script.contains("\n\n"), transcript().contains("1. Defuse"));
        assertFalse(transcript().contains("Pass the keyboard back"));
        assertEquals(List.of("Favor"), game.getCurrentPlayer().getHand());
        assertEquals(List.of("Defuse"), game.getPlayers().get(1).getHand());
    }

    @Test
    void unheldFavorReportsTheMissingCard() throws IOException {
        console("favor\n").play(game(List.of("Skip"), "Drawn"));
        assertTrue(transcript().contains("No Favor card"));
    }

    @Test
    void unavailableFavorTargetLeavesTheCardAndTurnAvailable() throws IOException {
        Game game = game(List.of("Favor"), "Drawn");
        console("favor\ndraw\n").play(game);
        assertTrue(transcript().contains("No opponent can give a card."));
        assertFalse(transcript().contains("Pass the keyboard"));
        assertEquals(List.of("Favor", "Drawn"), game.getPlayers().get(0).getHand());
    }

    @Test
    void privateFavorChoicesAreClearedAtEachKeyboardHandoff() throws IOException {
        Game game = new Game(List.of(new Player(0, "A", List.of("Favor")),
                new Player(1, "B", List.of("Gift"))), List.of("Drawn"), 0);
        console("favor\n1\n1\n\n1\n").play(game);
        String text = transcript();
        String clear = "\033[2J\033[H";
        assertTrue(text.contains(clear + "Pass the keyboard to B"));
        int retry = text.indexOf("Press Enter without typing a choice.");
        assertTrue(retry >= 0);
        assertTrue(text.indexOf("1. Gift") > retry);
        assertTrue(text.contains(clear + "Pass the keyboard back to A"));
        assertEquals(List.of("Gift"), game.getCurrentPlayer().getHand());
    }

    @Test
    void soundcheckAnnouncesOnlyTheCountAndLetsTheSamePlayerDraw() throws IOException {
        Game game = game(List.of("Soundcheck"), "Secret A", "Exploding Kitten", "Secret B");
        console(" SOUNDCHECK \ndraw\n").play(game);
        String text = transcript();
        String[] lines = text.split("\\R");
        for (String command : List.of("draw", "skip", "future", "shuffle", "favor", "soundcheck", "hand")) {
            assertTrue(lines[0].contains(command), command);
        }
        assertEquals("A played Soundcheck. Exploding Kittens detected: 1", lines[1]);
        assertEquals(lines[0], lines[2]);
        assertFalse(text.contains("Secret A"));
        assertFalse(text.contains("Secret B"));
        assertTrue(text.contains("A drew a card."));
        assertEquals(List.of("Secret A"), game.getPlayers().get(0).getHand());
        assertEquals("B", game.getCurrentPlayer().getName());
    }

    @Test
    void missingSoundcheckAllowsAnotherCommand() throws IOException {
        Game game = game(List.of("Favor"), "Skip");
        console("soundcheck\ndraw\n").play(game);
        assertTrue(transcript().contains("No Soundcheck card"));
        assertEquals(List.of("Favor", "Skip"), game.getPlayers().get(0).getHand());
    }

    @Test
    void skipThenKittenAnnouncesTheWinnerAndStopsReading() throws IOException {
        Game game = game(List.of("Skip"), "Exploding Kitten");
        console("skip\ndraw\nunused\n").play(game);
        String text = transcript();
        assertTrue(text.contains("A's turn"));
        assertTrue(text.contains("A played Skip."));
        assertTrue(text.indexOf("B's turn") > text.indexOf("A's turn"));
        assertTrue(text.contains("B was eliminated"));
        assertTrue(text.indexOf("A wins!") > text.indexOf("B's turn"));
        assertEquals(text.indexOf("A wins!"), text.lastIndexOf("A wins!"));
        assertTrue(game.getPlayers().get(0).getHand().isEmpty());
        assertEquals("A", game.getWinner().orElseThrow().getName());
        assertEquals("unused", input.readLine());
    }

    @Test
    void invalidCommandsAndHandLeaveTheTurnAvailableForDraw() throws IOException {
        Game game = new Game(List.of(new Player(0, "A", List.of("Favor")),
                new Player(1, "B", List.of("Nope"))), List.of("Shuffle"), 0);
        console("bogus\nskip\nhand\n DRAW \n").play(game);
        assertTrue(transcript().contains("Unknown command"));
        assertTrue(transcript().contains("No Skip card"));
        assertTrue(transcript().contains("Favor"));
        assertTrue(transcript().contains("A drew a card."));
        assertFalse(transcript().contains("was eliminated"));
        assertFalse(transcript().contains("Nope"));
        assertEquals(List.of("Favor", "Shuffle"), game.getPlayers().get(0).getHand());
        assertEquals("B", game.getCurrentPlayer().getName());
        assertFalse(transcript().contains("wins!"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void defuseAcceptsEitherEndAfterRejectingInvalidPositions(int position) throws IOException {
        Game game = game(List.of("Defuse"), "Exploding Kitten", "Favor");
        console("draw\nwhat\n-1\n2\n" + position + "\ndraw\n").play(game);
        assertTrue(transcript().contains("Defuse"));
        assertTrue(transcript().contains("Enter a number from 0 to 1"));
        assertFalse(game.needsReinsertion());
        assertTrue(game.getPlayers().get(0).getHand().isEmpty());
        assertEquals(position == 0, game.getWinner().isPresent());
        if (position == 1) {
            assertEquals(List.of("Favor"), game.getPlayers().get(1).getHand());
        }
    }

    @Test
    void endOfInputDuringDefusePreservesThePendingDecision() throws IOException {
        Game game = game(List.of("Defuse"), "Exploding Kitten");
        console("draw\n").play(game);
        assertTrue(game.needsReinsertion());
        assertEquals(List.of("Defuse"), game.getCurrentPlayer().getHand());
        assertFalse(transcript().contains("wins!"));
    }

    @Test
    void startsTwoPlayerGameAfterBadCountsAndSeats() throws IOException {
        console("one\n1\n6\n2\n\nBee\n0\n3\n1\n").start();
        assertTrue(transcript().contains("Enter a number from 2 to 5"));
        assertTrue(transcript().contains("Enter a number from 1 to 2"));
        assertTrue(transcript().contains("Player 1's turn"));
    }

    @Test
    void startsAtTheLastOfFiveSeats() throws IOException {
        console("5\nA\nB\nC\nD\nJosé\n5\n").start();
        assertTrue(transcript().contains("José's turn"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "2\nA\nB\n"})
    void endOfInputAtNumericSetupPromptsStopsBeforeFirstTurn(String script) throws IOException {
        console(script).start();
        assertTrue(transcript().contains("How many players? (2-5)"));
        if (!script.isEmpty()) {
            assertTrue(transcript().contains("Which player goes first?"));
        }
        assertFalse(transcript().contains("'s turn"));
    }

    @Test
    void endOfInputDuringNamesDoesNotStartAGame() throws IOException {
        console("2\nA\n").start();
        assertTrue(transcript().contains("Name for player 2"));
        assertFalse(transcript().contains("'s turn"));
    }

    @Test
    void secondChanceWorksThroughConsole() throws IOException {
        Game game = game(
                List.of("Second Chance"),
                "Attack", "Skip"
        );

        console("second\ndraw\n").play(game);

        String text = transcript();

        assertTrue(text.contains("A played Second Chance."));
        assertTrue(text.contains("A drew a card."));

        // The original top card moves to the bottom.
        // Alice draws Skip instead of Attack.
        assertEquals(
                List.of("Skip"),
                game.getPlayers().get(0).getHand()
        );

        // One card remains and the turn advances after drawing.
        assertEquals(1, game.getDrawPileSize());
        assertEquals(1, game.getCurrentPlayer().getId());
    }

    @Test
    void missingSecondChanceAllowsRetry() throws IOException {
        Game game = game(
                List.of("Skip"),
                "Attack"
        );

        console("second\ndraw\n").play(game);

        String text = transcript();

        // Missing card should produce an error.
        assertTrue(text.contains("No Second Chance card"));

        // Player may draw normally after the invalid action.
        assertTrue(text.contains("A drew a card."));

        assertEquals(
                List.of("Skip", "Attack"),
                game.getPlayers().get(0).getHand()
        );

        assertEquals(1, game.getCurrentPlayer().getId());
    }

}
