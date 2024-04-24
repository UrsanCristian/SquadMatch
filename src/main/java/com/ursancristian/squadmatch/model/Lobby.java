package com.ursancristian.squadmatch.model;

import com.ursancristian.squadmatch.enumerations.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "lobby_id_seq")
    @SequenceGenerator(name = "lobby_id_seq", sequenceName = "lobby_id_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    private String title;

    private int maxPlayers;

    @ManyToOne
    private Location location;

    @ManyToMany
    private List<User> team1;

    @ManyToMany
    private List<User> team2;

    private Boolean isOpen = true;

    private LocalDateTime scheduledTime;

    @Setter
    @Enumerated(EnumType.STRING)
    private Team winner;

    @OneToMany(mappedBy = "lobby")
    private List<Rating> ratings;
}



