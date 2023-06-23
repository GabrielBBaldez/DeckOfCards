package DeckOfCardsApplication.services;

import DeckOfCardsApplication.model.Game;
import DeckOfCardsApplication.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }


}