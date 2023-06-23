package DeckOfCardsApplication.services;

import DeckOfCardsApplication.model.Game;
import DeckOfCardsApplication.model.Player;
import DeckOfCardsApplication.model.Winner;
import DeckOfCardsApplication.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerService {

    @Autowired
    private WinnerRepository winnerRepository;

    public Winner saveWinner(Winner winner) {
        return winnerRepository.save(winner);
    }

    public Winner createWinner(Game game, Player player, int totalSum) {
        Winner winner = new Winner(game, player, totalSum);
        return winnerRepository.save(winner);
    }

}
