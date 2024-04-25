package com.ursancristian.squadmatch.service;

import com.ursancristian.squadmatch.dto.LobbyDTO;
import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import com.ursancristian.squadmatch.repository.LocationRepository;
import com.ursancristian.squadmatch.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LobbyService {

    private final LobbyRepository lobbyRepository;

    private final UserRepository userRepository;

    private final LocationRepository locationRepository;

    public Lobby createLobby(LobbyDTO lobbyDto, String username) {

        User creator = userRepository.findByUsername(username);

        if (creator == null) {
            throw new UsernameNotFoundException("Invalid creator username");
        }

        Lobby lobby = new Lobby();
        lobby.setTitle(lobbyDto.getTitle());
        lobby.setMaxPlayers(lobbyDto.getMaxPlayers());
        lobby.setCreator(creator);
        lobby.setScheduledTime(lobbyDto.getScheduledTime());
        lobby.setLocation(locationRepository.findById(lobbyDto.getLocationId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid location id"))
        );

        lobby.getTeam1().add(creator);

        return lobbyRepository.save(lobby);
    }

    public Lobby joinLobby(int lobbyId, String username) {
        Lobby lobby = lobbyRepository.findById(lobbyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lobby id"));

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }


        if (lobby.getTeam1().contains(user) || lobby.getTeam2().contains(user)) {
            throw new IllegalArgumentException("User has already joined this lobby");
        }


        if (lobby.getTeam1().size() + lobby.getTeam2().size() >= lobby.getMaxPlayers()) {
//            lobby.setIsOpen(false);
            throw new IllegalArgumentException("Lobby is full");
        }


        if (lobby.getTeam1().size() <= lobby.getTeam2().size()) {
            lobby.getTeam1().add(user);
        } else {
            lobby.getTeam2().add(user);
        }

        return lobbyRepository.save(lobby);
    }

    public Lobby leaveLobby(int lobbyId, String username) {
        Lobby lobby = lobbyRepository.findById(lobbyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lobby id"));

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }

        if (!lobby.getTeam1().contains(user) && !lobby.getTeam2().contains(user)) {
            throw new IllegalArgumentException("User is not in this lobby");
        }

        if (lobby.getTeam1().contains(user)) {
            lobby.getTeam1().remove(user);
        } else {
            lobby.getTeam2().remove(user);
        }

        return lobbyRepository.save(lobby);
    }
}
