package domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Game {
    private final List<Player> players;
    private final List<String> deck;
    private final WinCondition win;
    private int current;
    private boolean reinsertion;

    public Game(List<Player> players, List<String> deck, int start) {
        new PlayerSetup(players.size());
        new TurnInitialization(players.size(), start);
        this.players = players.stream().map(Game::copyPlayer).collect(Collectors.toList());
        if (this.players.stream().anyMatch(player -> !player.isAlive())) {
            throw new IllegalArgumentException("Start with living players");
        }
        this.deck = new DeckSetup(deck).getCards();
        current = start;
        win = new WinCondition(this.players);
    }

    public Game(GameSetup setup) {
        this(setup.getPlayers(), setup.getDrawPile(), setup.getCurrentPlayerIndex());
    }

    public Player getCurrentPlayer() {
        return copyPlayer(players.get(current));
    }

    public List<Player> getPlayers() {
        return players.stream().map(Game::copyPlayer).collect(Collectors.toList());
    }

    public int getDrawPileSize() {
        return deck.size();
    }

    public Optional<Player> getWinner() {
        return win.getWinner().map(Game::copyPlayer);
    }

    public boolean needsReinsertion() {
        return reinsertion;
    }

    public void draw() {
        requireAction();
        if (deck.isEmpty()) {
            throw new IllegalStateException("Draw pile is empty");
        }
        String card = deck.remove(0);
        Player player = players.get(current);
        if ("Exploding Kitten".equals(card)) {
            if (player.getHand().contains("Defuse")) {
                reinsertion = true;
                return;
            }
            player.eliminate();
        } else {
            player.receive(card);
        }
        advance();
    }

    public void skip() {
        requireAction();
        if (!players.get(current).spend("Skip")) {
            throw new IllegalStateException("No Skip card");
        }
        advance();
    }

    public void reinsertKitten(int position) {
        if (!reinsertion) {
            throw new IllegalStateException("No kitten to reinsert");
        }
        if (position < 0 || position > deck.size()) {
            throw new IllegalArgumentException("Position outside draw pile");
        }
        players.get(current).spend("Defuse");
        deck.add(position, "Exploding Kitten");
        reinsertion = false;
        advance();
    }

    private void requireAction() {
        if (win.isGameOver() || reinsertion) {
            throw new IllegalStateException("Game finished or kitten needs reinsertion");
        }
    }

    private void advance() {
        do {
            current = (current + 1) % players.size();
        } while (!players.get(current).isAlive());
    }

    private static Player copyPlayer(Player source) {
        Player copy = new Player(source.getId(), source.getName(), source.getHand());
        if (!source.isAlive()) {
            copy.eliminate();
        }
        return copy;
    }
}
