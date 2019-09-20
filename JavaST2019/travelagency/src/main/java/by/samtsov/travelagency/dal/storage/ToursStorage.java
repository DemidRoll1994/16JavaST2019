package by.samtsov.travelagency.dal.storage;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public class ToursStorage {
    private List<Tour> tourList;

    public ToursStorage(List<Tour> tours) {
        tourList = tours;
    }

    public Tour get(int id) {
        for (Tour tour : tourList) {
            if (tour.getId() == id) {
                return tour;
            }
        }
        return null;
    }

    public void add(Tour tour) {
        tourList.add(tour);
    }

    public boolean remove(int id) {
        return remove(get(id));
    }

    public boolean remove(Tour tour) {
        return tourList.remove(tour);
    }

    public boolean update(Tour tour) {

        tourList.set(idOf(tour), tour);

    }

    private int idOf(Tour tour) {
        for (Tour t : tourList) {
            if (tour.getId() == t.getId()) {
                return tour.getId();
            }
        }
        return -1;
    }
}
