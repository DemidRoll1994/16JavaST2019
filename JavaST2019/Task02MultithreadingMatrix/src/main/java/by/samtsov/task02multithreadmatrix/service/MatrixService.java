package by.samtsov.task02multithreadmatrix.service;

import by.samtsov.task02multithreadmatrix.beans.storage.Matrix;
import by.samtsov.task02multithreadmatrix.service.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MatrixService {

    Matrix innerMatrix;

    private static final Logger logger = LogManager.getLogger();

    public void initializeMatrixFromFile(String path, String delimiterInFile)
            throws IOException {
        String[] lines = new FileReader().read(path);
        int[][] valuesInLines = new Parser().parseLinesToInteger(lines
                , delimiterInFile);
        innerMatrix = new Matrix(valuesInLines);
    }


    public int getMatrixDimension() {
        return innerMatrix.getDimension();
    }

    public int[][] getMatrix() {
        return innerMatrix.getMatrix();
    }

    public void assignElementToMatrix(int x, int y, int value)
            throws IllegalArgumentException {
        if (x >= 0 && y >= 0) {
            innerMatrix.setElement(x, y, value);
        }else{
            throw new IllegalArgumentException();
        }
    }
}
