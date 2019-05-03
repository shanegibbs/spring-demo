package com.gibbsdevops.springdemo.web.controllers;

import com.gibbsdevops.springdemo.model.Thing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("things")
public class ThingRestController {

    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody
    Thing getThing(@PathVariable int id) throws InterruptedException {
        Thread.sleep(id);
        return new Thing(id);
    }

}
