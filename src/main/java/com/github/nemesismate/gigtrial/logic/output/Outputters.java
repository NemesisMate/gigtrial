package com.github.nemesismate.gigtrial.logic.output;

import com.github.nemesismate.gigtrial.logic.config.OutputMode;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class Outputters {

    public static Outputter from(OutputMode outputMode) throws IOException {
        switch (outputMode) {
            case CONSOLE:
                return new ConsoleOutputter();
            case FILE:
                return new FileOutputter(System.getProperty("user.home") + File.separatorChar + "GIG_SIM_RESULT_FILE.txt");
            default:
                throw new UnsupportedOperationException("Unsupported output mode: " + outputMode);
        }
    }

}
