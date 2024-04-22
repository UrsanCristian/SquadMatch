package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.dto.LobbyDTO;
import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.model.Location;
import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import com.ursancristian.squadmatch.repository.LocationRepository;
import com.ursancristian.squadmatch.repository.UserRepository;
import com.ursancristian.squadmatch.service.LobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LobbyController {

    private final LobbyRepository lobbyRepository;
    private final LobbyService lobbyService;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @GetMapping("/matches")
    public String lobbyList(Model model) {
        List<Lobby> lobbies = lobbyRepository.findAll();
        model.addAttribute("title", "Matches");
        model.addAttribute("lobbies", lobbies);
        return "lobby_list";
    }

    @GetMapping("/matches/{id}")
    public String lobbyDetails(Model model, @PathVariable int id, Principal principal) {
        Lobby lobby = lobbyRepository.findById(id).orElse(null);
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("title", "Match Details");
        model.addAttribute("lobby", lobby);
        model.addAttribute("user", user);
        return "lobby_details";
    }

    @GetMapping("/matches/create-lobby")
    public String createLobbyPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        List<Location> locations = locationRepository.findAll();

        model.addAttribute("title", "Create Lobby");
        model.addAttribute("lobby", new LobbyDTO());
        model.addAttribute("locations", locations);

        return "lobby_create";
    }

//    @PostMapping("/matches/create-lobby")
//    public String createLobbySubmit(@ModelAttribute LobbyDTO lobbyDto) {
//        Lobby lobby = lobbyService.createLobby(lobbyDto);
//        return "redirect:/matches";
//    }

    @PostMapping("/matches/create-lobby")
    public String createLobbySubmit(@ModelAttribute LobbyDTO lobbyDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User creator = userRepository.findByUsername(username);

        if (creator == null) {
            throw new UsernameNotFoundException("User not found");
        }

        lobbyDto.setCreator(creator);

        Lobby lobby = lobbyService.createLobby(lobbyDto, username);

        return "redirect:/matches";
    }

    @PostMapping("/matches/join/{lobbyId}")
    public String joinLobby(@PathVariable int lobbyId, Principal principal) {
        String username = principal.getName();
        lobbyService.joinLobby(lobbyId, username);
        return "redirect:/matches/{lobbyId}";
    }

    @PostMapping("/matches/leave/{lobbyId}")
    public String leaveLobby(@PathVariable int lobbyId, Principal principal) {
        String username = principal.getName();
        lobbyService.leaveLobby(lobbyId, username);
        return "redirect:/matches";
    }

}
