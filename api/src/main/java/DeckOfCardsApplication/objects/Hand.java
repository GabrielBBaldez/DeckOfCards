package DeckOfCardsApplication.objects;

import DeckOfCardsApplication.objects.Card;
import lombok.Data;

import java.util.List;

@Data
public class Hand {
    private List<Card> cards;
}