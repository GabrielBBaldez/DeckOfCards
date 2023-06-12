package DeckOfCardsApplication;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class DeckOfCardsApplication {

	public static void main(String[] args) {

		DeckOfCardsService deckOfCardsService = new DeckOfCardsService();

		String deckId = deckOfCardsService.createDeck();
		System.out.println("Baralho criado! ID: " + deckId);

		// Tirar 5 cartas do baralho
		int numCardsToDraw = 5;
		CardResponse cardResponse = deckOfCardsService.drawCards(deckId, numCardsToDraw);

		System.out.println("Cartas tiradas do baralho:");
		for (Card card : cardResponse.getCards()) {
			System.out.println("Carta: " + card.getValue() + " de " + card.getSuit());
		}

		int remainingCards = cardResponse.getRemaining();
		System.out.println("Cartas restantes no baralho: " + remainingCards);

	}

}
