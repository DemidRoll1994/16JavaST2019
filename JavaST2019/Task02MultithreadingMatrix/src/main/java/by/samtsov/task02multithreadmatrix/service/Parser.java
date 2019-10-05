package by.samtsov.task02multithreadmatrix.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {

    private static final Logger logger = LogManager.getLogger();
    private String delimiter;

    private String[][] parseLines(String[] parsingLines, String delimiter) {
        logger.debug("------Parsing start------");
        int arrayLength = parsingLines.length;
        String[][] parsedValues = new String[arrayLength][];
        for (int i = 0; i < arrayLength; i++) {
            parsedValues[i] = parsingLines[i].split(delimiter);
        }
        logger.debug("------Parsing finish------");
        return parsedValues;
    }

    //it should be modified with Stream API
    public int[][] parseLinesToInteger(String[] parsingLines, String delimiter) {
        int arrayLength = parsingLines.length;
        int[][] parsedValues = new int[arrayLength][];
        for (int i = 0; i < arrayLength; i++) {
            String[] temp = parsingLines[i].split(delimiter);
            parsedValues[i] = new int[temp.length];
            for (int j = 0; j < temp.length; j++) {
                parsedValues[i][j] = Integer.parseInt(temp[j]);
            }
        }
        return parsedValues;
    }
}
