package com.github.nemesismate.gigtrial.logic.ai;

import com.github.nemesismate.gigtrial.logic.match.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class RandomBrainTest {

    private RandomBrain brain;

    private Element returnedElement;

    @Before
    public void setup() {
        brain = new RandomBrain();
    }

    @Test
    public void thinkNextMovement() {
        whenTheBrainThinksNextMovement();

        thenTheSelectionIsAnyElement();
    }

    private void whenTheBrainThinksNextMovement() {
        returnedElement = brain.thinkNextMovement();
    }

    private void thenTheSelectionIsAnyElement() {
        assertNotNull(returnedElement);
    }
}