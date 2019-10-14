package by.samtsov.task02multithreadmatrix.service.fillerservice;

import by.samtsov.task02multithreadmatrix.service.MatrixService;

public class FastDiagonalFillerService extends DiagonalFillerService{


    public FastDiagonalFillerService(MatrixService newMatrixService) {
        matrixService = newMatrixService;
        modifiedElements = 0;
    }


}
