package com.github.nemesismate.gigtrial.logic.match;

import com.github.nemesismate.gigtrial.logic.match.MatchContext.RoundData;
import com.github.nemesismate.gigtrial.logic.match.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class MatchContextTest {

    MatchContext matchContext;

    Player firstPlayer;
    Player secondPlayer;
    int rounds;

    private Player givenPlayer;

    private RoundData givenRoundData;

    private Boolean returnedPlayerValidation;

    private Element givenChoice;
    private Player givenWinner;

    private int givenPlayerTotalWins;
    private int returnedTotalWins;

    @Before
    public void setup() {
        rounds = 10;

        firstPlayer = mock(Player.class);
        secondPlayer = mock(Player.class);

        matchContext = spy(new MatchContext(rounds, firstPlayer, secondPlayer));
    }

    @Test
    public void addFinishedRound() {
        givenAnyRoundData();

        whenAddFinishedRound();

        thenTheRoundDataIsAddedToTheContext();

    }

    @Test
    public void getTotalWinsFirstPlayer() {
        givenFirstPlayer();
        givenSomeFinishedRounds();

        whenGetTotalWinsForFirstPlayer();

        thenCalculatedTotalsIsCorrect();
    }

    @Test
    public void getTotalWinsSecondPlayer() {
        givenSecondPlayer();
        givenSomeFinishedRounds();

        whenGetTotalWinsForSecondPlayer();

        thenCalculatedTotalsIsCorrect();
    }

    @Test
    public void getTotalDraws() {
        givenSecondPlayer();
        givenSomeFinishedRounds();

        whenGetTotalWinsForSecondPlayer();

        thenCalculatedTotalsIsCorrect();
    }

    @Test
    public void isPlayer1WithCorrectPlayer() {
        givenFirstPlayer();

        whenIsFirstPlayer();

        thenThePlayerIsTheCorrectOne();
    }

    @Test
    public void isPlayer1WithWrongPlayer() {
        givenSecondPlayer();

        whenIsFirstPlayer();

        thenThePlayerIsTheWrongOne();
    }

    @Test
    public void isPlayer2WithCorrectPlayer() {
        givenSecondPlayer();

        whenIsSecondPlayer();

        thenThePlayerIsTheCorrectOne();
    }

    @Test
    public void isPlayer2WithWrongPlayer() {
        givenFirstPlayer();

        whenIsSecondPlayer();

        thenThePlayerIsTheWrongOne();
    }

    @Test
    public void getWinnerFromRoundDataWhenWinnerIsTheFirstPlayer() {
        givenAnyRoundData();
        givenTheFirstPlayerHasTheWinningChoice();

        whenGetWinner();

        thenTheWinnerIsTheFirstPlayer();
    }

    @Test
    public void getWinnerFromRoundDataWhenWinnerIsTheSecondPlayer() {
        givenAnyRoundData();
        givenTheSecondPlayerHasTheWinningChoice();

        whenGetWinner();

        thenTheWinnerIsTheSecondPlayer();
    }

    @Test
    public void getWinnerFromRoundDataWhenWinnerIsDraw() {
        givenAnyRoundData();
        givenNoPlayerHasTheWinningChoice();

        whenGetWinner();

        thenThereIsNoWinner();
    }

    @Test
    public void setRoundDataChoiceWithFirstPlayer() {
        givenAnyRoundData();
        givenFirstPlayer();
        givenAnyChoice();

        whenSettingGivenPlayerChoice();

        thenTheRoundDataChoiceIsTheGivenOneForTheFirstPlayer();

    }

    @Test
    public void setRoundDataChoiceWithSecondPlayer() {
        givenAnyRoundData();
        givenSecondPlayer();
        givenAnyChoice();

        whenSettingGivenPlayerChoice();

        thenTheRoundDataChoiceIsTheGivenOneForTheSecondPlayer();
    }

    @Test
    public void setRoundFirstPlayerChoice() {
        givenAnyRoundData();
        givenAnyChoice();

        whenSettingFirstPlayerChoice();

        thenTheRoundDataChoiceIsTheGivenOneForTheFirstPlayer();
    }


    @Test
    public void setRoundSecondPlayerChoice() {
        givenAnyRoundData();
        givenAnyChoice();

        whenSettingSecondPlayerChoice();

        thenTheRoundDataChoiceIsTheGivenOneForTheSecondPlayer();
    }

    private void givenAnyChoice() {
        givenChoice = Element.SCISSORS;
    }

    private void givenFirstPlayer() {
        givenPlayer = firstPlayer;
    }

    private void givenSecondPlayer() {
        givenPlayer = secondPlayer;
    }

    private void givenAnyRoundData() {
        givenRoundData = spy(matchContext.new RoundData());
    }

    private void givenSomeFinishedRounds() {
        List<RoundData> roundsData = IntStream.range(0, 7)
                .mapToObj(i -> mock(RoundData.class))
                .collect(Collectors.toList());

        Player other = mock(Player.class);

        IntStream.of(0, 1, 3, 6)
                .forEach(i -> willReturn(other).given(roundsData.get(i)).getWinner());

        givenPlayerTotalWins = 3;
        IntStream.of(2, 4, 5)
                .forEach(i -> willReturn(givenPlayer).given(roundsData.get(i)).getWinner());

        willReturn(roundsData).given(matchContext).getFinishedRounds();
    }

    private void givenTheFirstPlayerHasTheWinningChoice() {
        givenRoundDataFirstChoice(Element.ROCK);
        givenRoundDataSecondChoice(Element.SCISSORS);
    }

    private void givenTheSecondPlayerHasTheWinningChoice() {
        givenRoundDataFirstChoice(Element.SCISSORS);
        givenRoundDataSecondChoice(Element.ROCK);
    }

    private void givenNoPlayerHasTheWinningChoice() {
        givenRoundDataFirstChoice(Element.ROCK);
        givenRoundDataSecondChoice(Element.ROCK);
    }

    private void givenRoundDataFirstChoice(Element firstChoice) {
        willReturn(firstChoice).given(givenRoundData).getFirstPlayerChoice();
    }

    private void givenRoundDataSecondChoice(Element secondChoice) {
        willReturn(secondChoice).given(givenRoundData).getSecondPlayerChoice();
    }

    private void whenIsFirstPlayer() {
        returnedPlayerValidation = matchContext.isFirstPlayer(givenPlayer);
    }

    private void whenIsSecondPlayer() {
        returnedPlayerValidation = matchContext.isSecondPlayer(givenPlayer);
    }

    private void whenSettingGivenPlayerChoice() {
        givenRoundData.setChoice(givenPlayer, givenChoice);
    }

    private void whenSettingFirstPlayerChoice() {
        givenRoundData.setFirstPlayerChoice(givenChoice);
    }

    private void whenSettingSecondPlayerChoice() {
        givenRoundData.setSecondPlayerChoice(givenChoice);
    }

    private void whenGetWinner() {
        givenWinner = givenRoundData.getWinner();
    }

    private void whenAddFinishedRound() {
        matchContext.addFinishedRound(givenRoundData);
    }

    private void whenGetTotalWinsForFirstPlayer() {
        returnedTotalWins = matchContext.getTotalWinsFirstPlayer();
    }

    private void whenGetTotalWinsForSecondPlayer() {
        returnedTotalWins = matchContext.getTotalWinsSecondPlayer();
    }

    private void thenCalculatedTotalsIsCorrect() {
        assertNotEquals(givenPlayerTotalWins, 0);
        assertEquals(givenPlayerTotalWins, returnedTotalWins);
    }

    private void thenTheRoundDataIsAddedToTheContext() {
        assertTrue(matchContext.getFinishedRounds().contains(givenRoundData));
    }


    private void thenTheRoundDataChoiceIsTheGivenOneForTheFirstPlayer() {
        assertNotNull(givenChoice);
        assertEquals(givenChoice, givenRoundData.getFirstPlayerChoice());
    }

    private void thenTheRoundDataChoiceIsTheGivenOneForTheSecondPlayer() {
        assertNotNull(givenChoice);
        assertEquals(givenChoice, givenRoundData.getSecondPlayerChoice());
    }

    private void thenThePlayerIsTheCorrectOne() {
        assertTrue(returnedPlayerValidation);
    }

    private void thenThePlayerIsTheWrongOne() {
        assertNotNull(returnedPlayerValidation);
        assertFalse(returnedPlayerValidation);
    }

    private void thenTheWinnerIsTheFirstPlayer() {
        assertNotNull(firstPlayer);
        assertEquals(firstPlayer, givenWinner);
    }

    private void thenTheWinnerIsTheSecondPlayer() {
        assertNotNull(secondPlayer);
        assertEquals(secondPlayer, givenWinner);
    }

    private void thenThereIsNoWinner() {
        assertNotNull(givenWinner);
        assertEquals(Player.DRAW, givenWinner);
    }

}