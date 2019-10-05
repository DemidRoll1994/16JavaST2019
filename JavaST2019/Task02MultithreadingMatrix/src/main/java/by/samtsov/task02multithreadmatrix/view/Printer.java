package by.samtsov.task02multithreadmatrix.view;

import java.util.concurrent.TimeUnit;

public class Printer {

    public void printMatrix(int [][] matrix) {
        //todo multithread out with StringLineThreads and
        // StringLineThreads.join()
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int[] line : matrix) {
            for (Integer i : line){
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
