package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StandardDeckTest {
    @Test
    void containsExactOriginalEditionCards() {
        Map<String, Integer> expected = Map.ofEntries(
            Map.entry("Exploding Kitten", 4), Map.entry("Defuse", 6),
            Map.entry("Attack", 4), Map.entry("Favor", 4), Map.entry("Shuffle", 4),
            Map.entry("Skip", 4), Map.entry("Nope", 5), Map.entry("See the Future", 5),
            Map.entry("Tacocat", 4), Map.entry("Cattermelon", 4), Map.entry("Hairy Potato Cat", 4),
            Map.entry("Beard Cat", 4), Map.entry("Rainbow-Ralphing Cat", 4)
        );
        List<String> cards = StandardDeck.createCards();
        Map<String, Integer> actual = new HashMap<>();
        for (String card : cards) {
            actual.merge(card, 1, Integer::sum);
        }
        assertEquals(56, cards.size());
        assertEquals(expected, actual);
    }

    @Test
    void eachDeckIsIndependentAndMutable() {
        List<String> first = StandardDeck.createCards();
        List<String> second = StandardDeck.createCards();
        first.clear();
        first.add("custom card");
        assertEquals(56, second.size());
        assertFalse(second.contains("custom card"));
        assertEquals(second, StandardDeck.createCards());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void startsGameWithRealDeck(int count) {
        for (int start : new int[] {0, count - 1}) {
            GameSetup game = new GameSetup(count, Collections.nCopies(count, "Player"), start);
            assertEquals(count, game.getPlayers().size());
            Map<String, Integer> combined = new HashMap<>();
            for (Player player : game.getPlayers()) {
                assertEquals(8, player.getHand().size());
                assertEquals(1, Collections.frequency(player.getHand(), "Defuse"));
                assertFalse(player.getHand().contains("Exploding Kitten"));
                for (String card : player.getHand()) {
                    combined.merge(card, 1, Integer::sum);
                }
            }
            for (String card : game.getDrawPile()) {
                combined.merge(card, 1, Integer::sum);
            }
            Map<String, Integer> expected = new HashMap<>();
            for (String card : StandardDeck.createCards()) {
                expected.merge(card, 1, Integer::sum);
            }
            expected.put("Exploding Kitten", count - 1);
            expected.put("Soundcheck", 1);
            expected.put("Second Chance", 1);
            assertEquals(expected, combined);
            assertEquals(count - 1, Collections.frequency(game.getDrawPile(), "Exploding Kitten"));
            assertEquals(53 - 7 * count, game.getDrawPile().size());
            assertEquals(start, game.getCurrentPlayerIndex());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    void rejectsInvalidCount(int count) {
        assertThrows(IllegalArgumentException.class,
            () -> new GameSetup(count, Collections.nCopies(count, "Player"), 0));
    }

    @Test
    void rejectsInvalidStartAndNames() {
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, List.of("A", "B"), -1));
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, List.of("A", "B"), 2));
        assertThrows(IllegalArgumentException.class, () -> new GameSetup(2, List.of("A"), 0));
    }
}
