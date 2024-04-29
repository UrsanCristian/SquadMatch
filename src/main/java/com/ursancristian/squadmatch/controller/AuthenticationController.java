package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.dto.UserDTO;
import com.ursancristian.squadmatch.exceptions.UserAlreadyExistException;
import com.ursancristian.squadmatch.model.User;
import com.ursancristian.squadmatch.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("errorMessage") != null) {
            model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }

        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login-submit")
    public String loginSubmit() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        System.out.println("loading registration page");
        UserDTO userDto = new UserDTO();
        model.addAttribute("title", "Register");
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/register-submit")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO userDto,
            BindingResult result,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("registration");
            mav.addObject("message", result.getAllErrors().get(0).getDefaultMessage());
        } else {
            try {
                User registered = userService.registerNewUserAccount(userDto);
                mav.setViewName("redirect:/login");
            } catch (UserAlreadyExistException uaeEx) {
                mav.addObject("message", "An account for that username/email already exists.");
                mav.setViewName("registration");
            }
        }
        mav.addObject("user", userDto);
        return mav;
    }
}
