package DeckOfCardsApplication.exception;

public class DeckCreationException extends RuntimeException {

    public DeckCreationException() {
        super("Falha ao criar o baralho.");
    }

    public DeckCreationException(String message) {
        super(message);
    }

    public DeckCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
