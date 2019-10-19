package by.samtsov.task03.controller;

//import by.samtsov.task03.service.reader.FileReader;

import by.samtsov.task03.service.TextService;
import by.samtsov.task03.view.ConsoleView;

public class MainController {

    private static final String enterPath = "1 - print all tours"; //todo localtext
    private static final String defaultInputPath = ""; //todo localtext
    private static final String defaultOutputPath = ""; //todo localtext


    public void start() {
        ConsoleView consoleView = new ConsoleView();
        String inputPath = consoleView.askPath(defaultInputPath);

        TextService textService = new TextService();
        /*try {//todo delete trycatch
            Component text = textService.parseComponentFromText(new FileReader()
                    .singleLineRead(inputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
}
