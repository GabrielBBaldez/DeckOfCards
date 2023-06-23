package DeckOfCardsApplication.services;

import DeckOfCardsApplication.model.Player;
import DeckOfCardsApplication.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        return playerRepository.save(player);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

}