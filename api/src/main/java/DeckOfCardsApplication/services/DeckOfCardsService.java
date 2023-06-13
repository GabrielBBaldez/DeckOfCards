package DeckOfCardsApplication.services;

import DeckOfCardsApplication.enums.ApiUrls;
import DeckOfCardsApplication.objects.Card;
import DeckOfCardsApplication.objects.CardsRemaining;
import DeckOfCardsApplication.objects.DeckIdResponse;
import DeckOfCardsApplication.objects.Hand;
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
            String url = ApiUrls.CREATE_CARDS.getUrl();
            DeckIdResponse deckIdResponse = restTemplate.getForObject(url, DeckIdResponse.class);
            if (deckIdResponse != null) {
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

        public List<Hand> createFiveHands(String deckId){
            List<Hand> hands = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Hand hand = drawCards(deckId,5);
                hands.add(hand);
            }
            return hands;
        }


    }

