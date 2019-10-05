package by.samtsov.task02multithreadmatrix.controller;

import by.samtsov.task02multithreadmatrix.beans.thread.DiagonalFiller;
import by.samtsov.task02multithreadmatrix.service.MatrixService;
import by.samtsov.task02multithreadmatrix.view.Printer;

import java.util.ArrayList;
import java.util.List;

public class TESTCONTROLLER {
    public TESTCONTROLLER() {
        /*Matrix matrix = new Matrix(new int[][]{{0,1,2,3,4},{1,0,2,3,4},{1,1,0
                ,3,4},{3,1,2,0,4},{4,1,2,3,0}});
        */
        MatrixService matrixService = new MatrixService(new int[][]{{0, 1, 2, 3, 4}
                , {1, 0, 2, 3, 4}, {1, 1, 0, 3, 4}, {3, 1, 2, 0, 4}, {4, 1, 2, 3, 0}});
        List<DiagonalFiller> diagonalFillers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            diagonalFillers.add(new DiagonalFiller(i + 10
                    , matrixService));
        }
        for (DiagonalFiller d:diagonalFillers) {
            d.start();
        }
        new Printer().printMatrix(matrixService.getMatrix());
    }
}
