package com.github.nemesismate.gigtrial.logic.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ConfigFromConsoleTest {

    @Spy
    ConfigFromConsole configFromConsole;

    private String givenSelection;
    private Class<? extends Enum> givenOptionType;
    private String givenOptionMessage;
    private String givenScannedString;
    private Enum givenOptionMode;

    private Enum returnedOptionMode;

    private MatchMode givenMatchMode;
    private OutputMode givenOutputMode;

    private MatchMode returnedMatchMode;
    private OutputMode returnedOutputMode;

    private GameConfig returnedConfig;

    @Before
    public void setup() {
        willDoNothing().given(configFromConsole).println(any());
    }

    @Test
    public void gather() {
        givenAnyReadedOutputMode();
        givenAnyReadedAnyMatchMode();

        whenGather();

        thenTheGameConfigContainsGivenMatchMode();
        thenTheGameConfigContainsGivenOuputMode();
    }

    @Test
    public void getNumberWithValidInput() {
        assertEquals(Integer.valueOf(7), configFromConsole.getNumber("7"));
    }

    @Test
    public void getNumberWithInvalidInput() {
        assertNull(configFromConsole.getNumber("Invalid"));
    }

    @Test
    public void readOutputDestination() {
        givenReadOptionLineReturnsAnyOutputMode();

        whenReadingOutputMode();

        thenTheReadOutputModeIsTheReadedOption();
    }

    @Test
    public void readMatchMode() {
        givenReadOptionLineReturnsAnyMatchMode();

        whenReadingMatchMode();

        thenTheReadMatchModeIsTheReadedOption();
    }

    @Test
    public void readOptionLineWithValidSelection() {
        givenAnyOptionType();
        givenParseOptionReturnsValidMode();

        whenTheOptionLineIsRead();

        thenTheReadOptionLineIsTheParsed();
    }

    @Test
    public void readOptionLineWithInvalidSelection() {
        givenAnyOptionType();
        givenParseOptionReturnsInvalidMode();

        whenTheOptionLineIsRead();

        thenTheReadOptionLineIsReRead();
    }

    @Test
    public void parseOptionWithInvalidSelectionByIndex() {
        givenAnyInvalidSelectionByIndex();

        whenParsingSelection();

        thenTheSelectedOptionIsntCorrectlyParsed();
    }

    @Test
    public void parseOptionWithInvalidSelectionByName() {
        givenAnyInvalidSelectionByName();

        whenParsingSelection();

        thenTheSelectedOptionIsntCorrectlyParsed();
    }


    @Test
    public void parseOptionWithSelectionByIndex() {
        givenAnySelectionByIndex();

        whenParsingSelection();

        thenTheSelectedOptionIsCorrectlyParsed();
    }

    @Test
    public void parseOptionWithSelectionByName() {
        givenAnySelectionByName();

        whenParsingSelection();

        thenTheSelectedOptionIsCorrectlyParsed();
    }


    private void givenAnyOptionType() {
        this.givenOptionType = OutputMode.class;
        this.givenOptionMessage = ConfigFromConsole.outputModeMessage;
        this.givenOptionMode = OutputMode.CONSOLE;
    }


    private void givenAnyInvalidSelectionByIndex() {
        givenSelection = "17";

        willReturn(17)
                .given(configFromConsole).getNumber(givenSelection);

        givenMatchMode(null);
    }

    private void givenAnyInvalidSelectionByName() {
        givenSelection = "INVALID";

        willReturn(null)
                .given(configFromConsole).getNumber(givenSelection);

        givenMatchMode(null);
    }

    private void givenAnySelectionByIndex() {
        givenSelection = "2";

        willReturn(2)
                .given(configFromConsole).getNumber(givenSelection);

        givenMatchMode(MatchMode.REMOTE);
    }

    private void givenAnySelectionByName() {
        givenSelection = "FAIR";

        willReturn(null)
                .given(configFromConsole).getNumber(givenSelection);

        givenMatchMode(MatchMode.FAIR);
    }

    private void givenMatchMode(MatchMode matchMode) {
        givenMatchMode = matchMode;
    }

    private void givenAnyMatchMode() {
        givenMatchMode(MatchMode.UNFAIR);
    }

    private void givenAnyOutputMode() {
        givenOutputMode = OutputMode.CONSOLE;
    }

    private void givenReadOptionLineReturnsAnyMatchMode() {
        givenAnyMatchMode();
        willReturn(givenMatchMode)
                .given(configFromConsole).readOptionLine(ConfigFromConsole.matchModeMessage, MatchMode.class);
    }

    private void givenReadOptionLineReturnsAnyOutputMode() {
        givenAnyOutputMode();
        willReturn(givenOutputMode)
                .given(configFromConsole).readOptionLine(ConfigFromConsole.outputModeMessage, OutputMode.class);
    }

    private void givenParseOptionReturnsValidMode() {
        givenScannedString();

        willReturn(givenOptionMode)
                .given(configFromConsole).parseOption(givenScannedString, givenOptionType);
    }

    private void givenParseOptionReturnsInvalidMode() {
        givenScannedString();

        willReturn(null, givenOptionMode)
                .given(configFromConsole).parseOption(givenScannedString, givenOptionType);
    }

    private void givenScannedString() {
        givenScannedString = "ValidOption";
        willReturn(givenScannedString).given(configFromConsole).scanNextLine();
    }

    private void givenAnyReadedAnyMatchMode() {
        givenAnyMatchMode();
        willReturn(givenMatchMode).given(configFromConsole).readMatchMode();
    }

    private void givenAnyReadedOutputMode() {
        givenAnyOutputMode();
        willReturn(givenOutputMode).given(configFromConsole).readOutputDestination();
    }


    private void whenReadingOutputMode() {
        returnedOutputMode = configFromConsole.readOutputDestination();
    }

    private void whenReadingMatchMode() {
        returnedMatchMode = configFromConsole.readMatchMode();
    }

    private void whenParsingSelection() {
        returnedMatchMode = configFromConsole.parseOption(givenSelection, MatchMode.class);
    }

    private void whenTheOptionLineIsRead() {
        returnedOptionMode = configFromConsole.readOptionLine(givenOptionMessage, givenOptionType);
    }

    private void whenGather() {
        returnedConfig = configFromConsole.gather();
    }


    private void thenTheReadOptionLineIsReRead() {
        then(configFromConsole).should(times(2)).parseOption(givenScannedString, givenOptionType);
    }

    private void thenTheReadOptionLineIsTheParsed() {
        then(configFromConsole).should().parseOption(givenScannedString, givenOptionType);

        assertNotNull(givenOptionMode);
        assertEquals(givenOptionMode, returnedOptionMode);
    }

    private void thenTheReadOutputModeIsTheReadedOption() {
        then(configFromConsole).should().readOptionLine(ConfigFromConsole.outputModeMessage, OutputMode.class);

        assertNotNull(givenOutputMode);
        assertEquals(givenOutputMode, returnedOutputMode);
    }

    private void thenTheReadMatchModeIsTheReadedOption() {
        then(configFromConsole).should().readOptionLine(ConfigFromConsole.matchModeMessage, MatchMode.class);

        assertNotNull(givenMatchMode);
        assertEquals(givenMatchMode, returnedMatchMode);
    }

    private void thenTheSelectedOptionIsCorrectlyParsed() {
        assertNotNull(givenMatchMode);
        assertEquals(givenMatchMode, returnedMatchMode);
    }

    private void thenTheSelectedOptionIsntCorrectlyParsed() {
        assertNull(returnedMatchMode);
    }

    private void thenTheGameConfigContainsGivenMatchMode() {
        assertNotNull(givenMatchMode);
        assertEquals(givenMatchMode, returnedConfig.getMatchMode());
    }

    private void thenTheGameConfigContainsGivenOutputMode() {
        assertNotNull(givenOutputMode);
        assertEquals(givenOutputMode, returnedConfig.getOutputMode());
    }
}