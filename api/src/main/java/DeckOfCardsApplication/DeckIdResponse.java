package DeckOfCardsApplication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeckIdResponse {
    @JsonProperty("deck_id")
    private String deckId;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}