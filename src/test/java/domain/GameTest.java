package domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game(List<String> hand, List<String> deck, int start) {
        return new Game(List.of(new Player(0, "A", hand), new Player(1, "B")), deck, start);
    }

    @Test
    void ordinaryDrawAddsCardAndWrapsTurn() {
        Game game = game(List.of(), List.of("Favor", "Skip"), 1);
        game.draw();
        assertEquals(0, game.getCurrentPlayer().getId());
        assertEquals(List.of("Favor"), game.getPlayers().get(1).getHand());
        assertEquals(1, game.getDrawPileSize());
        game.draw();
        assertEquals(1, game.getCurrentPlayer().getId());
    }

    @Test
    void kittenEliminatesAndStopsAtWinner() {
        Game game = game(List.of(), List.of("Exploding Kitten"), 0);
        game.draw();
        assertFalse(game.getPlayers().get(0).isAlive());
        assertEquals(1, game.getWinner().orElseThrow().getId());
        assertThrows(IllegalStateException.class, game::draw);
        assertThrows(IllegalStateException.class, game::skip);
        assertThrows(IllegalStateException.class, () -> game.reinsertKitten(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void defuseReinsertsAtBothEnds(int position) {
        Game game = game(List.of("Defuse"), List.of("Exploding Kitten", "Skip"), 0);
        game.draw();
        assertTrue(game.needsReinsertion());
        assertThrows(IllegalStateException.class, game::draw);
        assertThrows(IllegalStateException.class, game::skip);
        assertThrows(IllegalArgumentException.class, () -> game.reinsertKitten(-1));
        assertThrows(IllegalArgumentException.class, () -> game.reinsertKitten(2));
        assertTrue(game.needsReinsertion());
        game.reinsertKitten(position);
        assertFalse(game.needsReinsertion());
        assertTrue(game.getPlayers().get(0).isAlive());
        assertTrue(game.getPlayers().get(0).getHand().isEmpty());
        assertEquals(2, game.getDrawPileSize());
        assertEquals(1, game.getCurrentPlayer().getId());
        game.draw();
        assertEquals(position == 0, game.getWinner().isPresent());
    }

    @Test
    void skipConsumesOneCardWithoutDrawing() {
        Game game = game(List.of("Skip", "Skip"), List.of("Favor"), 0);
        game.skip();
        assertEquals(List.of("Skip"), game.getPlayers().get(0).getHand());
        assertEquals(1, game.getDrawPileSize());
        assertEquals(1, game.getCurrentPlayer().getId());
        assertThrows(IllegalStateException.class, game::skip);
        assertEquals(1, game.getCurrentPlayer().getId());
    }

    @Test
    void skipsEliminatedPlayersAcrossRounds() {
        Game game = new Game(List.of(new Player(0, "A"), new Player(1, "B"), new Player(2, "C")),
                List.of("Exploding Kitten", "Skip", "Favor"), 0);
        game.draw();
        assertEquals(1, game.getCurrentPlayer().getId());
        assertTrue(game.getWinner().isEmpty());
        game.draw();
        assertEquals(2, game.getCurrentPlayer().getId());
        game.draw();
        assertEquals(1, game.getCurrentPlayer().getId());
    }

    @Test
    void rejectsEmptyDrawAndUnexpectedReinsertion() {
        Game game = game(List.of(), List.of(), 0);
        assertThrows(IllegalStateException.class, game::draw);
        assertThrows(IllegalStateException.class, () -> game.reinsertKitten(0));
        assertEquals(0, game.getCurrentPlayer().getId());
    }

    @Test
    void cannotSpendSkipWhileHoldingAKittenForReinsertion() {
        Game game = game(List.of("Defuse", "Skip"), List.of("Exploding Kitten"), 0);
        game.draw();
        assertThrows(IllegalStateException.class, game::skip);
        assertTrue(game.needsReinsertion());
        assertEquals(0, game.getCurrentPlayer().getId());
        assertEquals(List.of("Defuse", "Skip"), game.getCurrentPlayer().getHand());
    }

    @Test
    void rejectsAnEliminatedPlayerInTheStartingLineup() {
        Player eliminated = new Player(1, "B");
        eliminated.eliminate();
        List<Player> lineup = List.of(new Player(0, "A"), eliminated);
        assertThrows(IllegalArgumentException.class, () -> new Game(lineup, List.of("Skip"), 0));
    }

    @Test
    void connectsSetupAndProtectsOwnedPlayers() {
        GameSetup setup = new GameSetup(2, List.of("A", "B"), 1);
        Game game = new Game(setup);
        setup.getPlayers().get(1).eliminate();
        game.getCurrentPlayer().eliminate();
        game.getPlayers().get(0).eliminate();
        assertTrue(game.getCurrentPlayer().isAlive());
        assertTrue(game.getWinner().isEmpty());
        assertEquals(8, game.getCurrentPlayer().getHand().size());
        assertEquals(setup.getDrawPile().size(), game.getDrawPileSize());
    }
}
