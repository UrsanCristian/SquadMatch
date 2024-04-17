package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
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
