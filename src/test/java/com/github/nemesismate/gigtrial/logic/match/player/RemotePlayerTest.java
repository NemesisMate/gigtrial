package com.github.nemesismate.gigtrial.logic.match.player;

import com.github.nemesismate.gigtrial.logic.match.Element;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

public class RemotePlayerTest {

    RestTemplate restTemplate;
    RemotePlayer remotePlayer;

    private Element givenServerChosenElement;
    private Element returnedPlayerChosenElement;

    @Before
    public void setup() {
        remotePlayer = new RemotePlayer("RemotePlayer");
        restTemplate = mock(RestTemplate.class);
        remotePlayer.restTemplate = restTemplate;
    }

    @Test
    public void getNextChoice() {
        givenRemoteServerRespondsAnyElement();

        whenGettingTheNextChoice();

        thenThePlayerChoiceIsTheRemoteServerOne();
    }

    private void givenRemoteServerRespondsAnyElement() {
        givenServerChosenElement = Element.PAPER;
        willReturn(givenServerChosenElement).given(restTemplate).getForObject(anyString(), same(Element.class));
    }

    private void whenGettingTheNextChoice() {
        returnedPlayerChosenElement = remotePlayer.getNextChoice();
    }

    private void thenThePlayerChoiceIsTheRemoteServerOne() {
        then(restTemplate).should().getForObject(anyString(), same(Element.class));

        assertNotNull(givenServerChosenElement);
        assertEquals(givenServerChosenElement, returnedPlayerChosenElement);
    }
}