package com.github.nemesismate.gigtrial;

import com.github.nemesismate.gigtrial.logic.Game;
import com.github.nemesismate.gigtrial.logic.config.ConfigFromConsole;
import com.github.nemesismate.gigtrial.logic.config.GameConfig;
import com.github.nemesismate.gigtrial.logic.config.MatchMode;

public class Main {

    public static void main(String[] args) {

        GameConfig gameConfig = new ConfigFromConsole().gather();

        if (gameConfig.getMatchMode() == MatchMode.REMOTE) {
            ServerMain.main(args);
        }

        new Game(gameConfig).start();

        if (gameConfig.getMatchMode() == MatchMode.REMOTE) {
            ServerMain.getServerContext().close();
        }


    }

}
