package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.ArrayList;
import java.util.List;

public class FindBudgetToursSpecification implements Specification {
    private static final double BUDGETPRICE = 200.0;


    public List<Tour> execute(ToursStorage toursStorage) {
        List<Tour> tourList = new ArrayList<>();
        for (Tour tour : toursStorage.selectAll()) {
            if (tour.getPrice() <= BUDGETPRICE) {
                tourList.add(tour);
            }
        }
        return tourList;
    }
}
