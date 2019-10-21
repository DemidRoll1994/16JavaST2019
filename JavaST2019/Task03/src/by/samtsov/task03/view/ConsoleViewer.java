package by.samtsov.task03.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleViewer {


    private static final String DEFAULT_USER_ANSWER = "";
    private static final String RESOURCE_BUNDLE_NAME = "UI_messages";

    private static final String FIRST_LANGUAGE_CHOICE = "1";
    private static final String FIRST_LANGUAGE_COUNTRY = "RU";
    private static final String FIRST_LANGUAGE = "ru";
    private static final String SECOND_LANGUAGE_CHOICE = "2";
    private static final String SECOND_LANGUAGE_COUNTRY = "BY";
    private static final String SECOND_LANGUAGE = "be";
    private static final String THIRD_LANGUAGE_CHOICE = "3";
    private static final String THIRD_LANGUAGE_COUNTRY = "UK";
    private static final String THIRD_LANGUAGE = "en";


    private String country = Locale.getDefault().getCountry();
    private String language = Locale.getDefault().getLanguage();
    private Locale currentLocale = new Locale(language, country);
    ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME
            , currentLocale);

    public void printInfo(String info) {
        System.out.println(info);
    }

    public void askForLanguage() {

        String choice = new ConsoleViewer().askAQuestion(
                rb.getString("select_lang_msg"), "not selected");

        switch (choice) {
            case FIRST_LANGUAGE_CHOICE:
                currentLocale = new Locale(FIRST_LANGUAGE_COUNTRY
                        , FIRST_LANGUAGE);
                break;
            case SECOND_LANGUAGE_CHOICE:
                currentLocale = new Locale(SECOND_LANGUAGE_COUNTRY
                        , SECOND_LANGUAGE);
                break;
            case THIRD_LANGUAGE_CHOICE:
                currentLocale = new Locale(THIRD_LANGUAGE_COUNTRY
                        , THIRD_LANGUAGE);
                break;
            default:
                currentLocale = new Locale(country, language);
                break;
        }
        printInfo(rb.getString("chosenLanguageInfo"));
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, currentLocale);
    }


    private String askAQuestion(String question, String answerByDefault) {
        printInfo(question);
        String choice = receiveTextFromConsole();
        if (choice.equals(DEFAULT_USER_ANSWER)) {
            choice = answerByDefault;
        }
        return choice;
    }


    public String selectPath(String defaultPath) {
        return askAQuestion(rb.getString("choseFilePath"), defaultPath);
    }


    public String receiveTextFromConsole() {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }


    public void tryAgain() {
        printInfo(rb.getString("wrongChoiceMessage"));
    }

    public void generateErrorMessage() {
        printInfo(rb.getString("errorMessage"));
    }

    public String askForAction() {
        return askAQuestion(rb.getString("choseAction"), "");
    }

    public char askForChar() {
        while (true) {
            String answer = askAQuestion(rb.getString("choseCharacterToFind"), "");
            if (answer.length()==1) {
                return askAQuestion(rb.getString("choseCharacterToFind"), "").charAt(0);
            }
            tryAgain();
        }
    }
}
