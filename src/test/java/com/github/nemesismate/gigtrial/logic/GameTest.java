package com.github.nemesismate.gigtrial.logic;

import com.github.nemesismate.gigtrial.logic.config.GameConfig;
import com.github.nemesismate.gigtrial.logic.config.MatchMode;
import com.github.nemesismate.gigtrial.logic.config.OutputMode;
import com.github.nemesismate.gigtrial.logic.match.Match;
import com.github.nemesismate.gigtrial.logic.match.MatchContext;
import com.github.nemesismate.gigtrial.logic.output.Outputter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    GameConfig gameConfig;
    Game game;

    private MatchContext givenMatchContext;
    private Match givenMatch;
    private int givenRoundsAmount;
    private Outputter givenOutputter;

    @Before
    public void setup() {
        gameConfig = GameConfig.builder()
                .matchMode(MatchMode.FAIR)
                .outputMode(OutputMode.FILE)
                .build();

        game = spy(new Game(gameConfig));
    }

    @Test
    public void start() {
        givenAnyMatchContext();
        givenAnyMatch();
        givenAnyOuputter();

        whenStart();

        thenCompleteMatchIsCalled();
        thenOutputResultsIsCalled();
    }

    @Test
    public void completeMatch() {
        givenAnyMatch();
        givenAnyMatchRounds();

        whenCompleteMatch();

        thenAllRoundsArePerformed();
    }

    @Test
    public void outputResults() {
        givenAnyMatchContext();
        givenAnyMatch();
        givenAnyOuputter();

        whenOutputResults();

        thenTheOutputterPerformTheOutput();
    }


    private void givenAnyMatchRounds() {
        givenRoundsAmount = 3;
        willReturn(true, true, true, false)
                .given(givenMatch).hasMoreRounds();
    }

    private void givenAnyMatchContext() {
        givenMatchContext = mock(MatchContext.class);
        willReturn(givenMatchContext)
                .given(game).createMatchContext();
    }

    private void givenAnyMatch() {
        givenMatch = mock(Match.class);

        willReturn(givenMatch)
                .given(game).createMatch(givenMatchContext);

        willReturn(givenMatchContext)
                .given(givenMatch).getMatchContext();
    }

    private void givenAnyOuputter() {
        givenOutputter = mock(Outputter.class);

        willReturn(givenOutputter)
                .given(game).createOutputter();
    }


    private void whenStart() {
        game.start();
    }

    private void whenCompleteMatch() {
        game.completeMatch(givenMatch);
    }

    private void whenOutputResults() {
        game.outputResults(givenMatch, givenOutputter);
    }


    private void thenCompleteMatchIsCalled() {
        then(game).should().completeMatch(givenMatch);
    }

    private void thenOutputResultsIsCalled() {
        then(game).should().outputResults(givenMatch, givenOutputter);
    }

    private void thenAllRoundsArePerformed() {
        then(givenMatch).should(times(givenRoundsAmount)).nextRound();
    }

    private void thenTheOutputterPerformTheOutput() {
        try {
            then(givenOutputter).should().out(givenMatchContext);
        } catch(IOException e) {
            fail(e.getMessage());
        }
    }
}