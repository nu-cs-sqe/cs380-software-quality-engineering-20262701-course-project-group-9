package ui;

import domain.Game;
import domain.GameSetup;
import domain.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class ConsoleGame {
    private final BufferedReader input;
    private final PrintStream output;

    ConsoleGame(BufferedReader input, PrintStream output) {
        this.input = input;
        this.output = output;
    }

    void start() throws IOException {
        Integer count = readNumber("How many players?", 2, 5);
        if (count == null) {
            return;
        }
        List<String> names = new ArrayList<>();
        for (int seat = 1; seat <= count; seat++) {
            output.println("Name for player " + seat + " (leave blank for the default):");
            String name = input.readLine();
            if (name == null) {
                return;
            }
            names.add(name);
        }
        Integer first = readNumber("Which player goes first? Enter their seat number.", 1, count);
        if (first != null) {
            play(new Game(new GameSetup(count, names, first - 1)));
        }
    }

    void play(Game game) throws IOException {
        while (game.getWinner().isEmpty()) {
            if (game.needsReinsertion()) {
                Integer position = readNumber("Reinsert the kitten (0 is the top).", 0, game.getDrawPileSize());
                if (position == null) {
                    return;
                }
                game.reinsertKitten(position);
                continue;
            }
            Player player = game.getCurrentPlayer();
            output.println(
                player.getName()
                + "'s turn. Enter draw, skip, future, shuffle, favor, soundcheck, or hand:"
            );
            String command = input.readLine();
            if (command == null) {
                return;
            }
            takeAction(game, player, command);
        }
        output.println(game.getWinner().orElseThrow().getName() + " wins!");
    }

    private void takeAction(Game game, Player player, String command) throws IOException {
        try {
            switch (command.trim().toLowerCase(Locale.ROOT)) {
                case "hand":
                    output.println("Hand: " + player.getHand());
                    break;
                case "skip":
                    game.skip();
                    output.println(player.getName() + " played Skip.");
                    break;
                case "future":
                    playSeeTheFuture(game);
                    break;
                case "shuffle":
                    game.shuffle();
                    output.println(player.getName() + " played Shuffle.");
                    break;
                case "favor":
                    playFavor(game, player);
                    break;
                case "soundcheck":
                    int count = game.soundcheck();
                    output.println(player.getName() + " played Soundcheck. Exploding Kittens detected: " + count);
                    break;
                case "draw":
                    game.draw();
                    showDrawResult(game, player);
                    break;
                default:
                    output.println("Unknown command.");
            }
        } catch (IllegalStateException invalidMove) {
            output.println(invalidMove.getMessage());
        }
    }
    private void playSeeTheFuture(Game game) throws IOException {
    List<String> future = game.seeTheFuture();

    output.println("Future cards: " + future);
    output.println("Press Enter to hide the cards.");

    String line;
    while ((line = input.readLine()) != null) {
        if (line.isEmpty()) {
            break;
        }
        output.println("Press Enter without typing anything.");
    }

    clearScreen();
    }

    private void playFavor(Game game, Player actor) throws IOException {
        List<Integer> seats = game.favorTargets();
        if (seats.isEmpty()) {
            output.println("No opponent can give a card.");
            return;
        }
        List<Player> players = game.getPlayers();
        for (int i = 0; i < seats.size(); i++) {
            int seat = seats.get(i);
            output.println((i + 1) + ". " + seatLabel(players.get(seat), seat));
        }
        Integer choice = readNumber("Choose an opponent.", 1, seats.size());
        if (choice == null) {
            return;
        }
        int seat = seats.get(choice - 1);
        Player donor = players.get(seat);
        clearScreen();
        output.println("Pass the keyboard to " + seatLabel(donor, seat) + ". Press Enter when ready.");
        if (!confirmHandoff()) {
            return;
        }
        List<String> hand = donor.getHand();
        for (int i = 0; i < hand.size(); i++) {
            output.println((i + 1) + ". " + hand.get(i));
        }
        Integer card = readNumber("Choose the card to give.", 1, hand.size());
        clearScreen();
        if (card != null) {
            game.favor(seat, card - 1);
            output.println("Pass the keyboard back to " + actor.getName() + ".");
        }
    }

    private boolean confirmHandoff() throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            if (line.isEmpty()) {
                return true;
            }
            output.println("Press Enter without typing a choice.");
        }
        return false;
    }

    private String seatLabel(Player player, int seat) {
        return player.getName() + " (seat " + (seat + 1) + ")";
    }

    private void clearScreen() {
        output.print("\033[2J\033[H");
    }

    private void showDrawResult(Game game, Player player) {
        if (game.needsReinsertion()) {
            output.println(player.getName() + " drew an Exploding Kitten and can use Defuse.");
            return;
        }
        boolean eliminated = game.getPlayers().stream()
                .anyMatch(p -> p.getId() == player.getId() && !p.isAlive());
        output.println(player.getName() + (eliminated ? " was eliminated." : " drew a card."));
    }

    private Integer readNumber(String prompt, int low, int high) throws IOException {
        output.println(prompt + " (" + low + "-" + high + ")");
        String line;
        while ((line = input.readLine()) != null) {
            try {
                int value = Integer.parseInt(line.trim());
                if (value >= low && value <= high) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Invalid text and out-of-range numbers use the same retry prompt.
            }
            output.println("Enter a number from " + low + " to " + high + ".");
        }
        return null;
    }
}
