package by.samtsov.travelagency.dal.repository;

import by.samtsov.travelagency.beans.entities.Tour;

import java.util.List;

public interface ToursRepository {
    interface MemberRepository {
        public int create(Tour tour);
        public Tour get(String Id);
        public int update(Tour tour);
        public int delete(Tour tour);
        public List<Tour> query(Specification specificaion);


    }
}
