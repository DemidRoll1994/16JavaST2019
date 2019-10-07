package by.samtsov.task02multithreadmatrix.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {

    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_DATA_ERROR = "data in file is invalid ";
    private static final String EMPTY_MATRIX_ERROR = INVALID_DATA_ERROR
            +" (matrix is empty)";
    private static final String NON_ZERO_MAIN_DIAGONAL_ERROR
            = INVALID_DATA_ERROR + " (matrix main diagonal contains non-zero "
            + "values)";
    private static final String NOT_SQUARE_MATRIX_ERROR = INVALID_DATA_ERROR
            + " (matrix is not square)";

    public boolean isValid(int[][] matrix) {

        return isInitialized(matrix) && isSquare(matrix) && isMainDiagonalEmpty(matrix);
    }

    private boolean isInitialized(int[][] matrix) {
        if (matrix == null) {
            logger.warn(EMPTY_MATRIX_ERROR);
            return false;
        }
        return true;
    }

    private boolean isMainDiagonalEmpty(int[][] matrix) {
        final int expectedValue = 0;
        int matrixDimension = matrix.length;
        for (int i = 0; i < matrixDimension; i++) {
            if (matrix[i][i] != expectedValue) {
                logger.warn(NON_ZERO_MAIN_DIAGONAL_ERROR );
                return false;
            }
        }
        return true;
    }

    private boolean isSquare(int[][] matrix) {
        int matrixDimension = matrix.length;
        for (int[] line : matrix) {
            if (matrixDimension != line.length) {
                logger.warn(NOT_SQUARE_MATRIX_ERROR);
                return false;
            }
        }
        return true;
    }

}
