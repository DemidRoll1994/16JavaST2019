package by.samtsov.matrix.controller;

import by.samtsov.matrix.view.MatrixPrinter;

import java.util.ArrayList;

public class MatrixFiller {

    private static final int NUMBER_OF_ROWS_TO_FILL =2;

    private int[][] matrix;

    public MatrixFiller(int n) {
        matrix = initializeMatrix(n);
        fillTheMatrix(n);
        new MatrixPrinter().print(matrix);
    }

    private void fillTheMatrix(int n) {
        for (int i = 0; i < n/NUMBER_OF_ROWS_TO_FILL; i++) {
            new MatrixFillThread(i, matrix).start();
        }
    }

    private int[][] initializeMatrix(int n) {
        return new int[n][n];
    }
}
