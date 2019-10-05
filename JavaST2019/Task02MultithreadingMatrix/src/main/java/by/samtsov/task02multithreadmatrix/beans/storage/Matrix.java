package by.samtsov.task02multithreadmatrix.beans.storage;

public class Matrix {
    private int[][] matrix;

    public Matrix(int[][] newMatrix) {
        matrix = newMatrix.clone();
    }

    public int[][] getMatrix() {
        //todo return new matrix
        return matrix;
    }

    public void setMatrix(int[][] newMatrix) {
        matrix = matrix;
    }

    public void setElement(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public int getElement(int x, int y) {
        return matrix[x][y];
    }
}
