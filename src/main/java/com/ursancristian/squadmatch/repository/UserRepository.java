package com.ursancristian.squadmatch.repository;

import com.ursancristian.squadmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findAllByOrderByWinsDesc();

}
