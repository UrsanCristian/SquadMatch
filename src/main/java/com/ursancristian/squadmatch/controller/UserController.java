package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/players")
    public String userList(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("title", "Players");
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/players/{id}")
    public String userDetails(Model model, @PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("title", "Player Details");
        model.addAttribute("user", user);
        return "user_details";
    }
}
