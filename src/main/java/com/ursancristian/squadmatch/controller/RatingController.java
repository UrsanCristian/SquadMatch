package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.model.Rating;
import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping
    public String ratingList(Model model) {
        ratingService.getAllRatingsDone();
        List<Lobby> lobbies = ratingService.getUserLobbies();
        model.addAttribute("lobbies", lobbies);
        return "rating_list";

    }

    @GetMapping("/{lobby_id}")
    public String lobbyRatingDetails(Model model, @PathVariable("lobby_id") int lobbyId) {
        List<Rating> lobbyRatings = ratingService.getLobbyRatings(lobbyId);
        List<User> nonRatedUsers = ratingService.getNonRatedUsers(lobbyId);
        model.addAttribute("lobbyRatings", lobbyRatings);
        model.addAttribute("nonRatedUsers", nonRatedUsers);
        model.addAttribute("lobbyId", lobbyId);

        return "rating_details";
    }

    @PostMapping("/{lobby_id}")
    public String rateUser(@PathVariable("lobby_id") int lobbyId,
                           @RequestParam(value = "userIds[]") Integer[] userIds,
                           @RequestParam(value = "behavior[]") Integer[] behavior,
                           @RequestParam(value = "skills[]") Integer[] skills) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ratingService.rateUser(lobbyId, userIds, behavior, skills);


        return "redirect:/ratings/" + lobbyId;
    }

}
