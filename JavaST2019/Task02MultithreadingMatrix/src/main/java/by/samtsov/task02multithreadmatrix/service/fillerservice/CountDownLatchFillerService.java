package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.beans.thread.CountDownLatchFiller;
import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchFillerService extends DiagonalFillerService {

    private CountDownLatch countDownLatch;

    public CountDownLatchFillerService(MatrixService newMatrixService
            , int[] newFillerNumbers, int countDown) {
        matrixService = newMatrixService;
        modifiedElements = 0;
        countDownLatch = new CountDownLatch(countDown);
        fillerNumbers = newFillerNumbers;
    }

    @Override
    public void fillMatrix() {

        final int NUMBER_OF_ELEMENT_TO_STOP = 3;

        ArrayList<CountDownLatchFiller> diagFillers = new ArrayList<>();

        for (int i = 0; i < fillerNumbers.length; i++) {
            diagFillers.add(new CountDownLatchFiller(fillerNumbers[i]
                    , this, NUMBER_OF_ELEMENT_TO_STOP));
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

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
