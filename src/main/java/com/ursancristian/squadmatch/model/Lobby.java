package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Lobby {
    @Id
    @GeneratedValue
    private int id;

    private int maxPlayers;

    @ManyToOne
    private Location location;

    @ManyToMany
    private List<User> team1;

    @ManyToMany
    private List<User> team2;

    private Boolean is_active;

    private LocalDateTime date;

    private String winner;

    @OneToMany(mappedBy = "lobby")
    private List<Rating> ratings;

}



