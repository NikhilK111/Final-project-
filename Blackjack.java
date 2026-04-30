import java.util.*;

public class Blackjack {

    // Starting money for the player at the beginning of the game
    private static final int STARTING_MONEY = 5000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Show the rules before the game starts
        // This helps the player understand how Blackjack works
        BlackjackRules.print();

        System.out.print("Start game? (y/n): ");

        // If the user does not want to play, exit the program
        if (!scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Goodbye!");
            return;
        }

        // Player starts with fixed money
        int money = STARTING_MONEY;

        // Deck is created once so cards don’t reset every round
        Deck deck = new Deck();

        // Game loop continues while player still has money
        while (money > 0) {

            System.out.println("\nMoney: $" + money);

            // ---------------- BETTING SECTION ----------------
            // Player must enter a valid bet within their available money
            int bet;
            while (true) {
                System.out.print("Enter bet: ");
                try {
                    bet = Integer.parseInt(scanner.nextLine());
                    if (bet > 0 && bet <= money) break;
                } catch (Exception ignored) {}
                System.out.println("Invalid bet.");
            }

            // ---------------- DEALING CARDS ----------------
            // Create hands for player and dealer
            Hand player = new Hand();
            Hand dealer = new Hand();

            // Each gets 2 cards to start (standard Blackjack rule)
            player.add(deck.draw());
            player.add(deck.draw());

            dealer.add(deck.draw());
            dealer.add(deck.draw());

            // Show one dealer card only (hidden information mechanic)
            System.out.println("Dealer shows: " + dealer.getFirstCard());
            System.out.println("Your hand: " + player);

            // ---------------- PLAYER TURN ----------------
            boolean turn = true;

            // Player can hit, stand, or double until they stop or bust
            while (turn && player.getValue() < 21) {

                System.out.print("Hit, Stand, or Double? (h/s/d): ");
                String choice = scanner.nextLine();

                switch (choice.toLowerCase()) {

                    // HIT = take another card
                    case "h":
                        player.add(deck.draw());
                        System.out.println(player);
                        break;

                    // STAND = end turn
                    case "s":
                        turn = false;
                        break;

                    // DOUBLE = double bet, take one card, end turn
                    case "d":
                        if (money >= bet) {
                            money -= bet;
                            bet *= 2;
                            player.add(deck.draw());
                            System.out.println("Double: " + player);
                        }
                        turn = false;
                        break;
                }
            }

            // ---------------- DEALER TURN ----------------
            // Dealer must hit until at least 17 (casino rule)
            if (player.getValue() <= 21) {
                while (dealer.getValue() < 17 ||
                      (dealer.getValue() == 17 && dealer.hasSoft17())) {
                    dealer.add(deck.draw());
                }
            }

            System.out.println("Dealer: " + dealer);

            // ---------------- RESULTS ----------------
            int pv = player.getValue();
            int dv = dealer.getValue();

            // Player loses if over 21
            if (pv > 21) {
                System.out.println("You bust. Lose.");
                money -= bet;

            // Player wins if dealer busts or player is closer to 21
            } else if (dv > 21 || pv > dv) {
                System.out.println("You win!");

                // Special rule: Blackjack pays 3:2
                if (player.isBlackjack()) {
                    money += (int)(bet * 1.5);
                } else {
                    money += bet;
                }

            // Dealer wins if closer to 21
            } else if (pv < dv) {
                System.out.println("You lose.");
                money -= bet;

            // Same value = push (tie, no one wins)
            } else {
                System.out.println("Push.");
            }

            // Ask if player wants to continue
            System.out.print("Play again? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
        }

        System.out.println("Game over.");
        scanner.close();
    }
}