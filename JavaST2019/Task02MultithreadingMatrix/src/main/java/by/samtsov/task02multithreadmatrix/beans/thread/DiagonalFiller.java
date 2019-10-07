package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.DiagonalFillerService;
import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.concurrent.TimeUnit;

public class DiagonalFiller extends Thread {

    //private static final int numberOfRowsToFill = 2;

    private DiagonalFillerService diagonalFillerService;
    private int threadNumber;

    public DiagonalFiller(int newThreadNumber, MatrixService MatrixService) {
        threadNumber = newThreadNumber;
        diagonalFillerService = new DiagonalFillerService(MatrixService);
    }

    @Override
    public void run() {
        int i=0;
        while (diagonalFillerService.getModifiedElements()
                < diagonalFillerService.getMatrixDimension()) {
            //you can replace this method for another filler method todo
            diagonalFillerService.modifyNextElement(threadNumber);
            i++; // todo remove
            if (i%3==0){
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
