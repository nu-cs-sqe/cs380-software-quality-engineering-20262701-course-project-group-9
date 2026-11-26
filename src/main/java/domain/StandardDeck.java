package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StandardDeck {
    private StandardDeck() {
    }

    public static List<String> createCards() {
        List<String> cards = new ArrayList<>();
        for (String type : List.of("Exploding Kitten", "Attack", "Favor", "Shuffle", "Skip",
                "Tacocat", "Cattermelon", "Hairy Potato Cat", "Beard Cat", "Rainbow-Ralphing Cat")) {
            cards.addAll(Collections.nCopies(4, type));
        }
        cards.addAll(Collections.nCopies(6, "Defuse"));
        cards.addAll(Collections.nCopies(5, "Nope"));
        cards.addAll(Collections.nCopies(5, "See the Future"));
        return cards;
    }
}
