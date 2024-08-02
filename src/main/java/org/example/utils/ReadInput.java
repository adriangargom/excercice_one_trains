package org.example.utils;

import org.example.exceptions.InvalidInputFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadInput {

    // Valid line REGEX format and compiled pattern
    private static final String regex = "^[A-Z]{1,2}[0-9]*";
    private static final Pattern pattern = Pattern.compile(regex);

    // Reads the input file based on the provided path and processes the file
    public static List<String> readInputFile(String inputFilePath)
        throws InvalidInputFormatException, IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            return processFileData(bufferedReader);
        }
    }

    // The following method is uncharged of processing the file inside a new
    // array that contains all the valid lines of the file
    private static List<String> processFileData(BufferedReader bufferedReader)
        throws IOException, InvalidInputFormatException {

        List<String> rawInput = new ArrayList<>();
        String line = bufferedReader.readLine();

        while(line != null) {
            if(!validateInputLine(line))
                throw new InvalidInputFormatException(String.format("Invalid value %s", line));

            rawInput.add(line);
            line = bufferedReader.readLine();
        }

        return rawInput;
    }

    // Checks if the provided line follows a valid pattern and then can be
    // processed for building the graph
    private static boolean validateInputLine(String line) {
        boolean isLineEmpty = line.isEmpty();
        boolean isLineFormatValid = pattern.matcher(line).matches();

        return !isLineEmpty && isLineFormatValid;
    }

}