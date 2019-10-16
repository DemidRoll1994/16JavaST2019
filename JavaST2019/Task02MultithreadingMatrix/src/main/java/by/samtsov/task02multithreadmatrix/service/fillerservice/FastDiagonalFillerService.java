package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.service.MatrixService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class FastDiagonalFillerService extends DiagonalFillerService{

    ReentrantLock locker = new ReentrantLock();

    private static final Logger logger = LogManager.getLogger();


    public FastDiagonalFillerService(MatrixService newMatrixService) {
        matrixService = newMatrixService;
        modifiedElements = 0;
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
