package by.samtsov.task02multithreadmatrix.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Validator {

    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_DATA_ERROR = "data in file is invalid ";

    public boolean isValid(List<List<Integer>> matrix) {
        if (matrix == null) {
            logger.warn(INVALID_DATA_ERROR + " (matrix is empty)");
            return false;
        }
        return isSquare(matrix) && isMainDiagonalEmpty(matrix);
    }

    private boolean isMainDiagonalEmpty(List<List<Integer>> matrix) {
        //todo Multithread checking
        return false;
    }

    private boolean isSquare(List<List<Integer>> matrix) {
        //todo Multithreading
        int matrixDimension = matrix.size();
        for (List<Integer> line : matrix) {
            if (matrixDimension != line.size()) {
                return false;
            }
        }
        return true;
    }

}
