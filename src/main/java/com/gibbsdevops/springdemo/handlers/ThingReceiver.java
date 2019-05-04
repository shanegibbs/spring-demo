package com.gibbsdevops.springdemo.handlers;


import com.gibbsdevops.springdemo.model.Thing;
import org.springframework.stereotype.Component;

@Component
public class ThingReceiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Thing thing) {
        System.out.println("Received <" + thing.getName() + ">");
    }

}
