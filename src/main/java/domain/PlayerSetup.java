
package domain;

public class PlayerSetup {

    private final int playerCount;

    public PlayerSetup(int playerCount) {
        if (playerCount < 2 || playerCount > 5) {
            throw new IllegalArgumentException(
                "Player count must be between 2 and 5"
            );
        }

        this.playerCount = playerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
