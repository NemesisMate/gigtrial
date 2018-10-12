package com.github.nemesismate.gigtrial.logic.match.player;

import com.github.nemesismate.gigtrial.logic.ai.AiBrain;
import com.github.nemesismate.gigtrial.logic.match.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AiPlayerTest {

    AiPlayer aiPlayer;
    AiBrain givenAiBrain;

    private Element givenBrainChosenElement;
    private Element returnedPlayerChosenElement;

    @Before
    public void setup() {
        givenAiBrain = mock(AiBrain.class);
        aiPlayer = new AiPlayer("AiBrainyPlayer", givenAiBrain);
    }

    @Test
    public void getNextChoice() {
        givenBrainChosesAnyElement();

        whenGettingTheNextChoiceByThePlayer();

        thenThePlayerChoiceIsItBrainsOne();
    }

    private void givenBrainChosesAnyElement() {
        givenBrainChosenElement = Element.SCISSORS;
        willReturn(givenBrainChosenElement).given(givenAiBrain).thinkNextMovement();
    }

    private void whenGettingTheNextChoiceByThePlayer() {
        returnedPlayerChosenElement = aiPlayer.getNextChoice();
    }

    private void thenThePlayerChoiceIsItBrainsOne() {
        then(givenAiBrain).should().thinkNextMovement();

        assertNotNull(givenBrainChosenElement);
        assertEquals(givenBrainChosenElement, returnedPlayerChosenElement);
    }
}