package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LobbyController {

    private final LobbyRepository lobbyRepository;

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
}
