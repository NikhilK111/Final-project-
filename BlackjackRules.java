public class BlackjackRules {

    public static void print() {
        System.out.println("==================================");
        System.out.println("         WELCOME TO BLACKJACK");
        System.out.println("==================================");

        System.out.println("Goal:");
        System.out.println("- Get as close to 21 as possible without going over.");

        System.out.println("\nCard Values:");
        System.out.println("- 2–10 = face value");
        System.out.println("- J, Q, K = 10");
        System.out.println("- A = 1 or 11");

        System.out.println("\nRules:");
        System.out.println("- Dealer hits until 17");
        System.out.println("- Dealer hits soft 17");
        System.out.println("- Blackjack pays 3:2");
        System.out.println("- You can Hit, Stand, or Double");

        System.out.println("==================================\n");
    }
}