package by.samtsov.task03.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {

    private static final String enterPath = "1 - print all tours"; //todo localtext
    private static final String defaultAnswer = ""; //todo localtext


    public String askPath(String defaultValue) {

        System.out.printf("%s (%s)\n", enterPath , defaultValue);
        String choice = receiveTextFromConsole();
        if (choice.equals(defaultAnswer)){
            choice=defaultValue;
        }
        return choice;
    }


    public String receiveTextFromConsole () {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }








}
