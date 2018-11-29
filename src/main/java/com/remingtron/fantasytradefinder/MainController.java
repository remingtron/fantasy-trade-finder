package com.remingtron.fantasytradefinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/")
    public String index() {
        Player dummyPlayer = new Player();
        dummyPlayer.firstName = "Bob";
        dummyPlayer.lastName = "Jones";
        dummyPlayer.id = UUID.randomUUID().toString();
        dummyPlayer.tradeValue = 17;
        playerRepository.save(dummyPlayer);

        return "Hello world!\n" + "There are " + playerRepository.count() + " players in the database";
    }

}
