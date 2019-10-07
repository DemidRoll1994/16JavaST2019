package by.samtsov.task02multithreadmatrix.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class DiagonalFillerService {

    ReentrantLock locker = new ReentrantLock(); // создаем заглушку
    //Condition condition = locker.newCondition(); // получаем условие,
    // связанное

    private static final Logger logger = LogManager.getLogger();

    int modifiedElements=0;

    private MatrixService matrixService ;

    public DiagonalFillerService(MatrixService newMatrixService){
        matrixService= newMatrixService;
    }

    public void modifyElementWithLock(int x, int y, int value) {
        locker.lock();
        matrixService.assignElementToMatrix(x, y, value);
        locker.unlock();
    }

    public void modifyNextElement(int value) {
        int modifyingElement=0;
        locker.lock();
        if(modifiedElements<matrixService.getMatrixDimension()) {
            modifyingElement = modifiedElements++;
        }
        locker.unlock();
        try {
        matrixService.assignElementToMatrix(modifyingElement,
                modifyingElement, value);}
        catch (IllegalArgumentException iae){
            logger.warn(iae.getMessage());
        }
    }

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
}
