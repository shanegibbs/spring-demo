package com.gibbsdevops.springdemo.web.controllers;

import com.gibbsdevops.springdemo.model.Thing;
import com.gibbsdevops.springdemo.repo.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("things")
public class ThingRestController {

    @Autowired
    ThingRepository thingRepository;

    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody
    Thing getThing(@PathVariable Long id) throws InterruptedException {
        try {
            thingRepository.save(new Thing(null, "One " + new Random().nextInt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thingRepository.findById(id).get();
    }

}
