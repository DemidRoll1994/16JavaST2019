package by.samtsov.task02multithreadmatrix.beans.storage;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private List<List<Integer>> matrix1;

    public Matrix(List<List<Integer>> matrix) {
        matrix1 = new ArrayList<>(matrix);
    }

    public List<List<Integer>> getMatrix() {
        return matrix1;
    }

    public void setMatrix(List<List<Integer>> matrix) {
        matrix1 = matrix;
    }
}
