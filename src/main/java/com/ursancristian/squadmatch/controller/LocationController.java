package com.ursancristian.squadmatch.controller;

import com.ursancristian.squadmatch.model.Location;
import com.ursancristian.squadmatch.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepository locationRepository;

    @GetMapping("/locations")
    public String locationList(Model model) {
        List<Location> locations = locationRepository.findAll();
        model.addAttribute("title", "Locations");
        model.addAttribute("locations", locations);
        return "location_list";
    }

    @GetMapping("/locations/{id}")
    public String locationDetails(Model model, @PathVariable int id) {
        Location location = locationRepository.findById(id).orElse(null);
        model.addAttribute("title", "Location Details");
        model.addAttribute("location", location);
        return "location_details";
    }
}
