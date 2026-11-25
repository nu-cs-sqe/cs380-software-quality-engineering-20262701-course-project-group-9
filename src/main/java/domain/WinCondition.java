
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class WinCondition {

    private final List<Player> players;

    public WinCondition(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public boolean isGameOver() {
        return countAlivePlayers() == 1;
    }

    public Optional<Player> getWinner() {
        if (!isGameOver()) {
            return Optional.empty();
        }

        for (Player player : players) {
            if (player.isAlive()) {
                return Optional.of(player);
            }
        }

        return Optional.empty();
    }

    private int countAlivePlayers() {
        int count = 0;

        for (Player player : players) {
            if (player.isAlive()) {
                count++;
            }
        }

        return count;
    }
}
