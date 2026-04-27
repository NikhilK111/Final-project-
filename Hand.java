import java.util.*;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void add(Card c) {
        cards.add(c);
    }

    public Card removeLast() {
        return cards.remove(cards.size() - 1);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public int getValue() {
        int sum = 0;
        int aces = 0;

        for (Card c : cards) {
            sum += c.getValue();
            if (c.getRank().equals("A")) aces++;
        }

        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }

        return sum;
    }

    public boolean hasSoft17() {
        int sum = 0;
        int aces = 0;

        for (Card c : cards) {
            sum += c.getValue();
            if (c.getRank().equals("A")) aces++;
        }

        return sum == 17 && aces > 0;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}