import java.util.*;

public class Hand {

    // Stores all cards currently in this hand
    private List<Card> cards = new ArrayList<>();

    // Adds a new card to the hand
    public void add(Card c) {
        cards.add(c);
    }

    // Removes and returns the last card (used for splitting)
    public Card removeLast() {
        return cards.remove(cards.size() - 1);
    }

    // Returns the dealer's first visible card
    public Card getFirstCard() {
        return cards.get(0);
    }

    // Returns a specific card by index
    public Card getCard(int index) {
        return cards.get(index);
    }

    // Returns how many cards are in this hand
    public int size() {
        return cards.size();
    }

    // Calculates the total blackjack value of the hand
    public int getValue() {
        int sum = 0;
        int aces = 0;

        // Add up card values and count aces
        for (Card c : cards) {
            sum += c.getValue();
            if (c.getRank().equals("A")) aces++;
        }

        // If over 21 and we have aces, convert Ace from 11 to 1
        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }

        return sum;
    }

    // Checks for a "soft 17" (Ace counted as 11 in a 17 total)
    public boolean hasSoft17() {
        return getValue() == 17 &&
               cards.stream().anyMatch(c -> c.getRank().equals("A"));
    }

    // Checks if the hand is a natural blackjack (2 cards totaling 21)
    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    // Returns a read-only list of cards (prevents outside modification)
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    // Controls how the hand prints to the console
    @Override
    public String toString() {
        return cards + " = " + getValue();
    }
}