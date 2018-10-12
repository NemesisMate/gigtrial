package com.github.nemesismate.gigtrial.web.controller;

import com.github.nemesismate.gigtrial.logic.ai.RandomBrain;
import com.github.nemesismate.gigtrial.logic.match.Element;
import com.github.nemesismate.gigtrial.logic.match.player.AiPlayer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private AiPlayer aiPlayer = AiPlayer.builder().name("RemotePlayer").aiBrain(new RandomBrain()).build();

    @GetMapping("/choice")
    public Element getPlayerChoice() {
        return aiPlayer.getNextChoice();
    }

}
