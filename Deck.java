import java.util.*;

public class Deck {

    // This list stores all 52 cards in the deck
    // ArrayList is used because it allows easy adding/removing cards
    private List<Card> cards = new ArrayList<>();

    // Constructor: automatically builds a full deck when created
    public Deck() {
        reset();
    }

    // Resets the deck back to a full shuffled 52-card deck
    public void reset() {

        // Clear any existing cards in the deck
        cards.clear();

        // All possible card ranks in a standard deck
        String[] ranks = {
            "2","3","4","5","6","7","8","9","10","J","Q","K","A"
        };

        // All four suits in a deck
        String[] suits = {
            "♠","♥","♦","♣"
        };

        // Nested loop creates every combination of rank and suit
        // This produces 52 total cards
        for (String r : ranks) {
            for (String s : suits) {
                cards.add(new Card(r, s));
            }
        }

        // Randomizes the order of the cards so the game is fair
        Collections.shuffle(cards);
    }

    // Draws one card from the deck (like taking the top card)
    public Card draw() {

        // If the deck is empty, rebuild and reshuffle it
        if (cards.isEmpty()) reset();

        // Remove and return the last card in the list
        return cards.remove(cards.size() - 1);
    }

    // Returns how many cards are left in the deck
    public int size() {
        return cards.size();
    }
}