package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.service.MatrixService;

public abstract class DiagonalFillerService {

    protected int modifiedElements;

    protected MatrixService matrixService;

    public abstract void modifyNextElement(int threadNumber);

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
