package domain;

import java.util.ArrayList;
import java.util.List;

public final class TurnInitialization {
    private final List<Integer> turnOrder;

    public TurnInitialization(int playerCount, int startingPlayerIndex) {
        PlayerSetup setup = new PlayerSetup(playerCount);
        int count = setup.getPlayerCount();
        if (startingPlayerIndex < 0 || startingPlayerIndex >= count) {
            throw new IllegalArgumentException(
                    "Starting player must be an index within the player list");
        }
        turnOrder = new ArrayList<>();
        for (int offset = 0; offset < count; offset++) {
            turnOrder.add((startingPlayerIndex + offset) % count);
        }
    }

    public List<Integer> getTurnOrder() {
        return new ArrayList<>(turnOrder);
    }

    public int getCurrentPlayerIndex() {
        return turnOrder.get(0);
    }
}
