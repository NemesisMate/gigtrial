package com.github.nemesismate.gigtrial.logic.match.player;

import com.github.nemesismate.gigtrial.logic.match.Element;

public interface Player {

    Player DRAW = new Player() {
        @Override
        public String getName() {
            return "Draw";
        }

        @Override
        public Element getNextChoice() {
            throw new UnsupportedOperationException("The DRAW player can't make choices");
        }
    };

    String getName();

    Element getNextChoice();

}
