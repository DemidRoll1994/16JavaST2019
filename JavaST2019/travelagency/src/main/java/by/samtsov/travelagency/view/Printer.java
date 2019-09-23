package by.samtsov.travelagency.view;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public class Printer {


    public void printTours(List<Tour> tours) {
        for (Tour t : tours) {
            System.out.println(t.toString());
        }
    }
}
