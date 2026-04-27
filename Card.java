public class Card {
    private final String rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        switch (rank) {
            case "A": return 11;
            case "K":
            case "Q":
            case "J": return 10;
            default: return Integer.parseInt(rank);
        }
    }

    @Override
    public String toString() {
        return rank + suit;
    }
}