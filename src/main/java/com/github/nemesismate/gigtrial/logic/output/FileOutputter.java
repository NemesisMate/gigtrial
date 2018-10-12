package com.github.nemesismate.gigtrial.logic.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter extends WriterOutputter {

    public FileOutputter(String filePathName) throws IOException {
        super(new FileWriter(new File(filePathName)));
    }

}
