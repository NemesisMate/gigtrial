package com.github.nemesismate.gigtrial.logic.output;

import com.github.nemesismate.gigtrial.logic.match.Element;
import com.github.nemesismate.gigtrial.logic.match.MatchContext;
import com.github.nemesismate.gigtrial.logic.match.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Writer;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class WriterOutputterTest {

    WriterOutputter writerOutputter;

    @Mock
    Writer writer;

    private MatchContext givenMatchContext;

    @Before
    public void setup() throws Exception {
        willReturn(writer).given(writer).append(any());
        writerOutputter = spy(new WriterOutputter(writer));
    }

    @Test
    public void out() throws Exception {
        givenAnyMatchContext();

        whenOut();

        thenTheWriteIsPerformed();
    }

    private void givenAnyMatchContext() {
        givenMatchContext = spy(new MatchContext(10, mock(Player.class), mock(Player.class)));
        MatchContext.RoundData roundData = spy(givenMatchContext.new RoundData());

        willReturn(Element.PAPER).given(roundData).getFirstPlayerChoice();
        willReturn(Element.PAPER).given(roundData).getSecondPlayerChoice();
        willReturn(Collections.singletonList(roundData)).given(givenMatchContext).getFinishedRounds();
    }

    private void whenOut() throws Exception {
        writerOutputter.out(givenMatchContext);
    }

    private void thenTheWriteIsPerformed() throws Exception {
        then(writer).should().flush();
    }

}