package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.beans.thread.DiagonalBarrierFiller;
import by.samtsov.task02multithreadmatrix.beans.thread.DiagonalSemaphoreFiller;
import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SemaphoreFillerService extends DiagonalFillerService {

    private Semaphore semaphore;

    public SemaphoreFillerService(MatrixService newMatrixService
            , int [] newFillerNumbers, int semaphorePermits) {
        matrixService = newMatrixService;
        modifiedElements = 0;
        semaphore = new Semaphore(semaphorePermits);
        fillerNumbers=newFillerNumbers;
    }


    @Override
    public void fillMatrix() {

        ArrayList<DiagonalSemaphoreFiller> diagFillers = new ArrayList<>();

        for (int i = 0; i < fillerNumbers.length; i++) {

            diagFillers.add(new DiagonalSemaphoreFiller(fillerNumbers[i]
                    , this, semaphore));
            diagFillers.get(i).start();
        }
        try {
            for (int i = 0; i < fillerNumbers.length; i++) {
                diagFillers.get(i).join();
            }
        } catch (InterruptedException e) {
            logger.error(String.format(STANDARD_ERROR_FORMAT
                    , Thread.currentThread().getName(), e.getMessage()));
        }
    }
}
