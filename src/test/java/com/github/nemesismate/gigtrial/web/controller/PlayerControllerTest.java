package com.github.nemesismate.gigtrial.web.controller;

import com.github.nemesismate.gigtrial.logic.match.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PlayerControllerTest {

    @Autowired
    private MockMvc mvc;

    private MockHttpServletResponse response;

    @Test
    public void getPlayerChoice() throws Exception {

        whenRequestChoiceToTheRemotePlayer();

        thenTheResponseIsAnyValidElement();

    }

    private void whenRequestChoiceToTheRemotePlayer() throws Exception {
        response = mvc.perform(get("/player/choice")).andReturn().getResponse();
    }

    private void thenTheResponseIsAnyValidElement() throws Exception {
        assertNotNull(Element.valueOf(response.getContentAsString().replaceAll("\"", "")));
    }
}