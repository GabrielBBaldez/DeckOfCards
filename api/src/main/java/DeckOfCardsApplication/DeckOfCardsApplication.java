package DeckOfCardsApplication;

import DeckOfCardsApplication.objects.Card;
import DeckOfCardsApplication.objects.Hand;
import DeckOfCardsApplication.services.DeckOfCardsService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class DeckOfCardsApplication {

	public static void main(String[] args) {

		DeckOfCardsService deckOfCardsService = new DeckOfCardsService();

		String deckId = deckOfCardsService.createDeck();
		System.out.println("Baralho criado! ID: " + deckId);


		/*int numCardsToDraw = 5;
		Hand hand = deckOfCardsService.drawCards(deckId, numCardsToDraw);

		System.out.println("Cartas tiradas do baralho:");
		for (Card card : hand.getCards()) {
			System.out.println("Carta: " + card.getValue() + " de " + card.getSuit());
		} */

		List<Hand> hands = deckOfCardsService.createFiveHands(deckId);

		for (int i = 0; i < hands.size(); i++){
			System.out.print("Jogador " + (i +1 )+ "= [");
			 hands.get(i).getCards().forEach(card -> System.out.print(card.getValue()+ ","));
			System.out.print("]");
			System.out.println();
		}


		int remainingCards = deckOfCardsService.CardsRemaining(deckId);
		System.out.println("Cartas restantes no baralho: " + remainingCards);

	}

}
