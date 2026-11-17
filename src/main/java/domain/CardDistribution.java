package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CardDistribution {

    private static final String DEFUSE_CARD = "Defuse";
    private static final String EXPLODING_KITTEN_CARD = "Exploding Kitten";
    private static final int RANDOM_CARDS_PER_PLAYER = 7;

    private final List<List<String>> hands;
    private final List<String> drawPile;

    public CardDistribution(int playerCount, List<String> deck) {
        new PlayerSetup(playerCount);
        List<String> otherCards = new DeckSetup(deck).getCards();
        List<String> defuseCards = extractCards(otherCards, DEFUSE_CARD);
        List<String> explodingKittens = extractCards(otherCards, EXPLODING_KITTEN_CARD);
        validateCardCounts(playerCount, defuseCards.size(), explodingKittens.size(), otherCards.size());
        Collections.shuffle(otherCards);
        hands = dealHands(playerCount, defuseCards, otherCards);
        drawPile = createDrawPile(otherCards, defuseCards, explodingKittens.subList(0, playerCount - 1));
    }

    private List<String> extractCards(List<String> cards, String type) {
        List<String> matching = new ArrayList<>();
        java.util.Iterator<String> iterator = cards.iterator();
        while (iterator.hasNext()) {
            String card = iterator.next();
            if (type.equals(card)) {
                matching.add(card);
                iterator.remove();
            }
        }
        return matching;
    }

    private void validateCardCounts(int playerCount, int defuseCount, int kittenCount, int otherCount) {
        if (defuseCount < playerCount || kittenCount < playerCount - 1
                || otherCount < playerCount * RANDOM_CARDS_PER_PLAYER) {
            throw new IllegalArgumentException("Deck does not contain enough cards for the initial deal");
        }
    }

    private List<List<String>> dealHands(int playerCount, List<String> defuses, List<String> otherCards) {
        List<List<String>> dealt = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            List<String> hand = new ArrayList<>();
            hand.add(defuses.remove(0));
            for (int j = 0; j < RANDOM_CARDS_PER_PLAYER; j++) {
                hand.add(otherCards.remove(0));
            }
            dealt.add(hand);
        }
        return dealt;
    }

    private List<String> createDrawPile(List<String> otherCards, List<String> defuses, List<String> kittens) {
        List<String> pile = new ArrayList<>(otherCards);
        pile.addAll(defuses);
        pile.addAll(kittens);
        Collections.shuffle(pile);
        return pile;
    }

    public List<List<String>> getHands() {
        List<List<String>> handCopies = new ArrayList<>();
        for (List<String> hand : hands) {
            handCopies.add(new ArrayList<>(hand));
        }
        return handCopies;
    }

    public List<String> getDrawPile() {
        return new ArrayList<>(drawPile);
    }
}
