package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.List;

public class SortByPriceAndNameSpecification implements Specification {


    @Override
    public List<Tour> execute(ToursStorage toursStorage) {
        List<Tour> list = toursStorage.selectAll();
        list.sort(new TourIdComparator().thenComparing(new TourNameComparator()));
        return list;
    }
}
