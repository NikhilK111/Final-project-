import java.util.*;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] suits = {"♠","♥","♦","♣"};

        for (String r : ranks) {
            for (String s : suits) {
                cards.add(new Card(r, s));
            }
        }

        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(cards.size() - 1);
    }
}