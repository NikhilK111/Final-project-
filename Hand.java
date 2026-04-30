import java.util.*;

public class Hand {

    // This list stores all cards currently in the player's or dealer's hand
    private List<Card> cards = new ArrayList<>();

    // Adds a new card to the hand
    public void add(Card c) {
        cards.add(c);
    }

    // Removes and returns the last card in the hand
    // Used mainly for splitting hands in Blackjack
    public Card removeLast() {
        return cards.remove(cards.size() - 1);
    }

    // Returns the dealer’s first card (used because one card is hidden in Blackjack)
    public Card getFirstCard() {
        return cards.get(0);
    }

    // Returns a specific card from the hand
    public Card getCard(int index) {
        return cards.get(index);
    }

    // Returns how many cards are currently in the hand
    public int size() {
        return cards.size();
    }

    // Calculates the total value of the hand in Blackjack
    public int getValue() {

        int sum = 0;
        int aces = 0;

        // Add up all card values
        for (Card c : cards) {
            sum += c.getValue();

            // Count how many Aces are in the hand
            if (c.getRank().equals("A")) aces++;
        }

        // If total is over 21, convert Aces from 11 → 1 as needed
        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }

        return sum;
    }

    // Checks if the hand is a "soft 17"
    // Meaning: total is 17 AND contains an Ace counted as 11
    public boolean hasSoft17() {
        return getValue() == 17 &&
               cards.stream().anyMatch(c -> c.getRank().equals("A"));
    }

    // Checks if the hand is a natural Blackjack (2 cards = 21)
    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    // Controls how the hand prints in the console
    // Example: [A of ♠, 10 of ♥] = 21
    @Override
    public String toString() {
        return cards + " = " + getValue();
    }
}