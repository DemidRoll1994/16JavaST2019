package by.samtsov.task02multithreadmatrix.view;

public class Printer {

    public void printMatrix(int[][] matrix) {
        //todo multithread out with StringLineThreads and
        // StringLineThreads.join()
        /*
        String[] resultStrings = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            new Thread() {
                @Override
                public void run() {
                    String resultString= "";
                    for (int j : matrix[i]) {
                        resultString+=j;
                    }
                    resultStrings[i]=resultString;
                }
            }.start();
        }
        */
        for (int[] line : matrix) {
            for (Integer i : line) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
