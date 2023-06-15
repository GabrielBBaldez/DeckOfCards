package DeckOfCardsApplication.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "player")
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}