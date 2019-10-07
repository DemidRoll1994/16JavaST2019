package by.samtsov.task02multithreadmatrix.beans.storage;

import java.util.Arrays;

public class Matrix {
    private int[][] matrix;
    private int dimension;

    public Matrix(int[][] newMatrix) {
        matrix = newMatrix.clone();
        dimension= matrix.length;
    }

    public int[][] getMatrix() {
        int[][] newMatrix= new int[dimension][];
        for (int i = 0; i < dimension; i++) {
            newMatrix[i] = Arrays.copyOf(matrix[i],dimension);
            System.arraycopy( matrix[i], 0, newMatrix[i], 0, dimension );
        }
        return matrix;
    }

    public void setMatrix(int[][] newMatrix) {
        matrix = matrix;
        dimension= matrix.length;
    }

    public void setElement(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public int getElement(int x, int y) {
        return matrix[x][y];
    }

    public int getDimension() {
        return dimension;
    }

}
