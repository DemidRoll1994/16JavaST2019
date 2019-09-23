package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.ArrayList;
import java.util.List;

public class FindBudgetToursSpecification implements Specification {
    /**
     * Tour price.
     */

    private static final double BUDGETPRICE = 200.0;

    /**
     * method execute selection for price.
     * max price = 200.0
     * @param toursStorage - storage that contains all tours
     * @return tours with price less 200.
     */
    public List<Tour> execute(final ToursStorage toursStorage) {
        List<Tour> tourList = new ArrayList<>();
        for (Tour tour : toursStorage.selectAll()) {
            if (tour.getPrice() <= BUDGETPRICE) {
                tourList.add(tour);
            }
        }
        return tourList;
    }
}
