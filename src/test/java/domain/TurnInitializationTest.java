package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class TurnInitializationTest {
    @ParameterizedTest
    @MethodSource("validOrders")
    void initializesExactOrderAndCurrentPlayer(int count, int start, List<Integer> expected) {
        TurnInitialization turns = new TurnInitialization(count, start);
        assertEquals(expected, turns.getTurnOrder());
        assertEquals(start, turns.getCurrentPlayerIndex());
    }

    private static Stream<Arguments> validOrders() {
        return Stream.of(
                Arguments.of(2, 0, List.of(0, 1)),
                Arguments.of(3, 0, List.of(0, 1, 2)),
                Arguments.of(4, 0, List.of(0, 1, 2, 3)),
                Arguments.of(5, 0, List.of(0, 1, 2, 3, 4)),
                Arguments.of(2, 1, List.of(1, 0)),
                Arguments.of(3, 2, List.of(2, 0, 1)),
                Arguments.of(4, 3, List.of(3, 0, 1, 2)),
                Arguments.of(5, 4, List.of(4, 0, 1, 2, 3)),
                Arguments.of(5, 2, List.of(2, 3, 4, 0, 1)));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 6, Integer.MIN_VALUE, Integer.MAX_VALUE})
    void rejectsInvalidPlayerCounts(int count) {
        assertThrows(IllegalArgumentException.class, () -> new TurnInitialization(count, 0));
    }

    @ParameterizedTest
    @CsvSource({"2, -1", "2, 2", "5, -1", "5, 5", "5, -2147483648", "5, 2147483647"})
    void rejectsInvalidStartingPlayer(int count, int start) {
        assertThrows(IllegalArgumentException.class, () -> new TurnInitialization(count, start));
    }

    @Test
    void returnedOrderCannotChangeStoredOrderOrCurrentPlayer() {
        TurnInitialization turns = new TurnInitialization(3, 1);
        List<Integer> copy = turns.getTurnOrder();
        copy.set(0, 99);
        copy.clear();
        assertEquals(List.of(1, 2, 0), turns.getTurnOrder());
        assertEquals(1, turns.getCurrentPlayerIndex());
    }

    @Test
    void separateGamesKeepIndependentOrders() {
        TurnInitialization first = new TurnInitialization(2, 0);
        TurnInitialization second = new TurnInitialization(5, 4);
        assertEquals(List.of(0, 1), first.getTurnOrder());
        assertEquals(0, first.getCurrentPlayerIndex());
        assertEquals(List.of(4, 0, 1, 2, 3), second.getTurnOrder());
        assertEquals(4, second.getCurrentPlayerIndex());
    }
}
