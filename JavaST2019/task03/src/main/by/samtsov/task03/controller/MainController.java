package by.samtsov.task03.controller;


import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.service.TextService;
import by.samtsov.task03.service.fileio.FileService;
import by.samtsov.task03.view.ConsoleViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainController {

    /**
     * default input path. if user don't chose a path, data will be loaded from
     * this path.
     */
    private static final String DEFAULT_INPUT_PATH = "data/input.txt";
    /**
     * default output path. if user don't chose a path, data will be written to
     * this path.
     */
    private static final String DEFAULT_OUTPUT_PATH = "data/output.txt";
    /**
     * constant for exit choice. user answer must be same as here to choice this
     * action.
     */
    private static final String EXIT_ACTION = "0";
    /**
     * constant for load choice. user answer must be same as here to choice this
     * action.
     */
    private static final String LOAD_ACTION = "1";
    /**
     * constant for save choice. user answer must be same as here to choice this
     * action.
     */
    private static final String SAVE_ACTION = "2";
    /**
     * constant for console output choice. user answer must be same as here to
     * choice this action.
     */
    private static final String CONSOLE_OUTPUT_ACTION = "3";
    /**
     * constant for sort paragraphs by sentences choice. user answer must be
     * same as here to choice this action.
     */
    private static final String SORT_PARAGRAPHS_ACTION = "4";
    /**
     * constant for sort words in sentences choice. user answer must be same as
     * here to choice this action.
     */
    private static final String SORT_WORDS_IN_SENTENCES_ACTION = "5";
    /**
     * constant for sort lexemes in text choice. user answer must be same as
     * here to choice this action.
     */
    private static final String SORT_LEXEMES_IN_TEXT_ACTION = "6";
    /**
     * constant for change language choice. user answer must be same as here to
     * choice this action.
     */
    private static final String CHANGE_LANGUAGE_ACTION = "7";

    /**
     * load error message for logger.
     */
    private static final String LOAD_ERROR = "Reading file error";

    /**
     * save error message for logger.
     */
    private static final String SAVE_ERROR = "Writing file error";

    /**
     * error message format for logger. #1(%s)-LOAD_ERROR/SAVE_ERROR
     * #2(%s)- exception.getMessage();
     */
    private static final String DEFAULT_LOGGER_FORMAT = "%s %s";
    /**
     * log4j2 appender for current class.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * view-layer object. works with console.
     */
    private ConsoleViewer consoleViewer = new ConsoleViewer();
    /**
     * service-layer object. works with filesystem. reading and writing to file.
     */
    private FileService fileService = new FileService();
    /**
     * service-layer object. contains operation defined on components.
     */
    private TextService textService = new TextService();
    /**
     * Text as a Component-object.
     */
    private Component text;
    /**
     * Text as a String-object.
     */
    private String textInString;

    /**
     * method for chose a path.
     *
     * @param defaultPath - method will return this path if user don't chose a
     *                    valid path
     * @return path
     */
    private String choseFile(final String defaultPath) {
        String filePath;
        while (true) {
            filePath = consoleViewer.selectPath(defaultPath);
            if (fileService.isFileExist(filePath)) {
                break;
            }
            consoleViewer.tryAgain();
        }
        return filePath;
    }

    /**
     * main action method. works on while(true) statement.
     */
    void start() {
        while (true) {
            String action = consoleViewer.askForAction();
            switch (action) {
                case EXIT_ACTION:
                    return;
                case LOAD_ACTION:
                    loadFile();
                    break;
                case SAVE_ACTION:
                    writeFile();
                    break;
                case CONSOLE_OUTPUT_ACTION:
                    printText();
                    break;
                case CHANGE_LANGUAGE_ACTION:
                    consoleViewer.askForLanguage();
                    break;
                case SORT_PARAGRAPHS_ACTION:
                    if (text != null) {
                        textInString = textService.parseTextFromComponent(text);
                        textInString = textService
                                .sortParagraphsBySentencesCounts(textInString);
                    } else {
                        consoleViewer.tryAgain();
                    }
                    break;
                case SORT_WORDS_IN_SENTENCES_ACTION:
                    if (text != null) {
                        textInString = textService.parseTextFromComponent(text);
                        textInString = textService
                                .sortWordsInSentences(textInString);
                    } else {
                        consoleViewer.tryAgain();
                    }
                    break;
                case SORT_LEXEMES_IN_TEXT_ACTION:
                    if (text != null) {
                        textInString = textService.parseTextFromComponent(text);
                        char c = consoleViewer.askForChar();
                        if (c == 0) {
                            break;
                        }
                        textInString = textService.sortLexemesInText(
                                textInString, c);
                    } else {
                        consoleViewer.tryAgain();
                    }
                    break;
                default:
                    consoleViewer.tryAgain();
                    break;
            }
        }
    }

    /**
     * print Text if (text!= null) or inform about operation is unavailable now.
     */
    private void printText() {
        if (text != null) {
            consoleViewer.printInfo(textInString);
        } else {
            consoleViewer.tryAgain();
        }
    }

    /**
     * write String value in file in single line.
     */

    private void writeFile() {
        try {
            textInString = textService.parseTextFromComponent(text);
            fileService.singleLineWrite(choseFile(
                    DEFAULT_OUTPUT_PATH), textInString);
        } catch (IOException e) {
            logger.error(String.format(DEFAULT_LOGGER_FORMAT,
                    SAVE_ERROR, e.getMessage()));
            consoleViewer.generateErrorMessage();
        }
    }

    /**
     * read String value from file and parse it to component form.
     */
    private void loadFile() {
        try {
            textInString = fileService
                    .singleLineRead(choseFile(DEFAULT_INPUT_PATH));
            text = textService.parseComponentFromText(textInString);
        } catch (IOException e) {
            logger.error(String.format(DEFAULT_LOGGER_FORMAT,
                    LOAD_ERROR, e.getMessage()));
            consoleViewer.generateErrorMessage();
        }
    }
}
