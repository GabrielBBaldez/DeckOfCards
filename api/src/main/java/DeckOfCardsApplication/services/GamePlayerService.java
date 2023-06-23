package DeckOfCardsApplication.services;

import DeckOfCardsApplication.model.Game;
import DeckOfCardsApplication.model.GamePlayer;
import DeckOfCardsApplication.model.Player;
import DeckOfCardsApplication.repository.GameplayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerService {

    @Autowired
    private GameplayerRepository gameplayerRepository;

    public GamePlayer saveGamePlayer(GamePlayer gamePlayer) {
        return gameplayerRepository.save(gamePlayer);
    }

    public GamePlayer createGamePlayer(Game game, Player player) {
        GamePlayer gamePlayer = new GamePlayer(game, player);
        return gameplayerRepository.save(gamePlayer);
    }

}