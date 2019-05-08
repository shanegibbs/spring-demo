package com.gibbsdevops.springdemo.web.controllers;

import com.gibbsdevops.springdemo.model.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/things")
    public String thingsPage(Model model) {
        model.addAttribute("appName", appName);
        ResponseEntity<Thing> thing = restTemplate.getForEntity("http://localhost:8082/things/1", Thing.class);
        return "home";
    }

}
