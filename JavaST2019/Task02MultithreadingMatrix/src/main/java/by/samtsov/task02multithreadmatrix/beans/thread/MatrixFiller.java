package by.samtsov.task02multithreadmatrix.beans.thread;

public class MatrixFiller extends Thread {

    private static final int numberOfRowsToFill = 2;

    private int[][] matrix;
    private int threadNumber;

    public MatrixFiller(int threadNumber, int[][] matrixSource) {
        matrix = matrixSource;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        super.run();
        int matrixLength = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            matrix[numberOfRowsToFill * threadNumber][i] = threadNumber;
            matrix[numberOfRowsToFill * threadNumber + 1][i] = threadNumber;
        }
    }
}
