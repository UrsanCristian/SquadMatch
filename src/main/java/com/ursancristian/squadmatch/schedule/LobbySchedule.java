package com.ursancristian.squadmatch.schedule;

import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LobbySchedule {

    private final LobbyRepository lobbyRepository;


    @Scheduled(fixedRate = 3600000)
    public void cleanupLobbies() {
        LocalDateTime now = LocalDateTime.now();

        List<Lobby> lobbies = lobbyRepository.findAll();
        for (Lobby lobby : lobbies) {
            if (lobby.getIsOpen() && lobby.getScheduledTime().isBefore(now)) {
                lobbyRepository.delete(lobby);
            }
        }
    }

}
