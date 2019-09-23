package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.List;

public class SelectAllSpecification implements Specification {

    @Override
    public List<Tour> execute(ToursStorage toursStorage) {
        return toursStorage.selectAll();
    }
}
