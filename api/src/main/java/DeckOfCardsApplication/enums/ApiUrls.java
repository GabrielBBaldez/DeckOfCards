package DeckOfCardsApplication.enums;

public enum ApiUrls {
    CREATE_DECK("https://deckofcardsapi.com/api/deck/new/"),
    DRAW_CARDS("https://deckofcardsapi.com/api/deck/{deckId}/draw/?count={numCards}"),
    CARDS_REMAINING("https://deckofcardsapi.com/api/deck/{deckId}"),
    DECK_SHUFFLE("https://deckofcardsapi.com/api/deck/{deck_id}/shuffle/");

    private final String url;

    ApiUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}