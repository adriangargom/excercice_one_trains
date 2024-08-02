package org.example.utils;

import org.example.exceptions.InvalidInputFormatException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

public class ReadInputTest {

    private static final String validInputFilePath = "./src/main/resources/valid_input_file.txt";
    private static final String invalidInputFilePathA = "./src/main/resources/invalid_input_file_A.txt";
    private static final String invalidInputFilePathB = "./src/main/resources/invalid_input_file_B.txt";

    // Tests if a valid file can be read successfully
    @Test
    public void testInputA() throws InvalidInputFormatException, IOException {
        List<String> rawData = ReadInput.readInputFile(validInputFilePath);

        assertEquals(
            "The total number of lines inside of the file should be 4",
            4,
            rawData.size()
        );

        assertEquals("AB5", rawData.get(0));
        assertEquals("BC4", rawData.get(1));
        assertEquals("CD8", rawData.get(2));
        assertEquals("DC8", rawData.get(3));
    }

    // Tests if the program can handle files with invalid spaces
    @Test(expected = InvalidInputFormatException.class)
    public void testInvalidInputFileSpaces() throws InvalidInputFormatException, IOException {
        ReadInput.readInputFile(invalidInputFilePathA);
    }

    // Test if the program can handle invalid format files
    @Test(expected = InvalidInputFormatException.class)
    public void testInvalidInputFileFormat() throws InvalidInputFormatException, IOException {
        ReadInput.readInputFile(invalidInputFilePathB);
    }

    // Tests if the program can handle files that don't exist
    @Test(expected = IOException.class)
    public void testInvalidFilePath() throws InvalidInputFormatException, IOException {
        ReadInput.readInputFile("invalid_input_file_path");
    }

}