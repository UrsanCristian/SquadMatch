package com.ursancristian.squadmatch.service;

import com.ursancristian.squadmatch.dto.LobbyDTO;
import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import com.ursancristian.squadmatch.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LobbyService {

    private final LobbyRepository lobbyRepository;

    private final LocationRepository locationRepository;

    public Lobby createLobby(LobbyDTO lobbyDto) {
        Lobby lobby = new Lobby();
        lobby.setTitle(lobbyDto.getTitle());
        lobby.setMaxPlayers(lobbyDto.getMaxPlayers());
        lobby.setScheduledTime(lobbyDto.getScheduledTime());
        lobby.setLocation(locationRepository.findById(lobbyDto.getLocationId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid location id"))
        );
        return lobbyRepository.save(lobby);
    }
}
