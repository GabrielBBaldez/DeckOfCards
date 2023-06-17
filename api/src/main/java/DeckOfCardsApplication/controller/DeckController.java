package DeckOfCardsApplication.controller;

import DeckOfCardsApplication.services.DeckOfCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckOfCardsService deckOfCardsService;

    @Autowired
    public DeckController(DeckOfCardsService deckOfCardsService) {
        this.deckOfCardsService = deckOfCardsService;
    }

    @PostMapping
    public ResponseEntity<String> playDeckOfCards() {
        try {
            String result = deckOfCardsService.playDeckOfCards();
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
