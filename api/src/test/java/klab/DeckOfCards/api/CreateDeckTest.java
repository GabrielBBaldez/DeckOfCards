package klab.DeckOfCards.api;

import DeckOfCardsApplication.services.DeckOfCardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateDeckTest {

    @Test
    public void testGetFiveCards() {
        DeckOfCardsService deckOfCardsService = new DeckOfCardsService();

        String deckId = deckOfCardsService.createDeck();
        int remainingCards = deckOfCardsService.CardsRemaining(deckId);

        Assertions.assertNotNull(deckId);  // Verifica se a lista de cartas não é nula
        Assertions.assertEquals(52, remainingCards);  // Verifica se a lista contém exatamente 52 cartas
    }
    }

