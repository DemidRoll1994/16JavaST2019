package by.samtsov.task02multithreadmatrix.service;

import by.samtsov.task02multithreadmatrix.beans.storage.Matrix;
import by.samtsov.task02multithreadmatrix.service.reader.FileReader;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixService {

    Matrix innerMatrix;

    ReentrantLock locker = new ReentrantLock(); // создаем заглушку
    //Condition condition = locker.newCondition(); // получаем условие,
    // связанное


    public void initializeMatrixFromFile(String path, String delimiterInFile)
            throws IOException {
        String[] lines = new FileReader().read(path);
        int[][] valuesInLines = new Parser().parseLinesToInteger(lines
                , delimiterInFile);
        innerMatrix = new Matrix(valuesInLines);
    }

    public void modifyElementWithLock(int x, int y, int value) {
        locker.lock();
        if (innerMatrix.getElement(x, y) == 0) {
            System.out.println(" элемент (" + x + "," + y + ")  свободен!");
            //condition.signalAll();

        }
        locker.unlock();
        System.out.println("Я - поток " + Thread.currentThread().getName() + "и " +
                "я модернизирую элемент (" + x + ", " + y + ") значением " + value);
        innerMatrix.setElement(x, y, value);
    }

    public int[][] getMatrix() {
        return innerMatrix.getMatrix();
    }
}
