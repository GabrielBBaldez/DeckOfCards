package DeckOfCardsApplication.enums;

public enum DeckOfCardsApiEndpoints {
    CREATE_DECK("https://deckofcardsapi.com/api/deck/new/"),
    DRAW_CARDS("https://deckofcardsapi.com/api/deck/{deckId}/draw/?count={numCards}"),
    CARDS_REMAINING("https://deckofcardsapi.com/api/deck/{deckId}"),
    DECK_SHUFFLE("https://deckofcardsapi.com/api/deck/{deck_id}/shuffle/");

    private final String url;

    DeckOfCardsApiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}