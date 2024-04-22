package com.ursancristian.squadmatch.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "location_id_seq")
    @SequenceGenerator(name = "location_id_seq", sequenceName = "location_id_seq", initialValue = 1, allocationSize = 1)
    private int id;

    private String stadium;
    private String address;

    private String imageUrl;

    @OneToMany(mappedBy = "location")
    private List<Lobby> lobbies;

}
