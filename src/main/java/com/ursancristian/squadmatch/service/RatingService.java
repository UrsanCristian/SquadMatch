package com.ursancristian.squadmatch.service;

import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.model.Rating;
import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.repository.LobbyRepository;
import com.ursancristian.squadmatch.repository.RatingRepository;
import com.ursancristian.squadmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final LobbyRepository lobbyRepository;

    public List<Rating> getAllRatingsDone() {
        List<Rating> result = ratingRepository.findAll();
//        System.out.println(result);
        return result;
    }

    // return list of Lobby and User
    public List<Lobby> getUserLobbies() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        List<Lobby> userLobbies = lobbyRepository.findAllByCreatorOrTeam1ContainsOrTeam2Contains(user, List.of(user), List.of(user));
//        System.out.println(userLobbies);
        return userLobbies;
    }

    public List<User> getRatedUsers(int lobbyId) {
        List<Rating> lobbyRatings = getLobbyRatings(lobbyId);
        List<User> ratedUsers = lobbyRatings.stream().map(Rating::getPlayerTo).toList();
//        System.out.println(ratedUsers);
        return ratedUsers;
    }

    public List<Rating> getLobbyRatings(int lobbyId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        Lobby lobby = lobbyRepository.findById(lobbyId).orElseThrow(() -> new IllegalArgumentException("Invalid lobby id"));
        List<Rating> lobbyRatings = ratingRepository.findByLobby_IdAndPlayerFrom(lobby.getId(), user);
        return lobbyRatings;
    }

    public List<User> getNonRatedUsers(int lobbyId) {
        List<Integer> ratedUserIds = new ArrayList<>(getRatedUsers(lobbyId).stream()
                .map(User::getId).toList());
        Lobby lobby = lobbyRepository.findById(lobbyId).orElseThrow(() -> new IllegalArgumentException("Invalid lobby id"));
        System.out.println("ratedUserIds=" + ratedUserIds);
        List<User> allPlayers = lobby.getTeam1();
        allPlayers.addAll(lobby.getTeam2());
        System.out.println("allPlayers=" + allPlayers);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userMe = (User) principal;
        ratedUserIds.add(userMe.getId());
        System.out.println("userMe=" + userMe);
        System.out.println("ratedUserIds=" + ratedUserIds);

        List<User> nonRatedUsers = allPlayers.stream()
                .filter(user -> !ratedUserIds.contains(user.getId()))
                .toList();
        System.out.println("nonRatedUsers=" + nonRatedUsers);
        return nonRatedUsers;
    }

//    public List<User> getMissingRatings(int lobbyId) {
//        Lobby lobby = lobbyRepository.findById(lobbyId).orElseThrow(() -> new IllegalArgumentException("Invalid lobby id"));
//        List<User> missingRatings = userRepository.findAllByLobbiesNotContains(lobby);
//        System.out.println(missingRatings);
//        return missingRatings;
//    }

    public void rateUser(int lobbyId, Integer[] userIds, Integer[] behavior, Integer[] skills) {
        User playerFrom = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Lobby lobby = lobbyRepository.findById(lobbyId).orElseThrow(() -> new IllegalArgumentException("Invalid lobby id"));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("lobbyId: " + lobbyId);
        System.out.println("userIds: " + Arrays.toString(userIds));
        System.out.println("behavior: " + Arrays.toString(behavior));
        System.out.println("skills: " + Arrays.toString(skills));

        for (int i = 0; i < userIds.length; i++) {
            int playerToId = userIds[i];
            int behaviorRating = behavior[i];
            int skillsRating = skills[i];
            int playerFromId = playerFrom.getId();
            System.out.println("create rating for playerFromId: " + playerFromId + " playerToId: " + playerToId + " lobbyId: " + lobbyId + " behavior: " + behaviorRating + " skills: " + skillsRating);
            User playerTo = userRepository.findById(playerToId).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

            Rating rating = Rating.builder()
                    .lobby(lobby)
                    .playerFrom(playerFrom)
                    .playerTo(playerTo)
                    .behavior(behaviorRating)
                    .skills(skillsRating)
                    .build();
            ratingRepository.save(rating);

        }
    }
}

