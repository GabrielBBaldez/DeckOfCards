package DeckOfCardsApplication.objects;

import lombok.Data;

import java.util.List;

@Data
public class Hand {
    private List<Card> cards;
    private Player player;
}