package by.samtsov.task02multithreadmatrix.view;

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
    private static final String EXITCHOICE = "7 - Exit";

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
        System.out.println(EXITCHOICE);
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

