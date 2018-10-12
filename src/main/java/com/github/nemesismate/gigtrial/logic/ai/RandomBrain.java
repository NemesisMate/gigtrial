package com.github.nemesismate.gigtrial.logic.ai;

import com.github.nemesismate.gigtrial.logic.match.Element;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class RandomBrain implements AiBrain {

    private Random random = new Random();

    @Override
    public Element thinkNextMovement() {
        Element[] availableElements = Element.values();
        return availableElements[random.nextInt(availableElements.length)];
    }
}
