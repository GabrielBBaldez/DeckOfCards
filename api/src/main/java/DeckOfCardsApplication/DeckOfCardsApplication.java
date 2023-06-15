package DeckOfCardsApplication;

import DeckOfCardsApplication.services.DeckOfCardsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import java.util.Scanner;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class DeckOfCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeckOfCardsApplication.class, args);
		DeckOfCardsService deckOfCardsService = new DeckOfCardsService();
		Scanner scanner = new Scanner(System.in);
		boolean jogarNovamente = true;

		while (jogarNovamente) {
			deckOfCardsService.playDeckOfCards();

			System.out.print("Deseja jogar novamente? (S/N): ");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("N")) {
				jogarNovamente = false;
			}
		}
		System.out.println("Obrigado por jogar!");
	}
}
