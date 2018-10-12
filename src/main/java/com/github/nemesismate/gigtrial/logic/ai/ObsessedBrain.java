package com.github.nemesismate.gigtrial.logic.ai;

import com.github.nemesismate.gigtrial.logic.match.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObsessedBrain implements AiBrain {

    private Element element;

    @Override
    public Element thinkNextMovement() {
        return element;
    }
}
