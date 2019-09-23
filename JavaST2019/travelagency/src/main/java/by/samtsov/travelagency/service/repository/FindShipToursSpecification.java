package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.beans.enums.Transport;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.ArrayList;
import java.util.List;

public class FindShipToursSpecification implements Specification {

    @Override
    public List<Tour> execute(ToursStorage toursStorage) {
        List<Tour> tourList = new ArrayList<>();
        for (Tour tour : toursStorage.selectAll()) {
            if (tour.getTransport() == Transport.SHIP) {
                tourList.add(tour);
            }
        }
        return tourList;
    }
}
