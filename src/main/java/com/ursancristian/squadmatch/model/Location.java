package com.ursancristian.squadmatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
