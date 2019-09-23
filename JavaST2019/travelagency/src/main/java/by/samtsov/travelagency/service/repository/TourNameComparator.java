package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.Comparator;

public class TourNameComparator implements Comparator<Tour> {

    public int compare(Tour a, Tour b) {

        return b.getName().compareTo(a.getName());
    }
}
