package com.ursancristian.squadmatch.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@NoArgsConstructor
public class AuthenticationController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login-submit")
    public String loginSubmit() {
        return "redirect:/";
    }
}
