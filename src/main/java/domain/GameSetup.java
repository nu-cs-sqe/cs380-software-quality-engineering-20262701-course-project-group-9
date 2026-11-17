package domain;

import java.util.ArrayList;
import java.util.List;

public final class GameSetup {
    private final List<Player> players;
    private final CardDistribution distribution;
    private final TurnInitialization turns;

    public GameSetup(int count, List<String> names, List<String> deck, int start) {
        PlayerInitializer initializer = new PlayerInitializer(count, names);
        turns = new TurnInitialization(count, start);
        distribution = new CardDistribution(count, new DeckSetup(deck).getCards());
        players = new ArrayList<>();
        List<List<String>> hands = distribution.getHands();
        for (Player player : initializer.getPlayers()) {
            players.add(new Player(player.getId(), player.getName(), hands.get(player.getId())));
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getDrawPile() {
        return distribution.getDrawPile();
    }

    public List<Integer> getTurnOrder() {
        return turns.getTurnOrder();
    }

    public int getCurrentPlayerIndex() {
        return turns.getCurrentPlayerIndex();
    }
}
