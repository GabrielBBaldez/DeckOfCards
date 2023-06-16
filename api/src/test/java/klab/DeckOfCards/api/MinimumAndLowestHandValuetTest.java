package klab.DeckOfCards.api;

import DeckOfCardsApplication.model.Hand;
import DeckOfCardsApplication.model.Player;
import DeckOfCardsApplication.services.DeckOfCardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MinimumAndLowestHandValuetTest {
    @Test
    public void MinimumAndLowestHandValuetTest() {
        DeckOfCardsService deckOfCardsService = new DeckOfCardsService();
        String deckId = deckOfCardsService.createDeck();

        // Chamar o método createPlayersAndFiveHands()
        List<Hand> hands = deckOfCardsService.createPlayersAndFiveHands(deckId);

        // Verificar o valor máximo e mínimo possível para uma mão
        for (Hand hand : hands) {
            int sum = hand.getTotalSum();
            Assertions.assertTrue(sum >= 6, "A soma da mão deve ser maior ou igual á  6");
            Assertions.assertTrue(sum <= 64, "A soma da mão deve ser menor ou igual á 64");
        }
    }
}


