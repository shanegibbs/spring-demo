package com.gibbsdevops.springdemo.web.controllers;

import com.gibbsdevops.springdemo.model.Thing;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);

        ResponseEntity<Thing> thing = restTemplate.getForEntity("http://localhost:8081/things/100", Thing.class);

        return "home";
    }

}
