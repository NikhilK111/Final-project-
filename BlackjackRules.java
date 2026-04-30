public class BlackjackRules {
//The rules are printed at the start of the game, so players know how to play and win.
    public static void print() {
        System.out.println("=== BLACKJACK RULES ===");
        System.out.println("Welcome to Blackjack!");
        System.out.println("Goal:");
        System.out.println("Get as close to 21 as possible without going over.");
        System.out.println();
        System.out.println("Face Cards Like Queen,King,Jack count as 10");
        System.out.println("Aces count as either 1 or 11, whichever is better for you.");
        System.out.println("Number cards count as their face value (2-10).");
        System.out.println();
        System.out.println("You win by:");
        System.out.println("- Getting closer to 21 than the dealer");
        System.out.println("- Or the dealer busting (going over 21)");
        System.out.println("- Hitting exactly 21 (Blackjack)");
        System.out.println();
        System.out.println("You lose by:");
        System.out.println("- Going over 21 (Bust)");
        System.out.println("- The dealer being closer to 21");
        System.out.println();
        System.out.println("If you run out of money, the game ends.");
        System.out.println("Good luck");
        System.out.println();
    }
}