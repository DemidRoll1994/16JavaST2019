package by.samtsov.task02multithreadmatrix.service;

import by.samtsov.task02multithreadmatrix.beans.storage.Matrix;
import by.samtsov.task02multithreadmatrix.service.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MatrixService {
    /**
     * TestNG logger.
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * standard error format string. used for logging.
     */
    private static final String STANDARD_ERROR_FORMAT = "%s %s";
    /**
     * error code of Index Out Of Bound exception. appears if coordinates
     * less than 0.
     */
    private static final String INDEXOUTOFBOUNDEXCEPTION = "IndexOutOfBound "
            + "exception ";
    /**
     * matrix class instance.
     */
    Matrix innerMatrix;

    /**
     * Method initialize matrix from file.
     *
     * @param path            - local path to file
     * @param delimiterInFile - delimiter, that devide values in line
     * @throws IOException if file does not exist or it's blocked etc.
     */
    public void initializeMatrixFromFile(String path, String delimiterInFile)
            throws IOException {
        String[] lines = new FileReader().read(path);
        int[][] valuesInLines = new Parser().parseLinesToInteger(lines
                , delimiterInFile);
        innerMatrix = new Matrix(valuesInLines);
    }

    /**
     * returns dimension of main array in matrix.
     *
     * @return dimension of matrix.
     */
    public int getMatrixDimension() {
        return innerMatrix.getDimension();
    }

    /**
     * return copy of matrix.
     *
     * @return copy of matrix
     */
    public int[][] getMatrix() {
        return innerMatrix.getIntMatrix();
    }

    /**
     * set value at element with coordinates x,y.
     *
     * @param x     - coordinate of inserting element by x
     * @param y-    coordinate of inserting element by y
     * @param value - inserting value
     */
    public void assignElementToMatrix(int x, int y, int value) {
        if (x >= 0 && y >= 0) {
            innerMatrix.setElement(x, y, value);
        } else {
            logger.warn(String.format(STANDARD_ERROR_FORMAT,
                    Thread.currentThread().getName(), INDEXOUTOFBOUNDEXCEPTION));
        }
    }
}
