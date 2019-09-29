package by.samtsov.task02multithreadmatrix.view;

import java.util.List;

public class Printer {

    public void printTours(List<List<Integer>> matrix) {
        //todo multithread out with StringLineThreads and
        // StringLineThreads.join()

        for (List<Integer> line : matrix) {
            for (Integer i : line){
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
