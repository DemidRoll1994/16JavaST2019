package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public interface ToursRepository {
    void create(Tour tour);

    Tour read(int id);

    boolean update(Tour tour);

    boolean delete(Tour tour);

    List<Tour> query(Specification specification);


}
