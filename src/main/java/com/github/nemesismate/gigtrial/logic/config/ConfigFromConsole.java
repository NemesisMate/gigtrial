package com.github.nemesismate.gigtrial.logic.config;

import java.util.Arrays;
import java.util.Scanner;

public class ConfigFromConsole {

    static String matchModeMessage = "Please, select the mode of play:"
            + generateOptionsMessage(MatchMode.values());

    static String outputModeMessage = "Please, select the output destination:"
            + generateOptionsMessage(OutputMode.values());


    private static String generateOptionsMessage(Enum[] values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Enum value : values) {
            stringBuilder.append('\n').append(value.ordinal()).append(") ").append(value.toString());
        }

        return stringBuilder.toString();
    }

    private Scanner scanner = new Scanner(System.in);

    public GameConfig gather() {
        return GameConfig.builder()
                .matchMode(readMatchMode())
                .outputMode(readOutputDestination())
                .build();
    }


    Integer getNumber(String word) {
        try {
            return Integer.valueOf(word);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    OutputMode readOutputDestination() {
        return readOptionLine(outputModeMessage, OutputMode.class);
    }

    MatchMode readMatchMode() {
        return readOptionLine(matchModeMessage, MatchMode.class);
    }

    <T extends Enum> T readOptionLine(String message, Class<T> optionType) {
        println(message);
        String optionString = scanNextLine();

        T selectedOption = parseOption(optionString, optionType);

        if (selectedOption == null) {
            println("The selected option: " + optionString + " isn't available.");
            return readOptionLine(message, optionType);
        }

        return selectedOption;
    }

    String scanNextLine() {
        return scanner.nextLine();
    }

    <T extends Enum> T parseOption(String optionString, Class<T> optionType) {
        T selectedOption = null;

        Integer selectedIndex = getNumber(optionString);

        T[] values = optionType.getEnumConstants();

        if (selectedIndex != null) { // If we are selecting by index
            if (selectedIndex < values.length) {
                selectedOption = values[selectedIndex];
            }
        } else { // If we are selecting by name
            selectedOption = Arrays.stream(values)
                    .filter(value -> value.toString().equals(optionString.toUpperCase()))
                    .findFirst().orElse(null);
        }

        return selectedOption;
    }

    void println(String printString) {
        System.out.println(printString);
    }

}
