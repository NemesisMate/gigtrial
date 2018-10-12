package com.github.nemesismate.gigtrial.logic.match;

import com.github.nemesismate.gigtrial.logic.ai.ObsessedBrain;
import com.github.nemesismate.gigtrial.logic.ai.RandomBrain;
import com.github.nemesismate.gigtrial.logic.config.MatchMode;
import com.github.nemesismate.gigtrial.logic.match.player.AiPlayer;
import com.github.nemesismate.gigtrial.logic.match.player.Player;
import com.github.nemesismate.gigtrial.logic.match.player.RemotePlayer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Contexts {

    public static MatchContext from(MatchMode matchMode) {
        Player firstPlayer = AiPlayer.builder()
                .name("FirstPlayer")
                .aiBrain(new RandomBrain())
                .build();

        String secondName = "SecondPlayer";
        Player secondPlayer;

        switch (matchMode) {
            case FAIR:
                secondPlayer = AiPlayer.builder().name(secondName).aiBrain(new RandomBrain()).build();
                break;
            case UNFAIR:
                secondPlayer = AiPlayer.builder().name(secondName).aiBrain(new ObsessedBrain(Element.ROCK)).build();
                break;
            case REMOTE:
                secondPlayer = RemotePlayer.builder().name(secondName).build();
                break;
            default:
                throw new IllegalArgumentException("Unsupported mode: " + matchMode);
        }

        return new MatchContext(10, firstPlayer, secondPlayer);
    }

}
