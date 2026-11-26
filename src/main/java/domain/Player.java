package domain;

import java.util.ArrayList;
import java.util.List;

public final class Player {

    private final int id;
    private final String name;
    private boolean alive;
    private final List<String> hand;

    public Player(int id, String name) {
        this(id, name, List.of());
    }

    public Player(int id, String name, List<String> cards) {
        this.id = id;
        this.name = name;
        this.alive = true;
        this.hand = new ArrayList<>(cards);
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

    public void eliminate() {
        alive = false;
    }

    void receive(String card) {
        hand.add(card);
    }

    String giveCardAt(int index) {
        return hand.remove(index);
    }

    boolean spend(String card) {
        return hand.remove(card);
    }

    public List<String> getHand() {
        return new ArrayList<>(hand);
    }
}
