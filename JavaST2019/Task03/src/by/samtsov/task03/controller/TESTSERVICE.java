package by.samtsov.task03.controller;

import by.samtsov.task03.service.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TESTSERVICE {

    private static final Logger logger = LogManager.getLogger();

    public void start() {
        String string = null;
        try {
            string = new FileReader().singleLineRead("data/input.txt");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        //print(string.split("(?<=[!?.]{1,3})\r\n"));
        //print(string.split("(?<=[!?.]{1,3})\\s+"));
        //print(string.split("(?=[!?.,;])"));
        //print(string.split("(?<=[!?.]{1,3})\r\n"));
        //string.matches() //todo


        regWorker("[\\w]+|[^\\w\\s]+");
        System.out.println(7+6.0<+9/-9?("name"!="name"):'c'=='с');

    }

    public void print(String[] args) {
        for (String s : args) {
            System.out.println(s + "|");
        }
    }

    public void regWorker(String reg) {
        String string = null;
        try {
            string = new FileReader().singleLineRead("data/input.txt");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group() + "|");
        }
    }

}
