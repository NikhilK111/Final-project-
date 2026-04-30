import java.util.*;

public class War {

    // This method runs the entire War card game
    public static void playWar() {

        // Scanner lets the user control the game flow
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to War!");

        // Create and shuffle a new deck of 52 cards
        Deck deck = new Deck();

        // Two players, each gets their own pile of cards
        List<Card> player1 = new ArrayList<>();
        List<Card> player2 = new ArrayList<>();

        // ---------------- DEALING ----------------
        // Each player gets 26 cards (half the deck)
        for (int i = 0; i < 26; i++) {
            player1.add(deck.draw());
            player2.add(deck.draw());
        }

        // ---------------- MAIN GAME LOOP ----------------
        // Game continues until one player runs out of cards
        while (!player1.isEmpty() && !player2.isEmpty()) {

            // Each player flips the top card of their pile
            Card c1 = player1.remove(0);
            Card c2 = player2.remove(0);

            System.out.println("Player 1: " + c1);
            System.out.println("Player 2: " + c2);

            // Pot holds cards that will be won in this round
            List<Card> pot = new ArrayList<>();
            pot.add(c1);
            pot.add(c2);

            int v1 = c1.getValue();
            int v2 = c2.getValue();

            // ---------------- NORMAL ROUND ----------------
            if (v1 > v2) {

                // Player 1 wins and takes all cards in pot
                player1.addAll(pot);
                System.out.println("Player 1 wins the round!");

            } else if (v2 > v1) {

                // Player 2 wins and takes all cards in pot
                player2.addAll(pot);
                System.out.println("Player 2 wins the round!");

            } else {

                // ---------------- WAR CONDITION ----------------
                System.out.println("WAR!");

                boolean warResolved = false;

                // Keep repeating war until someone wins
                while (!warResolved) {

                    List<Card> warPot = new ArrayList<>();

                    // If a player does not have enough cards, they lose
                    if (player1.size() < 4) {
                        player2.addAll(player1);
                        player2.addAll(pot);
                        player1.clear();
                        System.out.println("Player 1 cannot continue war. Player 2 wins!");
                        return;
                    }

                    if (player2.size() < 4) {
                        player1.addAll(player2);
                        player1.addAll(pot);
                        player2.clear();
                        System.out.println("Player 2 cannot continue war. Player 1 wins!");
                        return;
                    }

                    // ---------------- WAR SETUP ----------------
                    // Each player places 3 cards face-down
                    for (int i = 0; i < 3; i++) {
                        warPot.add(player1.remove(0));
                        warPot.add(player2.remove(0));
                    }

                    // Then each player flips a 4th card
                    Card warC1 = player1.remove(0);
                    Card warC2 = player2.remove(0);

                    System.out.println("War card P1: " + warC1);
                    System.out.println("War card P2: " + warC2);

                    warPot.add(warC1);
                    warPot.add(warC2);

                    int wv1 = warC1.getValue();
                    int wv2 = warC2.getValue();

                    // ---------------- WAR RESULT ----------------
                    if (wv1 > wv2) {

                        // Player 1 wins everything in the war
                        player1.addAll(pot);
                        player1.addAll(warPot);

                        System.out.println("Player 1 wins the war!");
                        warResolved = true;

                    } else if (wv2 > wv1) {

                        // Player 2 wins everything in the war
                        player2.addAll(pot);
                        player2.addAll(warPot);

                        System.out.println("Player 2 wins the war!");
                        warResolved = true;

                    } else {

                        // Another tie → repeat war again
                        pot.addAll(warPot);
                        System.out.println("Another war!");
                    }
                }
            }

            // Show current card counts for both players
            System.out.println("Player 1 has " + player1.size() + " cards.");
            System.out.println("Player 2 has " + player2.size() + " cards.");

            // Allow player to return to menu or continue
            System.out.print("Continue playing War or return to menu? (c/m): ");
            if (scanner.nextLine().equalsIgnoreCase("m")) {
                return;
            }
        }

        // ---------------- GAME OVER ----------------
        if (player1.isEmpty()) {
            System.out.println("Player 2 wins the game!");
        } else {
            System.out.println("Player 1 wins the game!");
        }
    }

    // Allows War to run on its own (testing or direct launch)
    public static void main(String[] args) {
        playWar();
    }
}