package DeckOfCardsApplication.enums;

public enum ApiUrls {
    CREATE_CARDS("https://deckofcardsapi.com/api/deck/new/"),
    DRAW_CARDS("https://deckofcardsapi.com/api/deck/{deckId}/draw/?count={numCards}"),
    CARDS_REMAINING("https://deckofcardsapi.com/api/deck/{deckId}");

    private final String url;

    ApiUrls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}