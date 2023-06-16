package DeckOfCardsApplication.repository;

import DeckOfCardsApplication.model.Player;
import DeckOfCardsApplication.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WinnerRepository  extends JpaRepository<Winner, Integer>{
}