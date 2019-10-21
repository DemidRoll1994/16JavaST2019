package by.samtsov.task03.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleViewer {


    /**
     * Constant that contain default user answer value. it use for quick work
     * with application.
     */
    private static final String DEFAULT_USER_ANSWER = "";
    /**
     * Constant that contain RESOURCE BUNDLE NAME for multilingual app.
     */
    private static final String RESOURCE_BUNDLE_NAME = "UI_messages";

    /**
     * contains FIRST LANGUAGE CHOICE for multilingual app(1).
     */
    private static final String FIRST_LANGUAGE_CHOICE = "1";
    /**
     * contains FIRST LANGUAGE COUNTRY for multilingual app(Russia).
     */
    private static final String FIRST_LANGUAGE_COUNTRY = "RU";
    /**
     * contains FIRST LANGUAGE  for multilingual app.(russian).
     */
    private static final String FIRST_LANGUAGE = "ru";
    /**
     * contains second LANGUAGE CHOICE for multilingual app(2).
     */
    private static final String SECOND_LANGUAGE_CHOICE = "2";
    /**
     * contains second LANGUAGE COUNTRY for multilingual app(Belarus).
     */
    private static final String SECOND_LANGUAGE_COUNTRY = "BY";
    /**
     * contains second LANGUAGE  for multilingual app.(belorussian).
     */
    private static final String SECOND_LANGUAGE = "be";
    /**
     * contains third LANGUAGE CHOICE for multilingual app(3).
     */
    private static final String THIRD_LANGUAGE_CHOICE = "3";
    /**
     * contains second LANGUAGE COUNTRY for multilingual app(united kingdom).
     */
    private static final String THIRD_LANGUAGE_COUNTRY = "UK";
    /**
     * contains second LANGUAGE  for multilingual app.(english).
     */
    private static final String THIRD_LANGUAGE = "en";

    /**
     * l10n parameter. default value is current machine country..
     */
    private String country = Locale.getDefault().getCountry();
    /**
     * l10n parameter. default value is current machine language.
     */
    private String language = Locale.getDefault().getLanguage();
    /**
     * l10n parameter. default value is current machine locale.
     */
    private Locale currentLocale = new Locale(language, country);
    /**
     * l10n parameter. contains all defined messages for current Locale.
     */
    private ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME,
            currentLocale);

    /**
     * log4j2 appender for current class.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * printer on Console.
     *
     * @param info - text to print.
     */
    public void printInfo(final String info) {
        System.out.println(info);
    }

    /**
     * changes language of application.
     */
    public void askForLanguage() {

        String choice = new ConsoleViewer().askAQuestion(
                rb.getString("select_lang_msg"), "not selected");

        switch (choice) {
            case FIRST_LANGUAGE_CHOICE:
                currentLocale = new Locale(FIRST_LANGUAGE_COUNTRY,
                        FIRST_LANGUAGE);
                break;
            case SECOND_LANGUAGE_CHOICE:
                currentLocale = new Locale(SECOND_LANGUAGE_COUNTRY,
                        SECOND_LANGUAGE);
                break;
            case THIRD_LANGUAGE_CHOICE:
                currentLocale = new Locale(THIRD_LANGUAGE_COUNTRY,
                        THIRD_LANGUAGE);
                break;
            default:
                currentLocale = new Locale(country, language);
                break;
        }
        printInfo(rb.getString("chosenLanguageInfo"));
        rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, currentLocale);
    }

    /**
     * ask a question in @param question and @return user answer.
     *
     * @param question        - asked question
     * @param answerByDefault - method will return this answer if user enter
     *                        DEFAULT_USER_ANSWER
     * @return user answer as string
     */
    private String askAQuestion(final String question, final String
            answerByDefault) {
        printInfo(question);
        String choice = receiveTextFromConsole();
        if (choice.equals(DEFAULT_USER_ANSWER)) {
            choice = answerByDefault;
        }
        return choice;
    }

    /**
     * ask for a path selection.
     *
     * @param defaultPath - method will return this path if user enter
     *                    DEFAULT_USER_ANSWER
     * @return path
     */

    public String selectPath(final String defaultPath) {
        return askAQuestion(rb.getString("choseFilePath"), defaultPath);
    }

    /**
     * method receive text from console.
     *
     * @return user entered data as a string
     */
    public String receiveTextFromConsole() {
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            line = reader.readLine();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return line;
    }

    /**
     * simple method that says something go wrong. and ask for trying again.
     */
    public void tryAgain() {
        printInfo(rb.getString("wrongChoiceMessage"));
    }

    /**
     * simple method that says something go wrong. and ask for use feedback to
     * developer.
     */
    public void generateErrorMessage() {
        printInfo(rb.getString("errorMessage"));
    }

    /**
     * main menu method. ask for choice action that application should perform.
     *
     * @return choice as a String
     */
    public String askForAction() {
        return askAQuestion(rb.getString("choseAction"), "");
    }

    /**
     * specific method for ask a single char from user. check if user enter not
     * one symbol, ask again.
     *
     * @return symbol from console as a char.
     */
    public char askForChar() {
        while (true) {
            String answer = askAQuestion(rb.getString("choseCharacterToFind"),
                    "");
            if (answer.length() == 1) {
                return askAQuestion(rb.getString("choseCharacterToFind"), "")
                        .charAt(0);
            }
            tryAgain();
        }
    }
}
