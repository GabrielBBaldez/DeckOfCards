package DeckOfCardsApplication.services;

import DeckOfCardsApplication.enums.DeckOfCardsApiEndpoints;
import DeckOfCardsApplication.exception.DeckCreationException;
import DeckOfCardsApplication.exception.EmptyDeckException;
import DeckOfCardsApplication.model.*;
import DeckOfCardsApplication.repository.GameRepository;
import DeckOfCardsApplication.repository.GameplayerRepository;
import DeckOfCardsApplication.repository.PlayerRepository;
import DeckOfCardsApplication.repository.WinnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeckOfCardsService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameplayerRepository gameplayerRepository;
    @Autowired
    private WinnerRepository winnerRepository;

    private RestTemplate restTemplate;

    public DeckOfCardsService() {
        this.restTemplate = new RestTemplate();
    }

    public String createDeck() {
        String newDeckUrl = DeckOfCardsApiEndpoints.CREATE_DECK.getUrl();
        DeckIdResponse deckIdResponse = restTemplate.getForObject(newDeckUrl, DeckIdResponse.class);
        if (deckIdResponse != null) {
            String shuffleDeckUrl = DeckOfCardsApiEndpoints.DECK_SHUFFLE.getUrl();
            restTemplate.getForObject(shuffleDeckUrl, String.class, deckIdResponse.getDeckId());
            return deckIdResponse.getDeckId();
        } else {
            throw new DeckCreationException();
        }
    }

    private Map<String, String> createCardValuesMap() {
        Map<String, String> cardValuesMap = new HashMap<>();
        cardValuesMap.put("ACE", "1");
        cardValuesMap.put("JACK", "11");
        cardValuesMap.put("QUEEN", "12");
        cardValuesMap.put("KING", "13");
        return cardValuesMap;
    }

    public Hand drawCards(String deckId, int numCards) {
        String url = DeckOfCardsApiEndpoints.DRAW_CARDS.getUrl();
        Hand response = restTemplate.getForObject(url, Hand.class, deckId, numCards);
        if (response == null ) {
            throw new EmptyDeckException();
        }
        int totalSum = 0;

        // Mapear valores das cartas para valores numéricos
        Map<String, String> cardValuesMap = createCardValuesMap();

        // Verificar cada carta e converter o valor conforme o mapeamento
        for (Card card : response.getCards()) {
            String value = card.getValue();
            if (cardValuesMap.containsKey(value)) {
                card.setValue(cardValuesMap.get(value));
            }
        }
        int sum = response.getCards().stream()
                .mapToInt(card -> Integer.parseInt(card.getValue()))
                .sum();
        response.setTotalSum(sum);

        return response;
    }

    public int CardsRemaining(String deckId) {
        String url = DeckOfCardsApiEndpoints.CARDS_REMAINING.getUrl();
        CardsRemaining cardsRemaining = restTemplate.getForObject(url, CardsRemaining.class, deckId);
        return cardsRemaining.getRemaining();
    }

    public List<Hand> createPlayersAndFiveHands(String deckId) {
        List<Hand> hands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Hand hand = drawCards(deckId, 5);
            hands.add(hand);
            Player player = new Player();
            player.setName("Jogador: " + String.valueOf(i + 1));
            hand.setPlayer(player);
        }
        return hands;
    }

    private void saveWinners(Game game, List<Hand> hands, List<String> highestSumPlayers) {
        for (Hand hand : hands) {
            if (highestSumPlayers.contains(hand.getPlayer().getName())) {
                Winner winner = new Winner(game, hand.getPlayer(), hand.getTotalSum());
                winnerRepository.save(winner);
            }
        }
    }

    private String buildResultString(List<String> highestSumPlayers, int highestSum) {
        StringBuilder result = new StringBuilder();

        if (highestSumPlayers.size() == 1) {
            result.append("Vencedor é ").append(highestSumPlayers.get(0)).append(" com ").append(highestSum).append(" pontos");
        } else {
            result.append("Empate entre os jogadores: ");
            for (String player : highestSumPlayers) {
                result.append(player).append(" ");
            }
            result.append("com ").append(highestSum).append(" pontos");
        }

        return result.toString();
    }

    @Transactional
    public String playDeckOfCards() {
        Game game = new Game(LocalDate.now());
        gameRepository.save(game);
        String deckId = createDeck();
        List<Hand> hands = createPlayersAndFiveHands(deckId);
        int highestSum = Integer.MIN_VALUE;
        List<String> highestSumPlayers = new ArrayList<>();

        for (Hand hand : hands) {
            Player player = hand.getPlayer();
            playerRepository.save(player);

            GamePlayer gamePlayer = new GamePlayer(game, player);
            gameplayerRepository.save(gamePlayer);

            int totalSum = hand.getCards().stream()
                    .mapToInt(card -> Integer.parseInt(card.getValue()))
                    .sum();

            hand.setTotalSum(totalSum);

            if (totalSum > highestSum) {
                highestSum = totalSum;
                highestSumPlayers.clear();
                highestSumPlayers.add(player.getName());
            } else if (totalSum == highestSum) {
                highestSumPlayers.add(player.getName());
            }
        }

        return buildResultString(highestSumPlayers,highestSum);
    }
}

