package klab.DeckOfCards.api;

import DeckOfCardsApplication.model.Hand;
import DeckOfCardsApplication.model.Player;
import DeckOfCardsApplication.services.DeckOfCardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class testCreatePlayersAndFiveHands {
    @Test
    public void testCreatePlayersAndFiveHands() {
        DeckOfCardsService deckOfCardsService = new DeckOfCardsService();
        String deckId = deckOfCardsService.createDeck();

        // Chamar o método createPlayersAndFiveHands()
        List<Hand> hands = deckOfCardsService.createPlayersAndFiveHands(deckId);

        // Verificar se a lista de mãos não é nula e possui 5 elementos
        Assertions.assertNotNull(hands);
        Assertions.assertEquals(5, hands.size());

        // Verificar se cada mão possui exatamente 5 cartas
        for (Hand hand : hands) {
            Assertions.assertNotNull(hand.getCards());
            Assertions.assertEquals(5, hand.getCards().size());
        }

        // Verificar se cada mão possui um jogador com nome válido
        for (int i = 0; i < hands.size(); i++) {
            Hand hand = hands.get(i);
            Player player = hand.getPlayer();
            Assertions.assertNotNull(player);
            Assertions.assertEquals("Jogador: " + (i + 1), player.getName());
        }
    }
}