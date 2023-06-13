package klab.DeckOfCards.api;

import DeckOfCardsApplication.objects.Hand;
import DeckOfCardsApplication.services.DeckOfCardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrawCardsTest {
    @Test
    public void testGetFiveCards() {
        DeckOfCardsService deckOfCardsService = new DeckOfCardsService();

        String deckId = deckOfCardsService.createDeck();
        int numCardsToDraw = 5;
        Hand hand = deckOfCardsService.drawCards(deckId, numCardsToDraw);

        // Verifique se foram recebidas 5 cartas
        Assertions.assertEquals(5, hand.getCards().size());
    }
}
