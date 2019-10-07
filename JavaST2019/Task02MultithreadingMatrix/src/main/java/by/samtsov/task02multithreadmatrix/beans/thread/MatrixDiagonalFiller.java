package by.samtsov.task02multithreadmatrix.beans.thread;

import by.samtsov.task02multithreadmatrix.service.fillerservice.DiagonalFillerService;

public abstract class MatrixDiagonalFiller extends Thread {

    protected DiagonalFillerService diagonalFillerService;
    protected int threadNumber;
    @Override
    public abstract void run();

}
