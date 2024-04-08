package com.ursancristian.squadmatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue
    private int id;

    private String stadium;
    private String address;

    private String imageUrl;

    @OneToMany(mappedBy = "location")
    private List<Lobby> lobbies;

}
