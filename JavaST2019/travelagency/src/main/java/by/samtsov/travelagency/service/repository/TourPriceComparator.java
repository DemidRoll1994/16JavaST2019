package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.Comparator;

public class TourPriceComparator implements Comparator<Tour> {

    public int compare(Tour a, Tour b) {
        if (a.getPrice() > b.getPrice())
            return 1;
        else if (a.getPrice() < b.getPrice())
            return -1;
        else
            return 0;
    }
}
