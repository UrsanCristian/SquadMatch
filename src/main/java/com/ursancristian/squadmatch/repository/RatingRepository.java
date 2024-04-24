package com.ursancristian.squadmatch.repository;

import com.ursancristian.squadmatch.model.Rating;
import com.ursancristian.squadmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findByLobby_IdAndPlayerFrom(int id, User playerFrom);

}
