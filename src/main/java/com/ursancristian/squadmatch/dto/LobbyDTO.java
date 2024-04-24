package com.ursancristian.squadmatch.dto;

import com.ursancristian.squadmatch.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class LobbyDTO {

    private String title;

    private int maxPlayers;

    private User creator;

    private int locationId;

    private LocalDateTime scheduledTime;

}
