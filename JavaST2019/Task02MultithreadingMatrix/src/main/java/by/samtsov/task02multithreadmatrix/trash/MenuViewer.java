package by.samtsov.task02multithreadmatrix.trash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuViewer {

    private static final Logger logger = LogManager.getLogger();

    private static final String[] CHOICES = {"1 - print all tours",
            "2 - Find Only Bus Tours", "3 - Find Only Ship Tours",
            "4 - Find Budget tours", "5 - Sort by price and names all tours",
            "6 - Sort by id all tours", "7 - Exit"};

    public MenuViewer() {
    }

    public String printChoice(String msg) {
        System.out.println(msg);
        for (String choice : CHOICES) {
            System.out.println(CHOICES);
        }
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();
        } catch (IOException e) {
            logger.warn("can't read answer from console. see:" + e.getMessage());
        }
        return line;
    }
}

