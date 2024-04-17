package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.dto.UserDTO;
import com.ursancristian.squadmatch.exceptions.UserAlreadyExistException;
import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login-submit")
    public String loginSubmit() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("title", "Register");
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/register-submit")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO userDto,
            HttpServletRequest request,
            Errors errors) {

        ModelAndView mav = new ModelAndView();

        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("redirect:/login", "user", userDto);
    }
}
