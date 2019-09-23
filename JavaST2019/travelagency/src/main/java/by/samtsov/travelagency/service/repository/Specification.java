package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.List;

public interface Specification {
    List<Tour> execute(ToursStorage toursStorage);
}
