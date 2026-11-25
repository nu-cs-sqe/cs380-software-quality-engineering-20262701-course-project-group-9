package domain;

import java.util.List;
import java.util.Optional;

public final class Game {
    public Game(List<Player> players, List<String> deck, int start) { }
    public Game(GameSetup setup) { }
    public Player getCurrentPlayer() { return null; }
    public List<Player> getPlayers() { return null; }
    public int getDrawPileSize() { return 0; }
    public Optional<Player> getWinner() { return Optional.empty(); }
    public boolean needsReinsertion() { return false; }
    public void draw() { }
    public void skip() { }
    public void reinsertKitten(int position) { }
}
