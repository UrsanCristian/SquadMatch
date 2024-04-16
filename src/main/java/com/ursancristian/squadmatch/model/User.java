package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String email;

    private Boolean isActive = true;

    private Boolean isAdmin = false;

    private String pictureUrl = "https://i.imgur.com/afUEk7n.png";

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
