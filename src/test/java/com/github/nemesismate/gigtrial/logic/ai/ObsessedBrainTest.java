package com.github.nemesismate.gigtrial.logic.ai;

import com.github.nemesismate.gigtrial.logic.match.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class ObsessedBrainTest {

    ObsessedBrain brain;

    private Element givenElement;

    private Element returnedElement;

    @Before
    public void setup() {
        givenElement = Element.PAPER;
        brain = new ObsessedBrain(givenElement);
    }

    @Test
    public void thinkNextMovement() {
        whenTheBrainThinksNextMovement();

        thenTheSelectionIsThatElement();
    }

    private void whenTheBrainThinksNextMovement() {
        returnedElement = brain.thinkNextMovement();
    }

    private void thenTheSelectionIsThatElement() {
        assertNotNull(givenElement);
        assertEquals(givenElement, returnedElement);
    }
}