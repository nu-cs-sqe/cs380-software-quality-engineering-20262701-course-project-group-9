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
}
