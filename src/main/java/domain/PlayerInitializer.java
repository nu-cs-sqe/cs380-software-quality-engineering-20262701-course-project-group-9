
package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerInitializer {

    private final List<Player> players;

    public PlayerInitializer(int playerCount, List<String> inputNames) {

        PlayerSetup setup = new PlayerSetup(playerCount);
        PlayerNames playerNames = new PlayerNames(
            setup.getPlayerCount(), inputNames
        );

        this.players = new ArrayList<>();

        List<String> names = playerNames.getNames();

        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(i, names.get(i)));
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
