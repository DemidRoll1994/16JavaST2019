package by.samtsov.task02multithreadmatrix.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    private static final String[] CHOICES = {"0", "1", "2", "3", "4"};
    private static final String[] ANSWERS = {"1 - Fast diagonal filler"
            , "2 - Barrier diagonal filler", "3 - Semaphore diagonal filler"
            , "4 - Count down latch filler"};
    private static final String EXIT_ANSWER = "0 - Exit";

    private static final String DEFAULT_MESSAGE = "Select Matrix Filler:";
    private static final String INCORRECT_CHOICE_MSG
            = "Incorrect choice. Please enter a number from 0 to 4:";
    private static final String INPUT_ERROR
            = "Input from console error (System.in) ";

    private static final Logger logger = LogManager.getLogger();

    public MainMenu() {
    }

    public int getChoice() {
        String choice = "";
        while (true) {
            System.out.println(DEFAULT_MESSAGE);
            for (String answer : ANSWERS) {
                System.out.println(answer);
            }
            System.out.println(EXIT_ANSWER);
            try {
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(System.in));
                choice = reader.readLine();
            } catch (IOException e) {
                logger.error(INPUT_ERROR + e.getMessage());
            }
            if (isChoiceValid(choice)) {
                return Integer.parseInt(choice);
            }
            System.out.println(INCORRECT_CHOICE_MSG);
        }
    }

    private boolean isChoiceValid(String enteredChoice) {
        for (String validChoice : CHOICES) {
            if (enteredChoice.equalsIgnoreCase(validChoice)) {
                return true;
            }
        }
        return false;
    }


}
