package com.example.server.controller;

import com.example.server.model.Greeting;
import com.example.server.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate broker;

    @PostMapping("/greeting")
    public Greeting greeting(@RequestBody HelloMessage helloMessage) {
        broker.convertAndSend("/topic/greetings", new Greeting("Hello, " + helloMessage.getName() + "!"));
        return new Greeting("Hello, " + helloMessage.getName() + "!");
    }
}
