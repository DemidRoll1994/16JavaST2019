package by.samtsov.travelagency.service.repository;

import by.samtsov.travelagency.beans.entities.Tour;
import by.samtsov.travelagency.dal.ToursStorage;

import java.util.List;

public class ToursRepositoryImpl implements ToursRepository {

    private static ToursRepositoryImpl instance;
    private static ToursStorage toursStorage;

    private ToursRepositoryImpl() {
        toursStorage = ToursStorage.getInstance();
    }

    public ToursRepositoryImpl(List<Tour> tours) {
        toursStorage = ToursStorage.getInstance(tours);
    }

    public static ToursRepositoryImpl getInstance(List<Tour> tours) {
        if (instance == null) {
            instance = new ToursRepositoryImpl(tours);
        }
        return instance;
    }

    public static ToursRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ToursRepositoryImpl();
        }
        return instance;
    }

    public void create(Tour tour) {
        toursStorage.add(tour);
    }

    public Tour read(int id) {
        return toursStorage.get(id);
    }

    public boolean update(Tour tour) {
        return toursStorage.update(tour);
    }

    public boolean delete(Tour tour) {
        return toursStorage.remove(tour);
    }

    public boolean delete(int id) {
        return toursStorage.remove(id);
    }

    public List<Tour> query(Specification specificaion) {
        return specificaion.execute(toursStorage);
    }
}
