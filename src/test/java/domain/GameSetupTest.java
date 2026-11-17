package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameSetupTest {
    private List<String> deck() {
        List<String> cards = new ArrayList<>(Collections.nCopies(40, "Skip"));
        cards.addAll(Collections.nCopies(6, "Defuse"));
        cards.addAll(Collections.nCopies(4, "Exploding Kitten"));
        return cards;
    }

    private List<String> names(int count) {
        return new ArrayList<>(Collections.nCopies(count, " Alice "));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void initializesRealComponentsTogether(int count) {
        List<String> source = deck();
        GameSetup game = new GameSetup(count, names(count), source, count - 1);
        assertEquals(count, game.getPlayers().size());
        for (int i = 0; i < count; i++) {
            Player player = game.getPlayers().get(i);
            assertEquals(i, player.getId());
            assertEquals("Alice", player.getName());
            assertTrue(player.isAlive());
            assertEquals(8, player.getHand().size());
            assertEquals(1, Collections.frequency(player.getHand(), "Defuse"));
            assertEquals(7, Collections.frequency(player.getHand(), "Skip"));
            assertFalse(player.getHand().contains("Exploding Kitten"));
        }
        assertEquals(count - 1, Collections.frequency(game.getDrawPile(), "Exploding Kitten"));
        assertEquals(40 - 7 * count, Collections.frequency(game.getDrawPile(), "Skip"));
        assertEquals(6 - count, Collections.frequency(game.getDrawPile(), "Defuse"));
        assertEquals(45 + count, game.getDrawPile().size() + 8 * count);
        assertEquals(count - 1, game.getCurrentPlayerIndex());
        List<Integer> expected = new ArrayList<>();
        expected.add(count - 1);
        for (int i = 0; i < count - 1; i++) {
            expected.add(i);
        }
        assertEquals(expected, game.getTurnOrder());
        assertEquals(deck(), source);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    void rejectsInvalidCounts(int count) {
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(count, names(count), deck(), 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2})
    void rejectsInvalidStart(int start) {
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, names(2), deck(), start));
    }

    @Test
    void rejectsMismatchedNames() {
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, names(1), deck(), 0));
    }

    @Test
    void rejectsNullEmptyAndInsufficientDecks() {
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, names(2), null, 0));
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, names(2), List.of(), 0));
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, names(2), List.of("Skip"), 0));
    }

    @Test
    void protectsStateAndUsesDefaultName() {
        List<String> inputNames = new ArrayList<>(List.of("", " Bob "));
        List<String> inputDeck = deck();
        GameSetup game = new GameSetup(2, inputNames, inputDeck, 0);
        inputNames.clear();
        inputDeck.clear();
        game.getPlayers().get(0).getHand().clear();
        game.getPlayers().clear();
        game.getDrawPile().clear();
        game.getTurnOrder().clear();
        assertEquals(2, game.getPlayers().size());
        assertEquals("Player 1", game.getPlayers().get(0).getName());
        assertEquals("Bob", game.getPlayers().get(1).getName());
        assertEquals(8, game.getPlayers().get(0).getHand().size());
        assertEquals(31, game.getDrawPile().size());
        assertEquals(List.of(0, 1), game.getTurnOrder());
        assertEquals(0, game.getCurrentPlayerIndex());
    }

    @Test
    void playerCopiesStartingHand() {
        List<String> cards = new ArrayList<>(Collections.nCopies(8, "Skip"));
        Player player = new Player(0, "Alice", cards);
        cards.clear();
        player.getHand().clear();
        assertEquals(8, player.getHand().size());
        assertTrue(new Player(1, "Bob", List.of()).getHand().isEmpty());
    }
}
