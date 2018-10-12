package com.github.nemesismate.gigtrial.logic.match;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static com.github.nemesismate.gigtrial.logic.match.Element.PAPER;
import static com.github.nemesismate.gigtrial.logic.match.Element.ROCK;
import static com.github.nemesismate.gigtrial.logic.match.Element.SCISSORS;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ElementTest {

    @Parameters
    public static Element[][] data() {
        return new Element[][] {
//              FIRST       SECOND      WINNER
//              ----------------------------------
                { ROCK,      PAPER,      PAPER      },
                { PAPER,     ROCK,       PAPER      },
                { ROCK,      SCISSORS,   ROCK       },
                { SCISSORS,  ROCK,       ROCK       },
                { PAPER,     SCISSORS,   SCISSORS   },
                { SCISSORS,  PAPER,      SCISSORS   },
        };
    }

    @Parameter(0)
    public Element first;

    @Parameter(1)
    public Element second;

    @Parameter(2)
    public Element winner;

    @Test
    public void calculateWinner() {
        assertEquals(winner, first.calculateWinner(second));
    }
}