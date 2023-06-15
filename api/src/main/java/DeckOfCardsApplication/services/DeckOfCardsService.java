package DeckOfCardsApplication.services;

import DeckOfCardsApplication.enums.ApiUrls;
import DeckOfCardsApplication.objects.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
    public class DeckOfCardsService {

        private final RestTemplate restTemplate;

        public DeckOfCardsService() {
            this.restTemplate = new RestTemplate();
        }

        public String createDeck() {
            String newDeckUrl = ApiUrls.CREATE_DECK.getUrl();
            DeckIdResponse deckIdResponse = restTemplate.getForObject(newDeckUrl, DeckIdResponse.class);
            if (deckIdResponse != null) {
                String shuffleDeckUrl = ApiUrls.DECK_SHUFFLE.getUrl();
                restTemplate.getForObject(shuffleDeckUrl, String.class, deckIdResponse.getDeckId());
                return deckIdResponse.getDeckId();
            } else {
                throw new RuntimeException("Falha ao criar o baralho.");
            }
        }

        public Hand drawCards(String deckId, int numCards) {
            String url = ApiUrls.DRAW_CARDS.getUrl();
            Hand response = restTemplate.getForObject(url, Hand.class, deckId, numCards);

            // Mapear valores das cartas para valores numéricos
            Map<String, String> cardValuesMap = new HashMap<>();
            cardValuesMap.put("ACE", "1");
            cardValuesMap.put("JACK", "11");
            cardValuesMap.put("QUEEN", "12");
            cardValuesMap.put("KING", "13");

            // Verificar cada carta e converter o valor conforme o mapeamento
            for (Card card : response.getCards()) {
                String value = card.getValue();
                if (cardValuesMap.containsKey(value)) {
                    card.setValue(cardValuesMap.get(value));
                }
            }

            return response;
        }
        public int CardsRemaining(String deckId){
            String url = ApiUrls.CARDS_REMAINING.getUrl();
            CardsRemaining cardsRemaining= restTemplate.getForObject(url, CardsRemaining.class, deckId);
            return cardsRemaining.getRemaining();
        }

        public List<Hand> createPlayersAndFiveHands(String deckId){
            List<Hand> hands = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Hand hand = drawCards(deckId,5);
                hands.add(hand);
                Player player = new Player();
                player.setName("Jogador: " + String.valueOf(i + 1) );
                hand.setPlayer(player);
            }
            return hands;
        }

    public void playDeckOfCards() {


        String deckId = createDeck();
        List<Hand> hands = createPlayersAndFiveHands(deckId);
        int highestSum = Integer.MIN_VALUE;
        List<String> highestSumPlayers = new ArrayList<>();

        for (Hand handList : hands) {
            int sum = 0;
            for (Card cards : handList.getCards()) {
                int cardValue = Integer.parseInt(cards.getValue());
                sum += cardValue;
            }
            if (sum > highestSum) {
                highestSum = sum;
                highestSumPlayers.clear();
                highestSumPlayers.add(handList.getPlayer().getName());
            } else if (sum == highestSum) {
                highestSumPlayers.add(handList.getPlayer().getName());
            }
        }
        StringBuilder result = new StringBuilder();
        if (highestSumPlayers.size() == 1) {
            result.append("Vencedor é ").append(highestSumPlayers.get(0)).append(" com ").append(highestSum).append(" pontos");
        } else {
            result.append("Empate entre os jogadores: ");
            for (String players : highestSumPlayers) {
                result.append(players).append(" ");
            }
            result.append("com ").append(highestSum).append(" pontos");
        }
        System.out.println(result);
    }
}

