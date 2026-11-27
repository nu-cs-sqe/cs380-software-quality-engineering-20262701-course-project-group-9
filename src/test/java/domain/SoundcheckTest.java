package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SoundcheckTest {
    private static final String KITTEN = "Exploding Kitten";

    private Game game(List<String> hand, List<String> pile) {
        return new Game(List.of(new Player(8, "A", hand), new Player(12, "B")), pile, 0);
    }

    static Stream<Arguments> scans() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of("Skip", "Favor", "Attack", "Nope"), 0),
                Arguments.of(List.of(KITTEN), 1),
                Arguments.of(List.of(KITTEN, "Skip", KITTEN, "Favor", KITTEN), 3),
                Arguments.of(List.of(KITTEN, "Skip", "Nope", "Favor", KITTEN, KITTEN), 2));
    }

    @ParameterizedTest
    @MethodSource("scans")
    void countsKittensOnlyInTheFirstFiveCards(List<String> pile, int expected) {
        Game game = game(List.of("Soundcheck"), pile);
        assertEquals(expected, game.soundcheck());
        assertTrue(game.getCurrentPlayer().getHand().isEmpty());
        assertEquals(pile.size(), game.getDrawPileSize());
    }

    @Test
    void spendsOneCopyAndPreservesTurnAndDrawOrder() {
        List<String> pile = List.of("Favor", "Skip", "Attack", "Nope", "Shuffle", "Tacocat");
        Game game = game(List.of("Soundcheck", "Soundcheck", "Defuse"), pile);
        assertEquals(0, game.soundcheck());
        assertEquals(8, game.getCurrentPlayer().getId());
        assertEquals(List.of("Soundcheck", "Defuse"), game.getCurrentPlayer().getHand());
        for (int i = 0; i < pile.size(); i++) {
            game.draw();
            List<String> hand = game.getPlayers().get(i % 2).getHand();
            assertEquals(pile.get(i), hand.get(hand.size() - 1));
        }
    }

    @Test
    void rejectsPlayWithoutSoundcheck() {
        Game game = game(List.of("Defuse"), List.of("Skip"));
        assertThrows(IllegalStateException.class, game::soundcheck);
        assertEquals(List.of("Defuse"), game.getCurrentPlayer().getHand());
        assertEquals(1, game.getDrawPileSize());
    }

    @Test
    void preservesSoundcheckDuringReinsertion() {
        List<String> hand = List.of("Soundcheck", "Defuse");
        Game game = game(hand, List.of(KITTEN, "Skip"));
        game.draw();
        assertThrows(IllegalStateException.class, game::soundcheck);
        assertTrue(game.needsReinsertion());
        assertEquals(hand, game.getCurrentPlayer().getHand());
    }

    @Test
    void winnerCannotSpendSoundcheckAfterTheGame() {
        Game game = new Game(List.of(new Player(0, "A"), new Player(1, "B", List.of("Soundcheck"))),
                List.of(KITTEN), 0);
        game.draw();
        assertThrows(IllegalStateException.class, game::soundcheck);
        assertEquals(List.of("Soundcheck"), game.getWinner().orElseThrow().getHand());
    }
}
