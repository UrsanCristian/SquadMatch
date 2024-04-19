package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.dto.LobbyDTO;
import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.model.Location;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import com.ursancristian.squadmatch.repository.LocationRepository;
import com.ursancristian.squadmatch.service.LobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LobbyController {

    private final LobbyRepository lobbyRepository;
    private final LobbyService lobbyService;
    private final LocationRepository locationRepository;

    @GetMapping("/matches")
    public String lobbyList(Model model) {
        List<Lobby> lobbies = lobbyRepository.findAll();
        model.addAttribute("title", "Matches");
        model.addAttribute("lobbies", lobbies);
        return "lobby_list";
    }

    @GetMapping("/matches/{id}")
    public String lobbyDetails(Model model, @PathVariable int id) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        model.addAttribute("title", "Match Details");
        model.addAttribute("lobby", lobby);
        return "lobby_details";
    }

    @GetMapping("/matches/create-lobby")
    public String createLobbyPage(Model model) {
        List<Location> locations = locationRepository.findAll();

        model.addAttribute("title", "Create Lobby");
        model.addAttribute("lobby", new LobbyDTO());
        model.addAttribute("locations", locations);

        return "lobby_create";
    }

    @PostMapping("/matches/create-lobby")
    public String createLobbySubmit(@ModelAttribute LobbyDTO lobbyDto) {
        Lobby lobby = lobbyService.createLobby(lobbyDto);
        return "redirect:/matches";
    }

}
