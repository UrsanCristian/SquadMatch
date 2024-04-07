package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    private String firstName;

    private String lastName;
    @NotNull
    private String email;

    private Boolean is_active = true;

    private Boolean is_admin = false;

    private String pictureUrl;

    private int wins = 0;

    private int losses = 0;

    private int ratingBehavior;

    private int ratingSkills;

    @ManyToMany(mappedBy = "team1")
    private List<Lobby> lobbyTeams1;

    @ManyToMany(mappedBy = "team2")
    private List<Lobby> lobbyTeams2;
}
