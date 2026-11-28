
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class SecondChanceTest {

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
    void emptyDeckDoesNotConsumeCard() {
        Game game = game(
                List.of("Second Chance"),
                List.of()
        );

        assertThrows(
                IllegalStateException.class,
                game::secondChance
        );

        assertEquals(
                List.of("Second Chance"),
                game.getCurrentPlayer().getHand()
        );
    }

    @Test
    void singleCardPreservesDeck() {
        Game game = game(
                List.of("Second Chance"),
                List.of("Skip")
        );

        game.secondChance();

        assertEquals(1, game.getDrawPileSize());
        assertTrue(game.getCurrentPlayer().getHand().isEmpty());

        game.draw();

        assertEquals(
                List.of("Skip"),
                game.getPlayers().get(0).getHand()
        );
    }

    @Test
    void twoCardsAreRotated() {
        Game game = game(
                List.of("Second Chance"),
                List.of("Attack", "Skip")
        );

        game.secondChance();

        game.draw();
        assertEquals(
                List.of("Skip"),
                game.getPlayers().get(0).getHand()
        );

        game.draw();
        assertEquals(
                List.of("Attack"),
                game.getPlayers().get(1).getHand()
        );
    }

    @Test
    void multipleCardsPreserveOrderAfterRotation() {
        Game game = game(
                List.of("Second Chance"),
                List.of("Attack", "Skip", "Favor")
        );

        game.secondChance();

        assertEquals(0, game.getCurrentPlayer().getId());
        assertEquals(3, game.getDrawPileSize());

        game.draw();
        game.draw();
        game.draw();

        assertEquals(
                List.of("Skip", "Attack"),
                game.getPlayers().get(0).getHand()
        );
        assertEquals(
                List.of("Favor"),
                game.getPlayers().get(1).getHand()
        );
    }

    @Test
    void missingCardIsRejected() {
        Game game = game(
                List.of("Skip"),
                List.of("Attack")
        );

        assertThrows(
                IllegalStateException.class,
                game::secondChance
        );

        assertEquals(
                List.of("Skip"),
                game.getCurrentPlayer().getHand()
        );
        assertEquals(1, game.getDrawPileSize());
    }

    @Test
    void consumesExactlyOneCopy() {
        Game game = game(
                List.of("Second Chance", "Second Chance"),
                List.of("Attack", "Skip")
        );

        game.secondChance();

        assertEquals(
                List.of("Second Chance"),
                game.getCurrentPlayer().getHand()
        );

        assertEquals(0, game.getCurrentPlayer().getId());
        assertEquals(2, game.getDrawPileSize());
    }

    @Test
    void cannotPlayDuringKittenReinsertion() {
        Game game = game(
                List.of("Second Chance", "Defuse"),
                List.of("Exploding Kitten", "Skip")
        );

        game.draw();

        assertTrue(game.needsReinsertion());

        assertThrows(
                IllegalStateException.class,
                game::secondChance
        );

        assertTrue(
                game.getCurrentPlayer()
                        .getHand().contains("Second Chance")
        );
    }

    @Test
    void cannotPlayAfterGameEnds() {
        Game game = new Game(
                List.of(
                        new Player(0, "Alice"),
                        new Player(
                                1, "Bob", List.of("Second Chance")
                        )
                ),
                List.of("Exploding Kitten"),
                0
        );

        game.draw();

        assertTrue(game.getWinner().isPresent());

        assertThrows(
                IllegalStateException.class,
                game::secondChance
        );
    }

    @Test
    void projectDeckContainsOneSecondChance() {
        GameSetup setup = new GameSetup(
                2,
                List.of("Alice", "Bob"),
                0
        );

        long inHands = setup.getPlayers().stream()
                .flatMap(player -> player.getHand().stream())
                .filter("Second Chance"::equals)
                .count();

        long inPile = setup.getDrawPile().stream()
                .filter("Second Chance"::equals)
                .count();

        assertEquals(1L, inHands + inPile);
    }
}
