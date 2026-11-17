
package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerNames {

    private final List<String> names;

    public PlayerNames(int playerCount, List<String> inputNames) {
        validatePlayerCount(playerCount, inputNames);
        this.names = initPlayerNames(inputNames);
    }

    private void validatePlayerCount(int playerCount, List<String> inputNames) {
        if (inputNames.size() != playerCount) {
            throw new IllegalArgumentException(
                "Number of names must match player count"
            );
        }
    }

    private List<String> initPlayerNames(List<String> inputNames) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < inputNames.size(); i++) {
            String name = inputNames.get(i).trim();

            if (name.isEmpty()) {
                name = "Player " + (i + 1);
            }

            result.add(name);
        }

        return result;
    }

    public List<String> getNames() {
        return new ArrayList<>(names);
    }
}
