package com.ursancristian.squadmatch.repository;

import com.ursancristian.squadmatch.model.Lobby;
import com.ursancristian.squadmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LobbyRepository extends JpaRepository<Lobby, Integer> {
    List<Lobby> findAllByCreatorOrTeam1ContainsOrTeam2Contains(User creator, List<User> team1, List<User> team2);
}
