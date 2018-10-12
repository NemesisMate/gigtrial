package com.github.nemesismate.gigtrial.logic.match.player;

import com.github.nemesismate.gigtrial.logic.match.Element;
import lombok.Builder;
import org.springframework.web.client.RestTemplate;

public class RemotePlayer extends AbstractPlayer {

    RestTemplate restTemplate = new RestTemplate();

    @Builder
    public RemotePlayer(String name) {
        super(name);
    }

    @Override
    public Element getNextChoice() {
        return restTemplate.getForObject("http://localhost:8080/player/choice", Element.class);
    }

}
