package com.github.nemesismate.gigtrial.logic.output;

import java.io.PrintWriter;

public class ConsoleOutputter extends WriterOutputter {

    public ConsoleOutputter() {
        super(new PrintWriter(System.out));
    }

}
