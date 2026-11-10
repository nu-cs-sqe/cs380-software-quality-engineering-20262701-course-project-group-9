package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDistribution {

    private static final String DEFUSE_CARD = "Defuse";
    private static final String EXPLODING_KITTEN_CARD = "Exploding Kitten";
    private static final int RANDOM_CARDS_PER_PLAYER = 7;

    private final List<List<String>> hands;
    private final List<String> drawPile;

    public CardDistribution(int playerCount, List<String> deck) {
        new PlayerSetup(playerCount);

        if (deck == null) {
            throw new IllegalArgumentException("Deck must not be null");
        }

        List<String> defuseCards = new ArrayList<>();
        List<String> explodingKittens = new ArrayList<>();
        List<String> otherCards = new ArrayList<>();

        for (String card : deck) {
            if (card == null || card.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Deck must not contain null or blank cards"
                );
            }

            if (DEFUSE_CARD.equals(card)) {
                defuseCards.add(card);
            } else if (EXPLODING_KITTEN_CARD.equals(card)) {
                explodingKittens.add(card);
            } else {
                otherCards.add(card);
            }
        }

        int requiredOtherCards = playerCount * RANDOM_CARDS_PER_PLAYER;
        if (defuseCards.size() < playerCount
                || explodingKittens.size() < playerCount - 1
                || otherCards.size() < requiredOtherCards) {
            throw new IllegalArgumentException(
                "Deck does not contain enough cards for the initial deal"
            );
        }

        Collections.shuffle(otherCards);

        this.hands = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            List<String> hand = new ArrayList<>();
            hand.add(defuseCards.remove(0));
            for (int j = 0; j < RANDOM_CARDS_PER_PLAYER; j++) {
                hand.add(otherCards.remove(0));
            }
            this.hands.add(hand);
        }

        this.drawPile = new ArrayList<>();
        drawPile.addAll(otherCards);
        drawPile.addAll(defuseCards);
        drawPile.addAll(explodingKittens.subList(0, playerCount - 1));
        Collections.shuffle(drawPile);
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
