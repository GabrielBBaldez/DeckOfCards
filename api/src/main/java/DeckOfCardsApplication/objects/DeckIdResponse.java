package DeckOfCardsApplication.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeckIdResponse {
    @JsonProperty("deck_id")
    private String deckId;
}