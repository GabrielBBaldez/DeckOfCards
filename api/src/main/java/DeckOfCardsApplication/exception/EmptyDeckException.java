package DeckOfCardsApplication.exception;

public class EmptyDeckException extends RuntimeException {

    public EmptyDeckException() {
        super("O baralho não foi encontrado ou está vazio. Não é possível pegar mais cartas.");
    }

    public EmptyDeckException(String message) {
        super(message);
    }

    public EmptyDeckException(String message, Throwable cause) {
        super(message, cause);
    }
}