package by.samtsov.task02multithreadmatrix.controller;

import by.samtsov.task02multithreadmatrix.service.MatrixService;
import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalBarrierFillerService;
import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalFillerService;
import by.samtsov.task02multithreadmatrix.service.fillerservice.FastDiagonalFillerService;
import by.samtsov.task02multithreadmatrix.service.fillerservice.SemaphoreFillerService;
import by.samtsov.task02multithreadmatrix.view.MainMenu;
import by.samtsov.task02multithreadmatrix.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class MainController {

    private static final String FILE_PATH = "data/input.txt";
    private static final String SEPARATOR = ",";
    private static final String FILE_READING_ERROR = "File reading error : ";
    private static final String STANDARD_ERROR_FORMAT = "%s %s";
    private static final int[] FILLER_NUMBERS = new int[]{11, 22, 3, 44, 5};
    private static final int CHOICE1 = 1;
    private static final int CHOICE2 = 2;
    private static final int CHOICE3 = 3;
    private static final int CHOICE4 = 4;
    private static final int EXIT_CHOICE = 0;
    private static final int BARRIER_COUNT = 3;
    private static final int SEMAPHORE_COUNT = 2;


    private static final Logger logger = LogManager.getLogger();

    public MainController() {
        mainMenuHandler();
    }

    private void initializeMatrix(MatrixService matrixService) {

        try {
            matrixService.initializeMatrixFromFile(FILE_PATH, SEPARATOR);
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error(String.format(STANDARD_ERROR_FORMAT, FILE_READING_ERROR
                    , illegalArgumentException.getMessage()));
        } catch (NoSuchFileException nsfe) {
            logger.error(String.format(STANDARD_ERROR_FORMAT, FILE_READING_ERROR
                    , nsfe.getMessage()));
        } catch (IOException ioe) {
            logger.error(String.format(STANDARD_ERROR_FORMAT, FILE_READING_ERROR
                    , ioe.getMessage()));
        }
    }


    public void mainMenuHandler() {
        logger.info("Application has been start");
        MatrixService matrixService = new MatrixService();

        DiagonalFillerService diagonalFillerService;

        MainMenu mainMenu = new MainMenu();
        Printer printer = new Printer();

        while (true) {
            initializeMatrix(matrixService);
            int choice = mainMenu.getChoice();
            switch (choice) {
                case CHOICE1:
                    diagonalFillerService
                            = new FastDiagonalFillerService(matrixService
                            , FILLER_NUMBERS);
                    break;
                case CHOICE2:
                    diagonalFillerService
                            = new DiagonalBarrierFillerService(matrixService
                            , FILLER_NUMBERS, BARRIER_COUNT);
                    break;
                case CHOICE3:
                    diagonalFillerService
                            = new SemaphoreFillerService(matrixService
                            , FILLER_NUMBERS, SEMAPHORE_COUNT);
                    break;
                case CHOICE4:
                    diagonalFillerService
                            = new SemaphoreFillerService(matrixService
                            , FILLER_NUMBERS, SEMAPHORE_COUNT);
                    break;

                default:
                case EXIT_CHOICE:
                    logger.info("Application termination...");
                    return;
            }
            diagonalFillerService.fillMatrix();
            printer.printMatrix(diagonalFillerService.getMatrixService().getMatrix());
        }


    }


}
