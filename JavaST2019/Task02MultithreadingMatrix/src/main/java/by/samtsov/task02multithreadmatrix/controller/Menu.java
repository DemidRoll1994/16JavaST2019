package by.samtsov.task02multithreadmatrix.controller;

import by.samtsov.task02multithreadmatrix.beans.thread.DiagonalBarrierFiller;
import by.samtsov.task02multithreadmatrix.beans.thread.FastDiagonalFiller;
import by.samtsov.task02multithreadmatrix.beans.thread.MatrixDiagonalFiller;
import by.samtsov.task02multithreadmatrix.service.MatrixService;
import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalBarrierFillerService;
import by.samtsov.task02multithreadmatrix.service.fillerservice.FastDiagonalFillerService;
import by.samtsov.task02multithreadmatrix.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class Menu {

    private static final String FILE_PATH = "data/input.txt";
    private static final String SEPARATOR = ",";
    private static final String FILE_READING_ERROR = "File reading error : ";
    private static final String STANDARD_ERROR_FORMAT = "%s %s";
    private static final int[] FILLER_NUMBERS = new int[]{11, 22, 3, 44, 5};


    private static final Logger logger = LogManager.getLogger();

    public Menu() {
        mainMenuHandler();
    }


    public MatrixService fastDiagonalFill(MatrixService matrixService) {

        FastDiagonalFillerService fastDiagFillService =
                new FastDiagonalFillerService(matrixService);

        // correctly use here a List, but it less effective and slowly
        MatrixDiagonalFiller diagonalFiller1 =
                new FastDiagonalFiller(FILLER_NUMBERS[0], fastDiagFillService);
        MatrixDiagonalFiller diagonalFiller2 =
                new FastDiagonalFiller(FILLER_NUMBERS[1], fastDiagFillService);
        MatrixDiagonalFiller diagonalFiller3 =
                new FastDiagonalFiller(FILLER_NUMBERS[2], fastDiagFillService);
        MatrixDiagonalFiller diagonalFiller4 =
                new FastDiagonalFiller(FILLER_NUMBERS[3], fastDiagFillService);
        MatrixDiagonalFiller diagonalFiller5 =
                new FastDiagonalFiller(FILLER_NUMBERS[4], fastDiagFillService);
        diagonalFiller1.start();
        diagonalFiller2.start();
        diagonalFiller3.start();
        diagonalFiller4.start();
        diagonalFiller5.start();
        try {
            diagonalFiller1.join();
            diagonalFiller2.join();
            diagonalFiller3.join();
            diagonalFiller4.join();
            diagonalFiller5.join();
        } catch (InterruptedException e) {
            logger.error(String.format(STANDARD_ERROR_FORMAT
                    , Thread.currentThread().getName(), e.getMessage()));
        }
        return matrixService;
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

    private void barrierDiagonalFill(MatrixService matrixService) {
        final int barrier = 3;
        DiagonalBarrierFillerService diagBarierFillService =
                new DiagonalBarrierFillerService(matrixService);

        ArrayList<DiagonalBarrierFiller> diagFillers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            diagFillers.add(new DiagonalBarrierFiller(FILLER_NUMBERS[i]
                    , diagBarierFillService, barrier));
            diagFillers.get(i).start();
        }


        try {
            for (int i = 0; i < 4; i++) {
                diagFillers.get(i).join();
            }
        } catch (InterruptedException e) {
            logger.error(String.format(STANDARD_ERROR_FORMAT
                    , Thread.currentThread().getName(), e.getMessage()));
        }
    }

    public void mainMenuHandler() {
        MatrixService matrixService = new MatrixService();
        initializeMatrix(matrixService);
        fastDiagonalFill(matrixService);
        new Printer().printMatrix(matrixService.getMatrix());

        initializeMatrix(matrixService);
        barrierDiagonalFill(matrixService);
        new Printer().printMatrix(matrixService.getMatrix());
    }


}
