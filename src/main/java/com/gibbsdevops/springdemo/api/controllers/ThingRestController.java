package com.gibbsdevops.springdemo.api.controllers;

import com.gibbsdevops.springdemo.model.Thing;
import com.gibbsdevops.springdemo.api.repo.ThingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOG = LoggerFactory.getLogger(ThingRestController.class);

    @Autowired
    private ThingRepository thingRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(path = "/")
    public String getRoot() {
        return "things";
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody
    Thing getThing(@PathVariable Long id) throws InterruptedException {
        try {
            Thing saved = thingRepository.save(new Thing(null, "One " + new Random().nextInt()));
            System.out.println(saved);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOG.warn("Start rabbit");
        rabbitTemplate.convertAndSend("fanout.exchange", "", "hello");
        LOG.warn("End rabbit");

        return thingRepository.findById(id).get();
    }

}
