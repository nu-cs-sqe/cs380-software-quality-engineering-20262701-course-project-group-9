
package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final int id;
    private final String name;
    private boolean alive;
    private final List<String> hand;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.alive = true;
        this.hand = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public List<String> getHand() {
        return new ArrayList<>(hand);
    }
}
