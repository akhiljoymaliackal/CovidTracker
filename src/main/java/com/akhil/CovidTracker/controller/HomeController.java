package com.akhil.CovidTracker.controller;

import com.akhil.CovidTracker.service.CovidDataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CovidDataFetchService covidData;

    @Autowired
    public HomeController(CovidDataFetchService covidData) {
        this.covidData = covidData;
    }

    @GetMapping("/")
    public String home(Model model){
        int totalReportedCase = covidData.getCovidData().stream().mapToInt(s -> s.getCasesCount()).sum();
        model.addAttribute("covidData", covidData.getCovidData());
        model.addAttribute("totalReportedCase",totalReportedCase);
        return "home";
    }
}
