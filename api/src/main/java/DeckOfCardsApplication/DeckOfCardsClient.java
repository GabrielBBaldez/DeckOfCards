package DeckOfCardsApplication;

import feign.Param;
import feign.RequestLine;

public interface DeckOfCardsClient {

    @RequestLine("GET /api/deck/new/")
    DeckIdResponse createDeck();

    @RequestLine("GET /api/deck/{deckId}/draw/?count={numCards}")
    CardResponse drawCards(@Param("deckId") String deckId, @Param("numCards") int numCards);
}