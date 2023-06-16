package DeckOfCardsApplication.repository;


import DeckOfCardsApplication.model.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayerRepository extends JpaRepository<GamePlayer, Integer> {

}