package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.Comparator;

public class TourIdComparator implements Comparator<Tour> {

    public int compare(Tour a, Tour b) {
        if (a.getId() > b.getId())
            return 1;
        else if (a.getId() < b.getId())
            return -1;
        else
            return 0;
    }
}

