package DeckOfCardsApplication.controller;

import DeckOfCardsApplication.model.Game;
import DeckOfCardsApplication.repository.GameRepository;
import DeckOfCardsApplication.services.DeckOfCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckOfCardsService deckOfCardsService;
    private final GameRepository gameRepository;

    @Autowired
    public DeckController(DeckOfCardsService deckOfCardsService, GameRepository gameRepository) {
        this.deckOfCardsService = deckOfCardsService;
        this.gameRepository = gameRepository;
    }

    @PostMapping
    public ResponseEntity<String> createDeck() {
        try {
            String resultado = deckOfCardsService.playDeckOfCards();
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
