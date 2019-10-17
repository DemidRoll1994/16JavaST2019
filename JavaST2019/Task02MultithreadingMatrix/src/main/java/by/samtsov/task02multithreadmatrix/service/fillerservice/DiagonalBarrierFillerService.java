package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.beans.thread.DiagonalBarrierFiller;
import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class DiagonalBarrierFillerService extends DiagonalFillerService {

    private CyclicBarrier barrier;

    public DiagonalBarrierFillerService(MatrixService newMatrixService
            , int[] newFillerNumbers, int barrierCount) {
        matrixService = newMatrixService;
        modifiedElements = 0;
        barrier = new CyclicBarrier(barrierCount);
        fillerNumbers = newFillerNumbers;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    @Override
    public void fillMatrix() {

        final int NUMBER_OF_ELEMENT_TO_STOP = 3;

        ArrayList<DiagonalBarrierFiller> diagFillers = new ArrayList<>();

        for (int i = 0; i < fillerNumbers.length; i++) {
            diagFillers.add(new DiagonalBarrierFiller(fillerNumbers[i]
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

}
