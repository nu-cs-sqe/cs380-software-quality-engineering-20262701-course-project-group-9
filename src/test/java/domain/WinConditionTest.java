package domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class WinConditionTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5})
    void identifiesOnlyASingleSurvivor(int alive) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Player player = new Player(i, "Player " + i);
            if (i >= alive) {
                player.eliminate();
            }
            players.add(player);
        }
        WinCondition condition = new WinCondition(players);
        assertEquals(alive == 1, condition.isGameOver());
        assertEquals(alive == 1, condition.getWinner().isPresent());
        if (alive == 1) {
            assertSame(players.get(0), condition.getWinner().orElseThrow());
        }
    }

    @Test
    void observesEliminationAfterConstructionAndFindsLastPlayer() {
        Player first = new Player(0, "First");
        Player last = new Player(1, "Last");
        List<Player> source = new ArrayList<>(List.of(first, last));
        WinCondition condition = new WinCondition(source);
        source.clear();
        assertFalse(condition.isGameOver());
        first.eliminate();
        assertTrue(condition.isGameOver());
        assertSame(last, condition.getWinner().orElseThrow());
    }

    @Test
    void emptyListHasNoWinner() {
        WinCondition condition = new WinCondition(List.of());
        assertFalse(condition.isGameOver());
        assertTrue(condition.getWinner().isEmpty());
    }
}
