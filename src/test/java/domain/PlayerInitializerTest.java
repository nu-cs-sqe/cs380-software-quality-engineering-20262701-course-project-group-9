
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerInitializerTest {

    @Test
    void twoPlayersShouldBeCreated() {
        PlayerInitializer initializer = new PlayerInitializer(
            2, Arrays.asList("Alice", "Bob")
        );

        assertEquals(2, initializer.getPlayers().size());
    }

    @Test
    void fivePlayersShouldBeCreated() {
        PlayerInitializer initializer = new PlayerInitializer(
            5, Arrays.asList("A", "B", "C", "D", "E")
        );

        assertEquals(5, initializer.getPlayers().size());
    }

    @Test
    void onePlayerShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new PlayerInitializer(1, Arrays.asList("Alice"))
        );
    }

    @Test
    void sixPlayersShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new PlayerInitializer(
                6, Arrays.asList("A", "B", "C", "D", "E", "F")
            )
        );
    }

    @Test
    void playerIdsShouldBeUnique() {
        PlayerInitializer initializer = new PlayerInitializer(
            2, Arrays.asList("Alice", "Bob")
        );

        List<Player> players = initializer.getPlayers();

        assertEquals(0, players.get(0).getId());
        assertEquals(1, players.get(1).getId());
    }

    @Test
    void playerNamesShouldPreserveOrder() {
        PlayerInitializer initializer = new PlayerInitializer(
            2, Arrays.asList("Alice", "Bob")
        );

        List<Player> players = initializer.getPlayers();

        assertEquals("Alice", players.get(0).getName());
        assertEquals("Bob", players.get(1).getName());
    }

    @Test
    void playersShouldStartAlive() {
        PlayerInitializer initializer = new PlayerInitializer(
            2, Arrays.asList("Alice", "Bob")
        );

        for (Player player : initializer.getPlayers()) {
            assertTrue(player.isAlive());
        }
    }

    @Test
    void playersShouldStartWithEmptyHands() {
        PlayerInitializer initializer = new PlayerInitializer(
            2, Arrays.asList("Alice", "Bob")
        );

        for (Player player : initializer.getPlayers()) {
            assertTrue(player.getHand().isEmpty());
        }
    }
}
