package by.samtsov.task02multithreadmatrix.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final Logger logger = LogManager.getLogger();

    public List<List<String>> parseLines(List<String> parsingLines) {
        logger.debug("------Parsing start------");
        List<List<String>> parsedValues = new ArrayList<>();
        for (String line : parsingLines) {
            parsedValues.add(Arrays.asList(line.split(",")));
            logger.debug("parsed values:"+parsedValues.get(parsedValues.size()-1));
        }
        logger.debug("------Parsing finish------");
        return parsedValues;
    }
}
