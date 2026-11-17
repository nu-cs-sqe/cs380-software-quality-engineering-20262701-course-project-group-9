
package domain;

import java.util.ArrayList;
import java.util.List;

public final class DeckSetup {

    private final List<String> cards;

    public DeckSetup(List<String> inputCards) {
        validateCards(inputCards);
        this.cards = new ArrayList<>(inputCards);
    }

    private void validateCards(List<String> inputCards) {
        if (inputCards == null) {
            throw new IllegalArgumentException(
                "Deck must not be null"
            );
        }

        for (String card : inputCards) {
            if (card == null || card.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Deck must not contain null or blank cards"
                );
            }
        }
    }

    public List<String> getCards() {
        return new ArrayList<>(cards);
    }
}
