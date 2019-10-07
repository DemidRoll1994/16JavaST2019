package by.samtsov.task02multithreadmatrix.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {

    /**
     * TestNG logger.
     */

    private static final Logger logger = LogManager.getLogger();

    /**
     * unsupported operation
     */
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

    /**
     * method to parse one-dimension string array to two-dimension array of int
     * @param parsingLines one-dimension string array
     * @param delimiter string, that divide values in line
     * @return two-dimension array of int
     */
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
