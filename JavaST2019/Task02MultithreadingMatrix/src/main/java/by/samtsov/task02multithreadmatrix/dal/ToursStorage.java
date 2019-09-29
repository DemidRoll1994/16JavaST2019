package by.samtsov.task02multithreadmatrix.dal;

import java.util.ArrayList;
import java.util.List;

public class ToursStorage {
    private List<Tour> tourList;

    private static ToursStorage instance;

    private ToursStorage() {
    }

    public ToursStorage(List<Tour> tours) {
        tourList = tours;
    }

    public static ToursStorage getInstance() {
        if (instance == null) {
            instance = new ToursStorage();
        }
        return instance;
    }

    public static ToursStorage getInstance(List<Tour> tours) {
        if (instance == null) {
            instance = new ToursStorage(tours);
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

    public List<Tour> selectAll() {
        return new ArrayList<Tour>(tourList);
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
