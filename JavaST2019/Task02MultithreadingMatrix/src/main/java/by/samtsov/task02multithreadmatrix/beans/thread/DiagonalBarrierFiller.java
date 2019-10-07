package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalBarrierFillerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;

public class DiagonalBarrierFiller extends MatrixDiagonalFiller {

    private static final Logger logger = LogManager.getLogger();

    private static final String STANDARD_ERROR_FORMAT = "%s %s";
    private int numberOfElementToStop;

    public DiagonalBarrierFiller(int newThreadNumber, DiagonalBarrierFillerService
            diagonalBarrierFillerService, int newNumberOfElementToStop) {
        threadNumber = newThreadNumber;
        diagonalFillerService = diagonalBarrierFillerService;
        numberOfElementToStop = newNumberOfElementToStop;
    }

    @Override
    public void run() {
        int i = 0;
        while (diagonalFillerService.getModifiedElements()
                < diagonalFillerService.getMatrixDimension()) {
            diagonalFillerService.modifyNextElement(threadNumber);

            if (++i % numberOfElementToStop == 0
                    || diagonalFillerService.getModifiedElements()
                    == diagonalFillerService.getMatrixDimension() - 1) {
                try {
                    ((DiagonalBarrierFillerService) diagonalFillerService)
                            .getBarrier().await();
                } catch (BrokenBarrierException | InterruptedException e) {
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
        int result = super.hashCode();
        result = 31 * Objects.hash(numberOfElementToStop);
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
