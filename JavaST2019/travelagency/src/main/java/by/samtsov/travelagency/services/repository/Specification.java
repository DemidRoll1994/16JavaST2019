package by.samtsov.travelagency.services.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public interface Specification {
    public List<Tour> execute();
}
