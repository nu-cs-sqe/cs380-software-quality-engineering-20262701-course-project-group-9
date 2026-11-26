package domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class FavorTest {
    private Game game(List<String> first, List<String> second) {
        return new Game(List.of(new Player(10, "A", first), new Player(20, "B", second)), List.of("Drawn"), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    void transfersTheChosenCopyAndKeepsTheTurn(int card) {
        Game game = game(List.of("Favor", "Favor"), List.of("Skip", "Defuse", "Skip"));
        game.favor(1, card);
        assertEquals(List.of("Favor", "Skip"), game.getCurrentPlayer().getHand());
        List<String> remaining = card == 0 ? List.of("Defuse", "Skip") : List.of("Skip", "Defuse");
        assertEquals(remaining, game.getPlayers().get(1).getHand());
        assertEquals(1, game.getDrawPileSize());
        game.draw();
        assertEquals(List.of("Favor", "Skip", "Drawn"), game.getPlayers().get(0).getHand());
        assertEquals(20, game.getCurrentPlayer().getId());
    }

    @Test
    void aFavorMayBeGivenBackToThePlayerWhoAsked() {
        Game game = game(List.of("Favor"), List.of("Favor"));
        game.favor(1, 0);
        assertEquals(List.of("Favor"), game.getCurrentPlayer().getHand());
        assertTrue(game.getPlayers().get(1).getHand().isEmpty());
        assertTrue(game.favorTargets().isEmpty());
    }

    @Test
    void offersOnlyLivingOpponentsWithCardsBySeat() {
        Game game = new Game(List.of(new Player(10, "Same", List.of("Skip")),
                new Player(20, "Same", List.of("Favor")), new Player(30, "Empty"),
                new Player(40, "Same", List.of("Attack"))), List.of("Exploding Kitten", "Drawn"), 0);
        game.draw();
        assertEquals(List.of(3), game.favorTargets());
        assertThrows(IllegalArgumentException.class, () -> game.favor(0, 0));
        assertThrows(IllegalArgumentException.class, () -> game.favor(2, 0));
        assertEquals(List.of("Favor"), game.getCurrentPlayer().getHand());
        assertEquals(List.of("Skip"), game.getPlayers().get(0).getHand());
        game.favor(3, 0);
        assertEquals(List.of("Attack"), game.getCurrentPlayer().getHand());
        assertEquals(20, game.getCurrentPlayer().getId());
    }

    @Test
    void theFirstSeatCanBeChosenByALaterPlayer() {
        Game game = new Game(List.of(new Player(9, "A", List.of("Defuse")),
                new Player(8, "B", List.of("Favor"))), List.of("Drawn"), 1);
        assertEquals(List.of(0), game.favorTargets());
        game.favor(0, 0);
        assertEquals(List.of("Defuse"), game.getCurrentPlayer().getHand());
        assertTrue(game.getPlayers().get(0).getHand().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 2})
    void rejectsInvalidOrOwnSeatWithoutSpending(int seat) {
        Game game = game(List.of("Favor"), List.of("Skip"));
        assertThrows(IllegalArgumentException.class, () -> game.favor(seat, 0));
        assertEquals(List.of("Favor"), game.getCurrentPlayer().getHand());
        assertEquals(List.of("Skip"), game.getPlayers().get(1).getHand());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1})
    void rejectsAnInvalidGiftWithoutSpending(int card) {
        Game game = game(List.of("Favor"), List.of("Skip"));
        assertThrows(IllegalArgumentException.class, () -> game.favor(1, card));
        assertEquals(List.of("Favor"), game.getCurrentPlayer().getHand());
        assertEquals(List.of("Skip"), game.getPlayers().get(1).getHand());
    }

    @Test
    void requiresFavorInHand() {
        Game game = game(List.of("Skip"), List.of("Defuse"));
        assertThrows(IllegalStateException.class, () -> game.favor(1, 0));
        assertEquals(List.of("Defuse"), game.getPlayers().get(1).getHand());
        assertEquals(List.of("Skip"), game.getCurrentPlayer().getHand());
    }

    @Test
    void cannotPlayFavorWhileReinsertionIsPending() {
        Game game = new Game(List.of(new Player(0, "A", List.of("Favor", "Defuse")),
                new Player(1, "B", List.of("Skip"))), List.of("Exploding Kitten"), 0);
        game.draw();
        assertThrows(IllegalStateException.class, () -> game.favor(1, 0));
        assertTrue(game.needsReinsertion());
        assertEquals(List.of("Skip"), game.getPlayers().get(1).getHand());
    }

    @Test
    void cannotPlayAfterAWin() {
        Game game = new Game(List.of(new Player(0, "A", List.of("Skip")),
                new Player(1, "B", List.of("Favor"))), List.of("Exploding Kitten"), 0);
        game.draw();
        assertThrows(IllegalStateException.class, () -> game.favor(0, 0));
        assertEquals(List.of("Favor"), game.getWinner().orElseThrow().getHand());
    }
}
