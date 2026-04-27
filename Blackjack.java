import java.util.*;

public class Blackjack {
    private static final int STARTING_MONEY = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = STARTING_MONEY;

        System.out.println("=== BLACKJACK ===");

        while (money > 0) {
            System.out.println("\nMoney: $" + money);

            // 🔹 BET
            int bet;
            while (true) {
                System.out.print("Enter bet: ");
                try {
                    bet = Integer.parseInt(scanner.nextLine());
                    if (bet > 0 && bet <= money) break;
                } catch (Exception ignored) {}
                System.out.println("Invalid bet.");
            }

            Deck deck = new Deck();

            // 🔹 DEAL
            List<Hand> playerHands = new ArrayList<>();
            Hand dealer = new Hand();

            Hand mainHand = new Hand();
            mainHand.add(deck.draw());
            mainHand.add(deck.draw());

            dealer.add(deck.draw());
            dealer.add(deck.draw());

            playerHands.add(mainHand);

            System.out.println("Dealer shows: " + dealer.getFirstCard());
            System.out.println("Your hand: " + mainHand);

            // 🔹 INSURANCE
            if (dealer.getFirstCard().getRank().equals("A")) {
                System.out.print("Insurance? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    int insurance = bet / 2;
                    money -= insurance;

                    if (dealer.getValue() == 21) {
                        System.out.println("Insurance wins!");
                        money += insurance * 3;
                    }
                }
            }

            // 🔹 SPLIT CHECK
            if (mainHand.getFirstCard().getRank().equals(mainHand.removeLast().getRank())
                    && money >= bet) {

                System.out.print("Split? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {

                    Hand split = new Hand();
                    split.add(mainHand.removeLast());

                    mainHand.add(deck.draw());
                    split.add(deck.draw());

                    playerHands.add(split);
                    money -= bet;

                    System.out.println("Split into 2 hands.");
                }
            }

            // 🔹 PLAYER TURN
            for (int i = 0; i < playerHands.size(); i++) {
                Hand h = playerHands.get(i);

                System.out.println("\nHand " + (i + 1) + ": " + h);

                boolean turn = true;
                while (turn && h.getValue() < 21) {

                    System.out.print("Hit, Stand, or Double? (h/s/d): ");
                    String choice = scanner.nextLine();

                    switch (choice.toLowerCase()) {

                        case "h":
                            h.add(deck.draw());
                            System.out.println(h);
                            break;

                        case "s":
                            turn = false;
                            break;

                        case "d":
                            if (money >= bet) {
                                money -= bet;
                                bet *= 2;
                                h.add(deck.draw());
                                System.out.println("Double: " + h);
                            }
                            turn = false;
                            break;
                    }
                }

                if (h.getValue() > 21) {
                    System.out.println("Bust!");
                }
            }

            // 🔹 DEALER TURN
            while (dealer.getValue() < 17 ||
                  (dealer.getValue() == 17 && dealer.hasSoft17())) {
                dealer.add(deck.draw());
            }

            System.out.println("\nDealer: " + dealer + " = " + dealer.getValue());

            // 🔹 RESULTS (3:2 BLACKJACK PAYOUT)
            for (Hand h : playerHands) {
                int pv = h.getValue();
                int dv = dealer.getValue();

                System.out.println("\nHand: " + h + " = " + pv);

                if (pv > 21) {
                    money -= bet;
                } else if (dv > 21 || pv > dv) {
                    System.out.println("Win!");
                    money += (int)(bet * 1.5);
                } else if (pv < dv) {
                    System.out.println("Lose.");
                    money -= bet;
                } else {
                    System.out.println("Push.");
                }
            }

            System.out.print("\nPlay again? (y/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
        }

        System.out.println("Game over.");
        scanner.close();
    }
}