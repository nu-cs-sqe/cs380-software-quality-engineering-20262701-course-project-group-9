
package domain;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayerNamesTest {

    @Test
    void validNamesShouldBeStored() {
        PlayerNames names = new PlayerNames(2,
                List.of("Alice", "Bob"));

        assertEquals(List.of("Alice", "Bob"),
                names.getNames());
    }

    @Test
    void namesShouldBeTrimmed() {
        PlayerNames names = new PlayerNames(2,
                List.of(" Alice ", " Bob "));

        assertEquals(List.of("Alice", "Bob"),
                names.getNames());
    }

    @Test
    void emptyNameShouldUseDefault() {
        PlayerNames names = new PlayerNames(2,
                List.of("", "Bob"));

        assertEquals(List.of("Player 1", "Bob"),
                names.getNames());
    }

    @Test
    void whitespaceNameShouldUseDefault() {
        PlayerNames names = new PlayerNames(2,
                List.of("   ", "Bob"));

        assertEquals(List.of("Player 1", "Bob"),
                names.getNames());
    }

    @Test
    void fiveNamesShouldBeAccepted() {
        PlayerNames names = new PlayerNames(5,
                List.of("A", "B", "C", "D", "E"));

        assertEquals(5, names.getNames().size());
    }

    @Test
    void tooFewNamesShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PlayerNames(3,
                        List.of("Alice", "Bob")));
    }

    @Test
    void tooManyNamesShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new PlayerNames(2,
                        List.of("Alice", "Bob", "Charlie")));
    }
}
