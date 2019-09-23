package by.samtsov.travelagency.service;

import by.samtsov.travelagency.beans.entities.*;
import by.samtsov.travelagency.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TourCreator {

    private static final Logger logger = LogManager.getLogger();
    private static int maxId = 0; // 0 - is initial value

    public TourCreator() {
    }

    private static String nextId() {
        return Integer.toString(maxId++);
    }

    public List<Tour> selectCorrectValues(List<List<String>> lines) {
        List<Tour> tours = new ArrayList<>();
        Validator validator = new Validator();
        logger.debug("----creating objects------");
        for (List<String> line : lines) {
            logger.debug("object " + line + " is correct :" + validator.isValid(line));
            if (validator.isValid(line)) {
                Tour tour;
                tour = createTour(line);
                line.set(0, nextId());
                tours.add(tour.createTour(line));
            }
        }
        logger.debug("----correct objects are created------");
        return tours;
    }

    private Tour createTour(List<String> line) {
        if (line.get(0).equalsIgnoreCase("Cruise")) {
            return new CruiseTour();
        } else if (line.get(0).equalsIgnoreCase("HealthCare")) {
            return new HealthCareTour();
        } else if (line.get(0).equalsIgnoreCase("Relax")) {
            return new RelaxTour();
        } else if (line.get(0).equalsIgnoreCase("Shopping")) {
            return new ShoppingTour();
        } else if (line.get(0).equalsIgnoreCase("Sightseeing")) {
            return new SightseeingTour();
        }
        logger.error("validator is incorrect. line[0] \" " + line.get(0) + "\"can't be convert to tour type");
        return new RelaxTour();
    }
}
