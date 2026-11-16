
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DeckSetupTest {

    @Test
    void nullDeckShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DeckSetup(null));
    }

    @Test
    void emptyDeckShouldBeAccepted() {
        DeckSetup deck = new DeckSetup(Collections.emptyList());
        assertTrue(deck.getCards().isEmpty());
    }

    @Test
    void singleCardShouldBeAccepted() {
        DeckSetup deck = new DeckSetup(
            Arrays.asList("Skip")
        );
        assertEquals(1, deck.getCards().size());
    }

    @Test
    void twoCardsShouldBeAccepted() {
        DeckSetup deck = new DeckSetup(
            Arrays.asList("Skip", "Attack")
        );
        assertEquals(2, deck.getCards().size());
    }

    @Test
    void duplicateCardsShouldBePreserved() {
        DeckSetup deck = new DeckSetup(
            Arrays.asList("Skip", "Skip")
        );
        assertEquals(
            Arrays.asList("Skip", "Skip"),
            deck.getCards()
        );
    }

    @Test
    void nullCardShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DeckSetup(
                Arrays.asList("Skip", null)
            )
        );
    }

    @Test
    void emptyCardNameShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DeckSetup(
                Arrays.asList("Skip", "")
            )
        );
    }

    @Test
    void whitespaceOnlyCardNameShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> new DeckSetup(
                Arrays.asList("Skip", "   ")
            )
        );
    }

    @Test
    void cardOrderShouldBePreserved() {
        DeckSetup deck = new DeckSetup(
            Arrays.asList("Skip", "Attack", "Defuse")
        );

        assertEquals(
            Arrays.asList("Skip", "Attack", "Defuse"),
            deck.getCards()
        );
    }

    @Test
    void modifyingOriginalListShouldNotChangeDeck() {
        List<String> cards = new ArrayList<>(
            Arrays.asList("Skip", "Attack")
        );

        DeckSetup deck = new DeckSetup(cards);
        cards.add("Defuse");

        assertEquals(2, deck.getCards().size());
    }

    @Test
    void modifyingReturnedListShouldNotChangeDeck() {
        DeckSetup deck = new DeckSetup(
            Arrays.asList("Skip", "Attack")
        );

        List<String> returnedCards = deck.getCards();
        returnedCards.clear();

        assertEquals(2, deck.getCards().size());
    }
}
