package com.sdinternational.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/game/state")
    public String state() {
        return "Game engine available - console gameplay version running";
    }
}