public class Card {

    // The rank is the value/label of the card (2–10, J, Q, K, A)
    // Example: "A", "10", "K"
    private final String rank;

    // The suit is the symbol/category of the card
    // Example: Hearts, Spades, Diamonds, Clubs
    private final String suit;

    // Constructor: runs when we create a new card object
    // Example: new Card("A", "♠")
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Returns the rank of the card
    // Used when comparing cards or displaying them
    public String getRank() {
        return rank;
    }

    // Returns the suit of the card
    public String getSuit() {
        return suit;
    }

    // Converts the card into a numeric value for gameplay
    // Used in Blackjack and War to compare strength
    public int getValue() {

        switch (rank) {

            // Ace is worth 11 (Blackjack may adjust it later)
            case "A": return 11;

            // Face cards are all worth 10
            case "K":
            case "Q":
            case "J": return 10;

            // Number cards convert directly to their integer value
            default: return Integer.parseInt(rank);
        }
    }

    // Used later if you expand this into a GUI version
    // Returns the filename for a card image
    // Example: "A♠.png"
    public String getImageName() {
        return rank + suit + ".png";
    }

    // Controls how the card prints in the console
    // Instead of Card@123abc, it shows "A of ♠"
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}