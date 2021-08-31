package com.example.Coronavirustracker.controllers;


import com.example.Coronavirustracker.models.Locationstats;
import com.example.Coronavirustracker.services.Coronavirudataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller



public class Homecontroller {

    @Autowired
    Coronavirudataservice coronavirusdataservice;

    @GetMapping("/")
    //Whenever there is a get mapping to slash ie root URL, return the home template ie HTML file,
    // This works because in pom.XML we have ThymeLeaf dependenceis
    public String home(Model model){
        List<Locationstats> allStats = coronavirusdataservice.getAllstats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatesttotalcases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffrompreviousday()).sum();
        model.addAttribute("locationstats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";

    }
}
