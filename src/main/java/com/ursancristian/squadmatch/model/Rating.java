package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rating_id_seq")
    @SequenceGenerator(name = "rating_id_seq", sequenceName = "rating_id_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_from_id")
    private User playerFrom;

    @ManyToOne
    @JoinColumn(name = "player_to_id")
    private User playerTo;

    @ManyToOne
    private Lobby lobby;

    @Min(1)
    @Max(10)
    private int behavior;

    @Min(1)
    @Max(10)
    private int skills;
}
