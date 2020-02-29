package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.service.MatrixService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class DiagonalFillerService {

    protected final String STANDARD_ERROR_FORMAT = "%s %s";
    protected final Logger logger = LogManager.getLogger();
    protected int[] fillerNumbers;
    protected int modifiedElements;
    protected MatrixService matrixService;
    ReentrantLock locker = new ReentrantLock();

    //public abstract void modifyNextElement(int threadNumber);

    public int getModifiedElements() {
        return modifiedElements;
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }

    public void setMatrixService(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    public int getMatrixDimension() {
        return matrixService.getMatrixDimension();
    }

    public void modifyNextElement(int value) {

        locker.lock();
        int modifyingElement = 0;
        if (modifiedElements < matrixService.getMatrixDimension()) {
            modifyingElement = modifiedElements++;
        }
        locker.unlock();
        try {
            TimeUnit.MILLISECONDS.sleep((int)(100.0 * new Random().nextDouble()));
            matrixService.assignElementToMatrix(modifyingElement,
                    modifyingElement, value);
        } catch (IllegalArgumentException iae) {
            logger.warn(iae.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void fillMatrix();

}
