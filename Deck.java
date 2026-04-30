import java.util.*;

public class Deck {

    // List that holds all 52 cards in the deck
    private List<Card> cards = new ArrayList<>();

    // Constructor: when a Deck is created, it automatically fills and shuffles
    public Deck() {
        reset();
    }

    // Resets the deck back to a full 52 cards and shuffles them
    public void reset() {
        cards.clear(); // remove any existing cards

        // All possible ranks and suits in a standard deck
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] suits = {"S","H","D","C"}; // Spades, Hearts, Diamonds, Clubs

        // Create every combination of rank and suit (52 cards)
        for (String r : ranks) {
            for (String s : suits) {
                cards.add(new Card(r, s));
            }
        }

        // Randomize the order of the cards
        Collections.shuffle(cards);
    }

    // Draws (removes) the top card from the deck
    public Card draw() {

        // If deck runs out of cards, automatically reset and reshuffle
        if (cards.isEmpty()) reset();

        // Remove and return the last card in the list
        return cards.remove(cards.size() - 1);
    }

    // Returns how many cards are left in the deck
    public int size() {
        return cards.size();
    }
}