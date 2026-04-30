import java.util.*;

public class Blackjack {

    // Starting money the player begins the game with
    private static final int STARTING_MONEY = 5000;

    public static void main(String[] args) {

        // Scanner allows us to get input from the user (keyboard)
        Scanner scanner = new Scanner(System.in);

        // Show the rules before the game starts
        BlackjackRules.print();

        // Ask the player if they want to start the game
        System.out.print("Start game? (y/n): ");
        if (!scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Goodbye!");
            return; // Ends program if player says no
        }

        // Player's current money
        int money = STARTING_MONEY;

        System.out.println("=== BLACKJACK STARTED ===");

        // Main game loop — keeps running while player still has money
        while (money > 0) {

            // Display current money
            System.out.println("\nMoney: $" + money);

            // ---------------- BETTING PHASE ----------------
            int bet;

            // Loop until a valid bet is entered
            while (true) {
                System.out.print("Enter bet: ");
                try {
                    bet = Integer.parseInt(scanner.nextLine());

                    // Bet must be greater than 0 and less than available money
                    if (bet > 0 && bet <= money) break;

                } catch (Exception ignored) {}

                System.out.println("Invalid bet.");
            }

            // Create a new deck of cards for this round
            Deck deck = new Deck();

            // Lists allow multiple hands (used later for splits)
            List<Hand> playerHands = new ArrayList<>();
            List<Integer> handBets = new ArrayList<>();

            // Dealer's hand
            Hand dealer = new Hand();

            // Player's main hand
            Hand mainHand = new Hand();

            // Deal two cards to player
            mainHand.add(deck.draw());
            mainHand.add(deck.draw());

            // Deal two cards to dealer
            dealer.add(deck.draw());
            dealer.add(deck.draw());

            // Add player's hand and bet to the lists
            playerHands.add(mainHand);
            handBets.add(bet);

            // Show dealer's first card and player's hand
            System.out.println("Dealer shows: " + dealer.getFirstCard());
            System.out.println("Your hand: " + mainHand);

            // ---------------- PLAYER TURN ----------------
            // Loop through each hand (important for future split feature)
            for (int i = 0; i < playerHands.size(); i++) {

                Hand h = playerHands.get(i);
                int currentBet = handBets.get(i);

                boolean turn = true;

                // Player can keep choosing actions while under 21
                while (turn && h.getValue() < 21) {

                    System.out.print("Hit, Stand, or Double? (h/s/d): ");
                    String choice = scanner.nextLine();

                    switch (choice.toLowerCase()) {

                        // HIT = take another card
                        case "h":
                            h.add(deck.draw());
                            System.out.println(h);
                            break;

                        // STAND = end turn
                        case "s":
                            turn = false;
                            break;

                        // DOUBLE = double bet, take one card, end turn
                        case "d":
                            if (money >= currentBet) {
                                money -= currentBet;
                                currentBet *= 2;
                                handBets.set(i, currentBet);
                                h.add(deck.draw());
                                System.out.println("Double: " + h);
                            } else {
                                System.out.println("Not enough money.");
                            }
                            turn = false;
                            break;
                    }
                }

                // If hand value is over 21, player busts
                if (h.getValue() > 21) {
                    System.out.println("Bust!");
                }
            }

            
            // Dealer must hit until at least 17
            // Also hits on "soft 17" (Ace counted as 11)
            while (dealer.getValue() < 17 ||
                  (dealer.getValue() == 17 && dealer.hasSoft17())) {
                dealer.add(deck.draw());
            }

            System.out.println("\nDealer: " + dealer + " = " + dealer.getValue());

            //  RESULTS  
            // Compare each player hand to dealer hand
            for (int i = 0; i < playerHands.size(); i++) {

                Hand h = playerHands.get(i);
                int betVal = handBets.get(i);

                int pv = h.getValue();      // player value
                int dv = dealer.getValue(); // dealer value

                System.out.println("\nHand " + (i + 1) + ": " + h);

                if (pv > 21) {
                    System.out.println("Lose.");
                    money -= betVal;

                } else if (dv > 21 || pv > dv) {
                    System.out.println("Win!");

                    // Blackjack pays 3:2
                    if (h.isBlackjack()) {
                        money += (int)(betVal * 1.5);
                    } else {
                        money += betVal;
                    }

                } else if (pv < dv) {
                    System.out.println("Lose.");
                    money -= betVal;

                } else {
                    System.out.println("Push."); // tie, no money change
                }
            }

            // Ask player if they want to continue playing
            System.out.print("\nPlay again? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
        }

        System.out.println("Game over.");
        scanner.close();
    }
}