package domain;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardDistributionTest {

    @Test
    void initialDealShouldGiveEachPlayerOneDefuseAndSevenOtherCards() {
        for (int playerCount : List.of(2, 3, 5)) {
            CardDistribution distribution = new CardDistribution(
                    playerCount, createDeck(playerCount));

            List<List<String>> hands = distribution.getHands();

            assertEquals(playerCount, hands.size());
            for (List<String> hand : hands) {
                assertEquals(8, hand.size());
                assertEquals(1, hand.stream()
                        .filter("Defuse"::equals)
                        .count());
                assertTrue(hand.stream()
                        .noneMatch("Exploding Kitten"::equals));
            }
        }
    }

    @Test
    void drawPileShouldContainPlayerCountMinusOneExplodingKittens() {
        int playerCount = 4;
        CardDistribution distribution = new CardDistribution(
                playerCount, createDeck(playerCount));

        long explodingKittens = distribution.getDrawPile().stream()
                .filter("Exploding Kitten"::equals)
                .count();

        assertEquals(playerCount - 1, explodingKittens);
    }

    @Test
    void extraExplodingKittensShouldNotBeAddedToDrawPile() {
        int playerCount = 2;
        List<String> deck = createDeck(playerCount);
        deck.add("Exploding Kitten");
        CardDistribution distribution = new CardDistribution(
                playerCount, deck);

        long explodingKittens = distribution.getDrawPile().stream()
                .filter("Exploding Kitten"::equals)
                .count();

        assertEquals(playerCount - 1, explodingKittens);
    }

    @Test
    void fivePlayersShouldBeAccepted() {
        CardDistribution distribution = new CardDistribution(
                5, createDeck(5));

        assertEquals(5, distribution.getHands().size());
    }

    @Test
    void remainingCardsShouldStayInDrawPile() {
        int playerCount = 2;
        List<String> deck = createDeck(playerCount);
        deck.add("Defuse");
        CardDistribution distribution = new CardDistribution(
                playerCount, deck);

        assertEquals(1, distribution.getDrawPile().stream()
                .filter("Defuse"::equals)
                .count());
    }

    @Test
    void invalidPlayerCountShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(1, createDeck(1)));
        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(6, createDeck(6)));
    }

    @Test
    void deckWithoutEnoughCardsShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, List.of("Defuse")));
    }

    @Test
    void deckWithoutEnoughDefuseCardsShouldBeRejected() {
        List<String> deck = createDeck(2);
        deck.remove("Defuse");

        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, deck));
    }

    @Test
    void deckWithoutEnoughExplodingKittensShouldBeRejected() {
        List<String> deck = createDeck(2);
        deck.remove("Exploding Kitten");

        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, deck));
    }

    @Test
    void deckWithoutEnoughOtherCardsShouldBeRejected() {
        List<String> deck = createDeck(2);
        deck.remove("Card 0");

        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, deck));
    }

    @Test
    void nullDeckShouldBeRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, null));
    }

    @Test
    void blankCardShouldBeRejected() {
        List<String> deck = createDeck(2);
        deck.set(0, " ");

        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, deck));
    }

    @Test
    void nullCardShouldBeRejected() {
        List<String> deck = createDeck(2);
        deck.set(0, null);

        assertThrows(IllegalArgumentException.class,
                () -> new CardDistribution(2, deck));
    }

    @Test
    void gettersShouldReturnCopies() {
        CardDistribution distribution = new CardDistribution(
                2, createDeck(2));
        List<List<String>> hands = distribution.getHands();
        List<String> drawPile = distribution.getDrawPile();

        hands.get(0).clear();
        hands.clear();
        drawPile.clear();

        assertEquals(2, distribution.getHands().size());
        assertEquals(8, distribution.getHands().get(0).size());
        assertFalse(distribution.getDrawPile().isEmpty());
    }

    private List<String> createDeck(int playerCount) {
        List<String> deck = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            deck.add("Defuse");
        }
        for (int i = 0; i < playerCount - 1; i++) {
            deck.add("Exploding Kitten");
        }
        for (int i = 0; i < playerCount * 7; i++) {
            deck.add("Card " + i);
        }
        return deck;
    }
}
