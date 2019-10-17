package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalBarrierFillerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DiagonalBarrierFiller extends MatrixDiagonalFiller {

    private static final Logger logger = LogManager.getLogger();

    private static final String STANDARD_ERROR_FORMAT = "%s %s";
    CyclicBarrier barrier;
    private int numberOfElementToStop;

    public DiagonalBarrierFiller(int newThreadNumber
            , DiagonalBarrierFillerService diagonalBarrierFillerService
            , int newNumberOfElementToStop) {
        threadNumber = newThreadNumber;
        diagonalFillerService = diagonalBarrierFillerService;
        numberOfElementToStop = newNumberOfElementToStop;
        barrier = diagonalBarrierFillerService.getBarrier();
    }

    @Override
    public void run() {
        int i = 0;
        // to avoid a deadlock we don't place a barrier for last non full
        // pack of elements. Last full-pack element has next index:
        int lastModifiedWithBarrierElement
                = diagonalFillerService.getMatrixDimension()
                - diagonalFillerService.getMatrixDimension()
                % (numberOfElementToStop * barrier.getParties()) + 1;
        while (diagonalFillerService.getModifiedElements()
                < diagonalFillerService.getMatrixDimension()) {


            diagonalFillerService.modifyNextElement(threadNumber);

            if (++i % numberOfElementToStop == 0
                    && diagonalFillerService.getModifiedElements()
                    < lastModifiedWithBarrierElement) {
                try {
                    barrier.await(1000, TimeUnit.MILLISECONDS);
                } catch (BrokenBarrierException | InterruptedException | TimeoutException e) {
                    logger.error(String.format(STANDARD_ERROR_FORMAT
                            , Thread.currentThread().getName()
                            , e.getMessage()));
                }
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiagonalBarrierFiller)) return false;
        DiagonalBarrierFiller that = (DiagonalBarrierFiller) o;
        return numberOfElementToStop == that.numberOfElementToStop;
    }

    @Override
    public int hashCode() {
        final int hashModifier = 31;
        int result = super.hashCode();
        result += hashModifier * Objects.hash(numberOfElementToStop);
        return result;
    }

    @Override
    public String toString() {
        return super.toString()
                + "DiagonalBarrierFiller{"
                + "numberOfElementToStop=" + numberOfElementToStop
                + ", diagonalFillerService=" + diagonalFillerService
                + ", threadNumber=" + threadNumber
                + "} ";
    }


}
