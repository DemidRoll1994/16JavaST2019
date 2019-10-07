package by.samtsov.task02multithreadmatrix.view;

public class Printer {

    public void printMatrix(int[][] matrix) {

        for (int[] line : matrix) {
            for (Integer i : line) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println("\n--------------------------------------\n");
    }
}
