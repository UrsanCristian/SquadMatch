package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
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

    private Boolean isActive = true;

    private Boolean isAdmin = false;

    private String pictureUrl;

    private int wins = 0;

    private int losses = 0;

    @ManyToMany(mappedBy = "team1")
    private List<Lobby> lobbyTeams1;

    @ManyToMany(mappedBy = "team2")
    private List<Lobby> lobbyTeams2;

    @OneToMany(mappedBy = "playerFrom")
    private List<Rating> ratingsSent;

    @OneToMany(mappedBy = "playerTo")
    private List<Rating> ratingsReceived;

    @OneToMany(mappedBy = "creator")
    private List<Lobby> lobbyCreator;

    public Double averageBehavior() {
        return this.ratingsReceived.stream()
                .mapToInt(Rating::getBehavior)
                .average()
                .orElse(0f);
    }

    public Double averageSkills() {
        return this.ratingsReceived.stream()
                .mapToInt(Rating::getSkills)
                .average()
                .orElse(0f);
    }


}
