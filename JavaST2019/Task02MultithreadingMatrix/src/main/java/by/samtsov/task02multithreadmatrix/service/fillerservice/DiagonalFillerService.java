package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.service.MatrixService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public abstract class DiagonalFillerService {

    protected int modifiedElements;

    protected MatrixService matrixService;

    ReentrantLock locker = new ReentrantLock();

    private static final Logger logger = LogManager.getLogger();

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
        int modifyingElement = 0;
        locker.lock();
        if (modifiedElements < matrixService.getMatrixDimension()) {
            modifyingElement = modifiedElements++;
        }
        locker.unlock();
        try {
            matrixService.assignElementToMatrix(modifyingElement,
                    modifyingElement, value);
        } catch (IllegalArgumentException iae) {
            logger.warn(iae.getMessage());
        }
    }

}
