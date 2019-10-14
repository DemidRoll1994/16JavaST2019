package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.service.MatrixService;

import java.util.concurrent.CyclicBarrier;

public class DiagonalBarrierFillerService extends DiagonalFillerService {

    private CyclicBarrier barrier;

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public DiagonalBarrierFillerService(MatrixService newMatrixService) {
        matrixService = newMatrixService;
        modifiedElements = 0;
        barrier = new CyclicBarrier(4);
    }

}
