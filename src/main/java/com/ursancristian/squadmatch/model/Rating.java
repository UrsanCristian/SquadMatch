package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue
    private int id;

    private String playerFrom;

    private String playerTo;
    @ManyToOne
    private Lobby lobby;

    private int behavior;

    private int skills;
}
