package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    public List<Integer> favorTargets() {
        requireAction();
        if (!players.get(current).getHand().contains("Favor")) {
            throw new IllegalStateException("No Favor card");
        }
        List<Integer> seats = new ArrayList<>();
        for (int seat = 0; seat < players.size(); seat++) {
            Player target = players.get(seat);
            if (seat != current && target.isAlive() && !target.getHand().isEmpty()) {
                seats.add(seat);
            }
        }
        return seats;
    }

    public void favor(int targetSeat, int cardIndex) {
        if (!favorTargets().contains(targetSeat)) {
            throw new IllegalArgumentException("Choose an opponent with a card");
        }
        Player target = players.get(targetSeat);
        if (cardIndex < 0 || cardIndex >= target.getHand().size()) {
            throw new IllegalArgumentException("Card outside target hand");
        }
        Player actor = players.get(current);
        actor.spend("Favor");
        actor.receive(target.giveCardAt(cardIndex));
    }

    public int soundcheck() {
        requireAction();
        if (!players.get(current).spend("Soundcheck")) {
            throw new IllegalStateException("No Soundcheck card");
        }
        int end = Math.min(5, deck.size());
        return Collections.frequency(deck.subList(0, end), "Exploding Kitten");
    }

    public void skip() {
        requireAction();
        if (!players.get(current).spend("Skip")) {
            throw new IllegalStateException("No Skip card");
        }
        advance();
    }

    public List<String> seeTheFuture() {
    requireAction();

    Player player = players.get(current);

    if (!player.spend("See the Future")) {
        throw new IllegalStateException(
            "No See the Future card"
        );
    }

    int numberOfCards = Math.min(3, deck.size());

    return new ArrayList<>(deck.subList(0, numberOfCards));
    }

    public void shuffle() {
    shuffle(new Random());
}

    public void shuffle(Random random) {
    requireAction();

    if (random == null) {
        throw new IllegalArgumentException(
            "Random must not be null"
        );
    }

    Player player = players.get(current);

    if (!player.spend("Shuffle")) {
        throw new IllegalStateException(
            "No Shuffle card"
        );
    }

    Collections.shuffle(deck, random);
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
