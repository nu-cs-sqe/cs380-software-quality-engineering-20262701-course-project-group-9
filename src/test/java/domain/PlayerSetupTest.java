
package domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerSetupTest {

    @Test
    void onePlayerShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup(1));
    }

    @Test
    void twoPlayersShouldBeAccepted() {
        PlayerSetup setup = new PlayerSetup(2);
        assertEquals(2, setup.getPlayerCount());
    }

    @Test
    void fivePlayersShouldBeAccepted() {
        PlayerSetup setup = new PlayerSetup(5);
        assertEquals(5, setup.getPlayerCount());
    }

    @Test
    void sixPlayersShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup(6));
    }
    @Test
    void zeroPlayersShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup(0));
    }

    @Test
    void negativePlayersShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup(-1));
    }

    @Test
    void threePlayersShouldBeAccepted() {
        PlayerSetup setup = new PlayerSetup(3);
        assertEquals(3, setup.getPlayerCount());
    }

    @Test
    void fourPlayersShouldBeAccepted() {
        PlayerSetup setup = new PlayerSetup(4);
        assertEquals(4, setup.getPlayerCount());
    }
}
