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

public class Menu {

    private static final String FILE_PATH = "data/input.txt";
    private static final String SEPARATOR = ",";
    private static final String FILE_READING_ERROR = "File reading error : ";
    private static final String STANDARD_ERROR_FORMAT = "%s %s";


    private static final Logger logger = LogManager.getLogger();

    public Menu() {
        mainMenuHandler();
    }


    public MatrixService fastDiagonalFill(MatrixService matrixService) {

        FastDiagonalFillerService fastDiagonalFillerService =
                new FastDiagonalFillerService(matrixService);


        MatrixDiagonalFiller diagonalFiller1 = new FastDiagonalFiller(11
                , fastDiagonalFillerService);
        MatrixDiagonalFiller diagonalFiller2 = new FastDiagonalFiller(23
                , fastDiagonalFillerService);
        MatrixDiagonalFiller diagonalFiller3 = new FastDiagonalFiller(34
                , fastDiagonalFillerService);
        MatrixDiagonalFiller diagonalFiller4 = new FastDiagonalFiller(86
                , fastDiagonalFillerService);
        MatrixDiagonalFiller diagonalFiller5 = new FastDiagonalFiller(887
                , fastDiagonalFillerService);
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
            logger.error(String.format(STANDARD_ERROR_FORMAT, Thread.currentThread().getName()
                    , e.getMessage()));
        }
        return matrixService;
    }

    private void initializeMatrix(MatrixService matrixService) {

        try {
            matrixService.initializeMatrixFromFile(FILE_PATH, SEPARATOR);
        } catch (NoSuchFileException nsfe) {
            logger.error(String.format(STANDARD_ERROR_FORMAT, FILE_READING_ERROR
                    , nsfe.getMessage()));
        } catch (IOException ioe) {
            logger.error(String.format(STANDARD_ERROR_FORMAT, FILE_READING_ERROR
                    , ioe.getMessage()));
        }
    }


    private void barrierDiagonalFill(MatrixService matrixService) {

        DiagonalBarrierFillerService diagonalBarierFillerService =
                new DiagonalBarrierFillerService(matrixService);

        MatrixDiagonalFiller diagonalFiller1 =
                new DiagonalBarrierFiller(15, diagonalBarierFillerService, 3);
        MatrixDiagonalFiller diagonalFiller2 =
                new DiagonalBarrierFiller(27, diagonalBarierFillerService, 3);
        MatrixDiagonalFiller diagonalFiller3 =
                new DiagonalBarrierFiller(38, diagonalBarierFillerService, 3);
        MatrixDiagonalFiller diagonalFiller4 =
                new DiagonalBarrierFiller(46, diagonalBarierFillerService, 3);

        diagonalFiller1.start();
        diagonalFiller2.start();
        diagonalFiller3.start();
        diagonalFiller4.start();

        try {
            diagonalFiller1.join();
            diagonalFiller2.join();
            diagonalFiller3.join();
            diagonalFiller4.join();
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
