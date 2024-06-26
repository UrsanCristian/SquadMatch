package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", initialValue = 1, allocationSize = 1)
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

    private String role = "USER";

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
        double average = this.ratingsReceived.stream()
                .mapToInt(Rating::getBehavior)
                .average()
                .orElse(0f);

        DecimalFormat df = new DecimalFormat("#.##");

        return Double.valueOf(df.format(average));
    }

    public Double averageSkills() {
        double average = this.ratingsReceived.stream()
                .mapToInt(Rating::getSkills)
                .average()
                .orElse(0f);

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(average));
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

    public List<String> getRoles() {
        return List.of(this.role);
    }
}
