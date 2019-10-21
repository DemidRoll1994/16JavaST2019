package by.samtsov.task03.controller;

//import by.samtsov.task03.service.reader.FileReader;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.service.TextService;
import by.samtsov.task03.service.fileio.FileService;
import by.samtsov.task03.view.ConsoleViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainController {

    private static final String DEFAULT_INPUT_PATH = "data/input.txt";
    private static final String DEFAULT_OUTPUT_PATH = "data/output.txt";
    private static final String EXIT_ACTION = "0";
    private static final String LOAD_ACTION = "1";
    private static final String SAVE_ACTION = "2";
    private static final String CONSOLE_OUTPUT_ACTION = "3";
    private static final String SORT_PARAGRAPHS_ACTION = "4";
    private static final String SORT_WORDS_IN_SENTENCES_ACTION = "5";
    private static final String SORT_LEXEMES_IN_TEXT_ACTION = "6";
    private static final String CHANGE_LANGUAGE_ACTION = "7";

    private static final String LOAD_ERROR = "Reading file error";
    private static final String SAVE_ERROR = "Writing file error";

    private static final String DEFAULT_LOGGER_FORMAT = "%s %s";
    private static final Logger logger = LogManager.getLogger();
    private ConsoleViewer consoleViewer = new ConsoleViewer();
    private FileService fileService = new FileService();
    private TextService textService = new TextService();
    private Component text;
    private String textInString;


    private String choseFile(String defaultPath) {
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
                    textInString = textService.parseTextFromComponent(text);
                    textInString = textService
                            .sortParagraphsBySentencesCounts(textInString);
                    break;
                case SORT_WORDS_IN_SENTENCES_ACTION:
                    textInString = textService.parseTextFromComponent(text);
                    textInString = textService
                            .sortWordsInSentences(textInString);
                    break;
                case SORT_LEXEMES_IN_TEXT_ACTION:
                    textInString = textService.parseTextFromComponent(text);
                    char c = consoleViewer.askForChar();
                    if(c==0)break;
                    textInString = textService.sortLexemesInText(textInString
                            , c);
                    break;
                default:
                    consoleViewer.tryAgain();
                    break;
            }
        }
    }

    private void printText() {
        if (text != null) {
            consoleViewer.printInfo(textInString);
        } else {
            consoleViewer.tryAgain();
        }
    }


    private void writeFile() {
        try {
            textInString = textService.parseTextFromComponent(text);
            fileService.singleLineWrite(choseFile(
                    DEFAULT_OUTPUT_PATH), textInString);
        } catch (IOException e) {
            logger.error(String.format(DEFAULT_LOGGER_FORMAT
                    , SAVE_ERROR, e.getMessage()));
            consoleViewer.generateErrorMessage();
        }
    }

    private void loadFile() {
        try {
            textInString = fileService
                    .singleLineRead(choseFile(DEFAULT_INPUT_PATH));
            text = textService.parseComponentFromText(textInString);
        } catch (IOException e) {
            logger.error(String.format(DEFAULT_LOGGER_FORMAT
                    , LOAD_ERROR, e.getMessage()));
            consoleViewer.generateErrorMessage();
        }
    }
}
