package com.ursancristian.squadmatch.repository;

import com.ursancristian.squadmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
