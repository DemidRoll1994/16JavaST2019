package by.samtsov.task02multithreadmatrix.view;

import java.util.List;

public class Printer {


    public void printTours(List<Tour> tours) {
        for (Tour t : tours) {
            System.out.println(t.toString());
        }
    }
}
