package com.ursancristian.squadmatch.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LobbyDTO {

    private String title;

    private int maxPlayers;

    private int locationId;

    private LocalDateTime scheduledTime;

}
