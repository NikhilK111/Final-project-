public class Card {

    // The rank is the value shown on the card (2–10, J, Q, K, A)
    private final String rank;

    // The suit is the symbol of the card (♠, ♥, ♦, ♣)
    private final String suit;

    // Constructor runs when a new card is created
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Returns the rank of the card
    public String getRank() {
        return rank;
    }

    // Returns the suit of the card
    public String getSuit() {
        return suit;
    }

    // Returns the blackjack value of the card
    // Face cards are worth 10
    // Ace starts as 11 (Hand class adjusts to 1 if needed)
    public int getValue() {
        switch (rank) {
            case "A": return 11;
            case "K":
            case "Q":
            case "J": return 10;
            default: return Integer.parseInt(rank);
        }
    }

    // This will be used later when adding card images to a GUI
    // Example: "A♠.png"
    public String getImageName() {
        return rank + suit + ".png";
    }

    // Controls how the card prints to the console
    // Instead of Card@4a54c0de, it shows: "A of ♠"
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}