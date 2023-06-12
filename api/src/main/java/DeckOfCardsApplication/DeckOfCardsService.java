package DeckOfCardsApplication;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
    @Service
    public class DeckOfCardsService {

        private final RestTemplate restTemplate;

        public DeckOfCardsService() {
            this.restTemplate = new RestTemplate();
        }

        public String createDeck() {
            String url = "https://deckofcardsapi.com/api/deck/new/";
            DeckIdResponse deckIdResponse = restTemplate.getForObject(url, DeckIdResponse.class);
            if (deckIdResponse != null) {
                return deckIdResponse.getDeckId();
            } else {
                throw new RuntimeException("Falha ao criar o baralho.");
            }
        }

        public CardResponse drawCards(String deckId, int numCards) {
            String url = "https://deckofcardsapi.com/api/deck/{deckId}/draw/?count={numCards}";
            return restTemplate.getForObject(url, CardResponse.class, deckId, numCards);
        }
    }

