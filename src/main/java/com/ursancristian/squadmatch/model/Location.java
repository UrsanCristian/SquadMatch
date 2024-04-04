package com.ursancristian.squadmatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue
    private int id;

    private String stadium;
    private String address;

    private String imageUrl;

}
