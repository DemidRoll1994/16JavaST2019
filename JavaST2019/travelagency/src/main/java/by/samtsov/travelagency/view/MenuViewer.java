package by.samtsov.travelagency.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuViewer {

    private static final String CHOICE1 = "1 - print all tours";
    private static final String CHOICE2 = "2 - Find Only Bus Tours";
    private static final String CHOICE3 = "3 - Find Only Ship Tours";
    private static final String CHOICE4 = "4 - Find Budget tours";
    private static final String CHOICE5 = "5 - Sort by price and names all tours";
    private static final String CHOICE6 = "6 - Sort by id all tours";
    private static final String EXITCHOICE = "7";

    private static final Logger logger = LogManager.getLogger();

    public MenuViewer() {
    }

    public String printChoice(String msg) {
        System.out.println(msg);
        System.out.println(CHOICE1);
        System.out.println(CHOICE2);
        System.out.println(CHOICE3);
        System.out.println(CHOICE4);
        System.out.println(CHOICE5);
        System.out.println(CHOICE6);
        System.out.println("7 - Exit");
        String line = "";
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();
        } catch (IOException e) {
            logger.debug(e);
            e.printStackTrace();
        }
        return line;
    }
}
