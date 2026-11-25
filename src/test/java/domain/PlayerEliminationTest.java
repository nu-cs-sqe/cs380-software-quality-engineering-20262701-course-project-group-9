package domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerEliminationTest {
    @Test
    void eliminationIsPermanentAndDoesNotChangeIdentityOrHand() {
        Player player = new Player(2, "Isaiah", List.of("Skip"));
        assertTrue(player.isAlive());
        player.eliminate();
        assertFalse(player.isAlive());
        player.eliminate();
        assertFalse(player.isAlive());
        assertEquals(2, player.getId());
        assertEquals("Isaiah", player.getName());
        assertEquals(List.of("Skip"), player.getHand());
    }
}
