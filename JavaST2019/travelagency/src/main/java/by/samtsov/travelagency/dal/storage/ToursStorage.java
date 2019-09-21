package by.samtsov.travelagency.dal.storage;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public class ToursStorage {
    private List<Tour> tourList;

    private static ToursStorage instance;

    private ToursStorage() {
    }

    public static ToursStorage getInstance(){
        if (instance == null) {
            instance = new ToursStorage();
        }
        return instance;
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
        if (!tourList.isEmpty()) {
            return remove(get(id));
        }
        return false;
    }

    public boolean remove(Tour tour) {
        if (!tourList.isEmpty()) {
            return tourList.remove(tour);
        }
        return false;
    }

    /**
     * Method will update tour in storage.
     * Unfortunately, in this storage version
     *
     * @return boolean value with <b>true</b> if updated and <b>false</b> if doesn't.
     */
    public boolean update(Tour tour) {
        int tourId = -1;
        for (Tour t : tourList) {
            if (tour.getId() == t.getId()) {
                tourId = tour.getId();
                break;
            }
        }

        if (tourId != -1) {
            tourList.set(tourId, tour);
            return true;
        }
        return false;
    }

}
