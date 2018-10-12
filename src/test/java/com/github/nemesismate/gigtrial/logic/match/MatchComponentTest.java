package com.github.nemesismate.gigtrial.logic.match;

import com.github.nemesismate.gigtrial.logic.match.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;

/**
 * This one is a component test. ¡¡¡Not UNIT!!!
 */
@RunWith(MockitoJUnitRunner.class)
public class MatchComponentTest {

    Match match;

    @Mock
    Player firstPlayer;

    @Mock
    Player secondPlayer;

    private int givenChoiceGotTimes = 1;
    private Exception returnedException;

    @Before
    public void setup() {
        MatchContext matchContext = new MatchContext(2, firstPlayer, secondPlayer);
        match = new Match(matchContext);

        willReturn(Element.SCISSORS).given(firstPlayer).getNextChoice();
        willReturn(Element.ROCK).given(secondPlayer).getNextChoice();
    }

    @Test
    public void roundIterationIsPerformed() {
        whenNextRound();

        thenTheChoiceIsGotForBothPlayers();

        thenThereAreRoundsLeft();


        whenNextRound();

        thenTheChoiceIsGotForBothPlayers();

        thenThereIsNoRoundLeft();


        whenNextRound();

        thenWeGetAnException();
    }



    private void whenNextRound() {
        try {
            match.nextRound();
        } catch(Exception e) {
            returnedException = e;
        }
    }

    private void thenTheChoiceIsGotForBothPlayers() {
        then(firstPlayer).should(times(givenChoiceGotTimes)).getNextChoice();
        then(secondPlayer).should(times(givenChoiceGotTimes)).getNextChoice();

        givenChoiceGotTimes++;
    }

    private void thenWeGetAnException() {
        assertNotNull(returnedException);
    }

    private void thenThereIsNoRoundLeft() {
        assertFalse(match.hasMoreRounds());
    }

    private void thenThereAreRoundsLeft() {
        assertTrue(match.hasMoreRounds());
    }
}