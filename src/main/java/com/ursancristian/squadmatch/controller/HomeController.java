package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.repository.LobbyRepository;
import com.ursancristian.squadmatch.repository.LocationRepository;
import com.ursancristian.squadmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LobbyRepository lobbyRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Squad Match");
        model.addAttribute("lobbiesCount", lobbyRepository.count());
        model.addAttribute("usersCount", userRepository.count());
        model.addAttribute("locationsCount", locationRepository.count());
        return "home";
    }
}
