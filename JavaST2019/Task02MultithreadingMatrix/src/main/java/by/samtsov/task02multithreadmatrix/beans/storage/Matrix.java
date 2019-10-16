package by.samtsov.task02multithreadmatrix.beans.storage;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    /**
     * Array to store a matrix values.
     */
    private int[][] intMatrix;
    /**
     * Dimension of matrix.
     */
    private int dimension;

    /**
     * Constructor for initialization matrix.
     *
     * @param newMatrix matrix of init-values
     */
    public Matrix(final int[][] newMatrix) {
        intMatrix = newMatrix.clone();
        dimension = intMatrix.length;
    }

    /**
     * accessor for field intMatrix.
     *
     * @return copy of matrix array.
     */

    public int[][] getIntMatrix() {
        int[][] newMatrix = new int[dimension][];
        for (int i = 0; i < dimension; i++) {
            newMatrix[i] = Arrays.copyOf(intMatrix[i], dimension);
            System.arraycopy(intMatrix[i], 0, newMatrix[i], 0, dimension);
        }
        return intMatrix;
    }

    /**
     * accessor for field intMatrix.
     *
     * @param newMatrix array with new values for matrix
     */
    public void setIntMatrix(final int[][] newMatrix) {
        intMatrix = newMatrix;
        dimension = intMatrix.length;
    }

    /**
     * set @param value in position @param x, @param y.
     *
     * @param x     - x-position
     * @param y     - y-position
     * @param value - element value
     */
    public void setElement(final int x, final int y, final int value) {
        intMatrix[x][y] = value;
    }

    /**
     * get element at position x, y.
     *
     * @param x - x-position
     * @param y - y-position
     * @return element at position x,y
     */

    public int getElement(final int x, final int y) {
        return intMatrix[x][y];
    }

    /**
     * Field dimension getter.
     *
     * @return value of field  dimension.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * method that compares two objects.
     *
     * @param o comparing object
     * @return true is object in param o is equal this object
     */

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        return dimension == matrix.dimension
                && Arrays.equals(intMatrix, matrix.intMatrix);
    }

    /**
     * return string with Object state.
     *
     * @return string with parameter values.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Matrix{"
                + "intMatrix=" + Arrays.toString(intMatrix)
                + ", dimension=" + dimension
                + '}';
    }

    /**
     * @return hashcode in int range.
     */
    @Override
    public int hashCode() {
        final int hashModifier = 31;
        int result = super.hashCode();
        result += hashModifier * Objects.hash(dimension);
        result += hashModifier * Arrays.hashCode(intMatrix);
        return result;
    }
}
