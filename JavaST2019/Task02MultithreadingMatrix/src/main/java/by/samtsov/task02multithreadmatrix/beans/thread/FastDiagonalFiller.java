package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.FastDiagonalFillerService;

public class FastDiagonalFiller extends MatrixDiagonalFiller {

    public FastDiagonalFiller(int newThreadNumber,
                              FastDiagonalFillerService fastDiagonalFillerService) {
        threadNumber = newThreadNumber;
        diagonalFillerService = fastDiagonalFillerService;
    }

    @Override
    public void run() {
        while (diagonalFillerService.getModifiedElements()
                < diagonalFillerService.getMatrixDimension()) {
            diagonalFillerService.modifyNextElement(threadNumber);
        }
    }

    @Override
    public String toString() {
        return super.toString()
                + "FastDiagonalFiller{"
                +"diagonalFillerService=" + diagonalFillerService
                +", threadNumber=" + threadNumber
                +"} ";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
