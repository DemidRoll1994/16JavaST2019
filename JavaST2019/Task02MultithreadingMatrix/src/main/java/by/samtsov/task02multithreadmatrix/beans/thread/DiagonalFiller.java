package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.concurrent.TimeUnit;

public class DiagonalFiller extends Thread {

    //private static final int numberOfRowsToFill = 2;

    private MatrixService matrixService;
    private int threadNumber;

    public DiagonalFiller(int threadNumber, MatrixService newMatrixService) {
        matrixService = newMatrixService;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            matrixService.modifyElementWithLock(i,i,threadNumber);

        }

    }


}
