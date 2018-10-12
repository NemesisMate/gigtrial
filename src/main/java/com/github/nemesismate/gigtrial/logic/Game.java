package com.github.nemesismate.gigtrial.logic;

import com.github.nemesismate.gigtrial.logic.config.GameConfig;
import com.github.nemesismate.gigtrial.logic.match.Contexts;
import com.github.nemesismate.gigtrial.logic.match.Match;
import com.github.nemesismate.gigtrial.logic.match.MatchContext;
import com.github.nemesismate.gigtrial.logic.output.Outputter;
import com.github.nemesismate.gigtrial.logic.output.Outputters;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class Game {

    @Getter
    private final GameConfig gameConfig;

    public void start() {
        MatchContext matchContext = createMatchContext();
        Match match = createMatch(matchContext);
        Outputter outputter = createOutputter();

        completeMatch(match);

        outputResults(match, outputter);
    }

    public void completeMatch(@NonNull Match match) {
        while(match.hasMoreRounds()) {
            match.nextRound();
        }
    }

    public void outputResults(@NonNull Match match, @NonNull Outputter outputter) {
        try {
            outputter.out(match.getMatchContext());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    MatchContext createMatchContext() {
        return Contexts.from(gameConfig.getMatchMode());
    }

    Match createMatch(@NonNull MatchContext context) {
        return new Match(context);
    }

    Outputter createOutputter() {
        try {
            return Outputters.from(gameConfig.getOutputMode());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

}
