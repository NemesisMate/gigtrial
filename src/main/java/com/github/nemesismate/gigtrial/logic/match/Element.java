package com.github.nemesismate.gigtrial.logic.match;

import lombok.NonNull;

public enum Element {

    ROCK,
    PAPER,
    SCISSORS;

    public Element calculateWinner(@NonNull Element otherElement) {
        if(this == otherElement) {
            return null;
        }

        switch(this) {
            case ROCK:
                return otherElement == Element.SCISSORS ? this : otherElement;
            case PAPER:
                return otherElement == Element.ROCK ? this : otherElement;
            case SCISSORS:
                return otherElement == Element.PAPER ? this : otherElement;
            default:
                throw new UnsupportedOperationException("Element not supported: " + this);
        }
    }

}
