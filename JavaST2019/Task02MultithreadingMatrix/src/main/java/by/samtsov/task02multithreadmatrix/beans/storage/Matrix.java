package by.samtsov.task02multithreadmatrix.beans.storage;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private int[][] intMatrix;
    private int dimension;

    public Matrix(int[][] newMatrix) {
        intMatrix = newMatrix.clone();
        dimension = intMatrix.length;
    }

    public int[][] getIntMatrix() {
        int[][] newMatrix = new int[dimension][];
        for (int i = 0; i < dimension; i++) {
            newMatrix[i] = Arrays.copyOf(intMatrix[i], dimension);
            System.arraycopy(intMatrix[i], 0, newMatrix[i], 0, dimension);
        }
        return intMatrix;
    }

    public void setIntMatrix(int[][] newMatrix) {
        intMatrix = newMatrix;
        dimension = intMatrix.length;
    }

    public void setElement(int x, int y, int value) {
        intMatrix[x][y] = value;
    }

    public int getElement(int x, int y) {
        return intMatrix[x][y];
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix matrix = (Matrix) o;
        return dimension == matrix.dimension &&
                Arrays.equals(intMatrix, matrix.intMatrix);
    }

    @Override
    public String toString() {
        return super.toString()
                + "Matrix{"
                + "intMatrix=" + Arrays.toString(intMatrix)
                + ", dimension=" + dimension
                + '}';
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * Objects.hash(dimension);
        result = 31 * result + Arrays.hashCode(intMatrix);
        return result;
    }
}
