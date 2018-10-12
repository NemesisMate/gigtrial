package com.github.nemesismate.gigtrial.logic.match.player;

import com.github.nemesismate.gigtrial.logic.ai.AiBrain;
import com.github.nemesismate.gigtrial.logic.match.Element;
import lombok.Builder;
import lombok.NonNull;

public class AiPlayer extends AbstractPlayer {

    @NonNull
    private AiBrain aiBrain;

    @Builder
    public AiPlayer(String name, AiBrain aiBrain) {
        super(name);
        this.aiBrain = aiBrain;
    }

    @Override
    public Element getNextChoice() {
        return aiBrain.thinkNextMovement();
    }
    
}
