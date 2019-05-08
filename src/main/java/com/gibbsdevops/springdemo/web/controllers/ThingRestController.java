package com.gibbsdevops.springdemo.web.controllers;

import com.gibbsdevops.springdemo.model.Thing;
import com.gibbsdevops.springdemo.repo.ThingRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private ThingRepository thingRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody
    Thing getThing(@PathVariable Long id) throws InterruptedException {
        try {
            Thing saved = thingRepository.save(new Thing(null, "One " + new Random().nextInt()));
            System.out.println(saved);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rabbitTemplate.convertAndSend("fanout.exchange", "", "hello");

        return thingRepository.findById(id).get();
    }

}
