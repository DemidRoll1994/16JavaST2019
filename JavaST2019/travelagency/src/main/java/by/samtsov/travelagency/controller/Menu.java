package by.samtsov.travelagency.controller;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.service.Parser;
import by.samtsov.travelagency.service.TourCreator;
import by.samtsov.travelagency.service.reader.FileReader;
import by.samtsov.travelagency.service.repository.*;
import by.samtsov.travelagency.view.MenuViewer;
import by.samtsov.travelagency.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private static final String FILE_PATH = "data/input.txt";
    private static final String CHOICE1 = "1";
    private static final String CHOICE2 = "2";
    private static final String CHOICE3 = "3";
    private static final String CHOICE4 = "4";
    private static final String CHOICE5 = "5";
    private static final String CHOICE6 = "6";
    private static final String EXIT_CHOICE = "7";
    private static final String DEFAULT_MESSAGE = "Select operation:";
    private static final String ERROR_MESSAGE = "Incorrect choice. Please enter a number from 1 to 7:";

    private static final Logger logger = LogManager.getLogger();
    private static final MenuViewer menuViewer = new MenuViewer();
    private static final Printer printer = new Printer();

    public Menu() {
        mainMenuHandler();
    }

    public void mainMenuHandler() {
        List<Tour> tours = new ArrayList<>();
        try {
            List<String> lines = Arrays.asList(new FileReader().read(FILE_PATH));
            Parser parser = new Parser();
            List<List<String>> valuesInLines = parser.parseLines(lines);
            tours = new TourCreator().selectCorrectValues(valuesInLines);

        } catch (IOException e) {
            logger.error(e);
            System.out.println(e);
        }
        ToursRepositoryImpl repository = ToursRepositoryImpl.getInstance(tours);
        String choice = EXIT_CHOICE + "1";
        String msg = DEFAULT_MESSAGE;
        while (!choice.equalsIgnoreCase(EXIT_CHOICE)) {
            choice = menuViewer.printChoice(msg);
            msg = DEFAULT_MESSAGE;
            switch (choice) {
                case CHOICE1:
                    printer.printTours(repository.query(new SelectAllSpecification()));
                    break;
                case CHOICE2:
                    printer.printTours(repository.query(new FindBusToursSpecification()));
                    break;
                case CHOICE3:
                    printer.printTours(repository.query(new FindShipToursSpecification()));
                    break;
                case CHOICE4:
                    printer.printTours(repository.query(new FindBudgetToursSpecification()));
                    break;
                case CHOICE5:
                    printer.printTours(repository.query(new SortByPriceAndNameSpecification()));
                    break;
                case CHOICE6:
                    printer.printTours(repository.query(new SortByIdSpecification()));
                    break;
                default:
                    msg = ERROR_MESSAGE;

            }
        }
    }
}
