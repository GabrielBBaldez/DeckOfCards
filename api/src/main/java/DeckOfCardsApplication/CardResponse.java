package DeckOfCardsApplication;

import java.util.List;

public class CardResponse {
    private List<Card> cards;
    private int remaining;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
