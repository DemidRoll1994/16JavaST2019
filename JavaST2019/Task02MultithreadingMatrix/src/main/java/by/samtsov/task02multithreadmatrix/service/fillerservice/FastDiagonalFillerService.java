package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.beans.thread.FastDiagonalFiller;
import by.samtsov.task02multithreadmatrix.beans.thread.MatrixDiagonalFiller;
import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.ArrayList;
import java.util.List;

public class FastDiagonalFillerService extends DiagonalFillerService {

    public FastDiagonalFillerService(MatrixService newMatrixService
            , int [] newFillerNumbers) {
        matrixService = newMatrixService;
        modifiedElements = 0;
        fillerNumbers=newFillerNumbers;
    }

    public void fillMatrix() {

        List<MatrixDiagonalFiller> diagonalFillers = new ArrayList<>();
        for (int i = 0; i < fillerNumbers.length; i++) {
            diagonalFillers.add(new FastDiagonalFiller(fillerNumbers[i]
                    , this));
        }
        for (int i = 0; i < fillerNumbers.length; i++) {
            diagonalFillers.get(i).start();
            try {
                diagonalFillers.get(i).join();
            } catch (InterruptedException e) {
                logger.error(String.format(STANDARD_ERROR_FORMAT
                        , Thread.currentThread().getName(), e.getMessage()));
            }
        }

    }
}
