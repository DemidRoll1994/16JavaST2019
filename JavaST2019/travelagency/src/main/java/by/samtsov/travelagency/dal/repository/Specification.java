package by.samtsov.travelagency.dal.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public interface Specification {
    public List<Tour> execute();
}
