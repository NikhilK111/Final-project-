import java.util.*;

public class Blackjack {
    private static final int STARTING_MONEY = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = STARTING_MONEY;

        System.out.println("Welcome to Blackjack!");

        while (money > 0) {
            System.out.println("\nMoney: $" + money);
            System.out.print("Enter bet: ");
            int bet = Integer.parseInt(scanner.nextLine());

            Deck deck = new Deck();
            Hand player = new Hand();
            Hand dealer = new Hand();

            player.add(deck.draw());
            dealer.add(deck.draw());
            player.add(deck.draw());
            dealer.add(deck.draw());

            System.out.println("Dealer shows: " + dealer);
            System.out.println("Your hand: " + player + " (" + player.getValue() + ")");

            // Player turn
            while (player.getValue() < 21) {
                System.out.print("Hit or Stand? (h/s): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("h")) {
                    player.add(deck.draw());
                    System.out.println("Your hand: " + player + " (" + player.getValue() + ")");
                } else {
                    break;
                }
            }

            if (player.getValue() > 21) {
                System.out.println("Bust! You lose.");
                money -= bet;
                continue;
            }

            // Dealer turn
            while (dealer.getValue() < 17 || dealer.hasSoft17()) {
                dealer.add(deck.draw());
            }

            System.out.println("Dealer hand: " + dealer + " (" + dealer.getValue() + ")");

            // Results
            if (dealer.getValue() > 21 || player.getValue() > dealer.getValue()) {
                System.out.println("You win!");
                money += bet;
            } else if (player.getValue() < dealer.getValue()) {
                System.out.println("You lose.");
                money -= bet;
            } else {
                System.out.println("Push.");
            }

            System.out.print("Play again? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
        }

        scanner.close();
    }
}