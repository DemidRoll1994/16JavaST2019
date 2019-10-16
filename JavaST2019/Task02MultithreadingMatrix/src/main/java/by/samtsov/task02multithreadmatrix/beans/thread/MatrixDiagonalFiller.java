package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalFillerService;

import java.util.Objects;

public abstract class MatrixDiagonalFiller extends Thread {

    protected DiagonalFillerService diagonalFillerService;
    protected int threadNumber;

    @Override
    public abstract void run();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatrixDiagonalFiller)) return false;
        MatrixDiagonalFiller that = (MatrixDiagonalFiller) o;
        return threadNumber == that.threadNumber && Objects
                .equals(diagonalFillerService, that.diagonalFillerService);
    }

    @Override
    public int hashCode() {
        final int hashModifier = 31;
        int result = super.hashCode();
        result += hashModifier * Objects.hash(diagonalFillerService);
        result += hashModifier * threadNumber;
        return result;
    }

    @Override
    public String toString() {
        return super.toString()
                + "MatrixDiagonalFiller{"
                + "diagonalFillerService=" + diagonalFillerService
                + ", threadNumber=" + threadNumber
                + "} ";
    }
}
