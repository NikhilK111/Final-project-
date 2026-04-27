import java.util.*;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void add(Card c) {
        cards.add(c);
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

        return aces > 0 && sum == 17;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}