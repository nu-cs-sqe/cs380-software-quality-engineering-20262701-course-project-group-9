package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

class FutureAndShuffleTest {

    private Game game(List<String> hand, List<String> deck) {
        return new Game(
            List.of(
                new Player(0, "Alice", hand),
                new Player(1, "Bob")
            ),
            deck,
            0
        );
    }

    @Test
    void seeFutureWithEmptyDeck() {
        Game game = game(List.of("See the Future"), List.of());

        assertEquals(List.of(), game.seeTheFuture());
        assertEquals(0, game.getDrawPileSize());
    }

    @Test
    void seeFutureWithOneCard() {
        Game game = game(
            List.of("See the Future"),
            List.of("Skip")
        );

        assertEquals(List.of("Skip"), game.seeTheFuture());
    }

    @Test
    void seeFutureWithTwoCards() {
        Game game = game(
            List.of("See the Future"),
            List.of("Skip", "Attack")
        );

        assertEquals(
            List.of("Skip", "Attack"),
            game.seeTheFuture()
        );
    }

    @Test
    void seeFutureWithThreeCards() {
        Game game = game(
            List.of("See the Future"),
            List.of("Skip", "Attack", "Favor")
        );

        assertEquals(
            List.of("Skip", "Attack", "Favor"),
            game.seeTheFuture()
        );
    }

    @Test
    void seeFutureWithFourCardsOnlyShowsThree() {
        Game game = game(
            List.of("See the Future"),
            List.of("Skip", "Attack", "Favor", "Nope")
        );

        assertEquals(
            List.of("Skip", "Attack", "Favor"),
            game.seeTheFuture()
        );

        assertEquals(4, game.getDrawPileSize());
        assertEquals(0, game.getCurrentPlayer().getId());
    }

    @Test
    void seeFutureRequiresCard() {
        Game game = game(
            List.of("Skip"),
            List.of("Attack")
        );

        assertThrows(
            IllegalStateException.class,
            game::seeTheFuture
        );
    }

    @Test
    void seeFutureConsumesCardWithoutEndingTurn() {
        Game game = game(
            List.of("See the Future", "Skip"),
            List.of("Attack")
        );

        game.seeTheFuture();

        assertEquals(List.of("Skip"),
            game.getCurrentPlayer().getHand());
        assertEquals(0, game.getCurrentPlayer().getId());
    }

    @Test
    void shuffleWithEmptyDeck() {
        Game game = game(List.of("Shuffle"), List.of());

        game.shuffle();

        assertEquals(0, game.getDrawPileSize());
    }

    @Test
    void shuffleWithOneCard() {
        Game game = game(
            List.of("Shuffle"),
            List.of("Skip")
        );

        game.shuffle();

        assertEquals(1, game.getDrawPileSize());
    }

    @Test
    void shuffleChangesDrawOrderWithFixedSeed() {
    List<String> cards = List.of(
        "Skip", "Attack", "Favor", "Nope"
    );

    Game game = game(List.of("Shuffle"), cards);

    List<String> expected = new ArrayList<>(cards);
    Collections.shuffle(expected, new Random(42));

    game.shuffle(new Random(42));

    List<String> actual = new ArrayList<>();

    for (int i = 0; i < cards.size(); i++) {
        int currentId = game.getCurrentPlayer().getId();

        int before = game.getPlayers()
            .get(currentId).getHand().size();

        game.draw();

        List<String> hand = game.getPlayers()
            .get(currentId).getHand();

        assertEquals(before + 1, hand.size());

        actual.add(hand.get(hand.size() - 1));
    }

    assertNotEquals(cards, expected);
    assertEquals(expected, actual);
    }

    @Test
    void shuffleRequiresCard() {
        Game game = game(
            List.of("Skip"),
            List.of("Attack")
        );

        assertThrows(
            IllegalStateException.class,
            game::shuffle
        );
    }

    @Test
    void shuffleConsumesCardWithoutEndingTurn() {
        Game game = game(
            List.of("Shuffle", "Skip"),
            List.of("Attack", "Favor")
        );

        game.shuffle();

        assertEquals(List.of("Skip"),
            game.getCurrentPlayer().getHand());
        assertEquals(0, game.getCurrentPlayer().getId());
        assertEquals(2, game.getDrawPileSize());
    }

    @Test
    void actionsBlockedDuringReinsertion() {
        Game game = game(
            List.of("Defuse", "See the Future", "Shuffle"),
            List.of("Exploding Kitten", "Skip")
        );

        game.draw();

        assertTrue(game.needsReinsertion());

        assertThrows(
            IllegalStateException.class,
            game::seeTheFuture
        );

        assertThrows(
            IllegalStateException.class,
            game::shuffle
        );
    }
}
