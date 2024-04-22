package com.ursancristian.squadmatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    @GetMapping
    public String ratingList(Model model) {
        return "rating_list";
    }
}
