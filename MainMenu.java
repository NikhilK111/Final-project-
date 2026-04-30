import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

        // Scanner is used to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Infinite loop keeps showing the menu until the user exits
        while (true) {

            // Display menu options
            System.out.println("\nWhat game would you like to play?");
            System.out.println("1. Blackjack");
            System.out.println("2. War");
            System.out.println("3. Exit");

            // Get user choice
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            // If user selects Blackjack, start Blackjack game
            if (choice.equals("1")) {

                // Calls the main method of Blackjack class
                Blackjack.main(new String[]{});

            // If user selects War, start War game
            } else if (choice.equals("2")) {

                // Calls War game method
                War.playWar();

            // Exit option ends the program loop
            } else if (choice.equals("3")) {

                System.out.println("Goodbye!");
                break;

            // Handles invalid input
            } else {

                System.out.println("Invalid choice.");
            }
        }

        // Closes scanner to free system resources
        scanner.close();
    }
}