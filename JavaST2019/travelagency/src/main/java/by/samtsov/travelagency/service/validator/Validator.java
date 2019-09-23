package by.samtsov.travelagency.service.validator;

import by.samtsov.travelagency.beans.enums.CurrencyEnum;
import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.HealthCareType;
import by.samtsov.travelagency.beans.enums.Transport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Validator {

    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_DATA_ERROR = "data in file is invalid ";

    public boolean isValid(List<String> line) {
        if (line == null) {
            logger.warn(INVALID_DATA_ERROR + " (null-line)");
            return false;
        }
        if (line.size() < 6) {
            logger.warn(INVALID_DATA_ERROR + " (less then 6 parameters)");
            return false;
        }
        if (line.get(0).equalsIgnoreCase("Cruise")) {
            return isValidTour(line) && isValidCruiseTour(line);
        } else if (line.get(0).equalsIgnoreCase("HealthCare")) {
            return isValidTour(line) && isValidHealthCareTour(line);
        } else if (line.get(0).equalsIgnoreCase("Relax")) {
            return isValidTour(line) && isValidRelaxTour(line);
        } else if (line.get(0).equalsIgnoreCase("Shopping")) {
            return isValidTour(line) && isValidShoppingTour(line);
        } else if (line.get(0).equalsIgnoreCase("Sightseeing")) {
            return isValidTour(line) && isValidSightseeingTour(line);
        }

        logger.warn(String.format("type of tour %s is incorrect!",line.toString()));
        return false;
    }

    private boolean isValidTour(List<String> line) {
        try {
            Transport.valueOf(line.get(4).toUpperCase());
            Feed.valueOf(line.get(5).toUpperCase());
            if (Double.parseDouble(line.get(1)) < 0 || Integer.parseInt(line.get(2)) < 1) {
                logger.warn("price less then 0 or duration less then 1. tour can't be exist!");
                return false;
            }
        } catch (Exception e) {
            logger.warn(String.format("%s %s", INVALID_DATA_ERROR, e));
            return false;
        }
        return true;
    }

    private boolean isValidCruiseTour(List<String> line) {
        try {
            boolean isBoolean = line.get(7).equalsIgnoreCase("true")
                    || line.get(7).equalsIgnoreCase("false");
            if (!isBoolean) {
                logger.warn("value is not boolean");
            }
            return isBoolean;
        } catch (Exception e) {
            logger.warn(String.format("%s %s", INVALID_DATA_ERROR, e));
            return false;
        }
    }

    private boolean isValidHealthCareTour(List<String> line) {
        try {
            HealthCareType.valueOf(line.get(6).toUpperCase());
        } catch (Exception e) {
            logger.warn(String.format("%s %s", INVALID_DATA_ERROR, e));
            return false;
        }
        return true;
    }

    private boolean isValidRelaxTour(List<String> line) {
        try {
            boolean isBoolean = line.get(7).equalsIgnoreCase("true")
                    || line.get(7).equalsIgnoreCase("false");
            if (!isBoolean) {
                logger.warn("value is not boolean");
            }
            return isBoolean;
        } catch (Exception e) {
            logger.warn(String.format("%s %s", INVALID_DATA_ERROR, e));
            return false;
        }
    }

    private boolean isValidShoppingTour(List<String> line) {
        try {
            CurrencyEnum.valueOf(line.get(6).toUpperCase());
        } catch (Exception e) {
            logger.warn(String.format("%s %s", INVALID_DATA_ERROR, e));
            return false;
        }
        return true;
    }

    private boolean isValidSightseeingTour(List<String> line) {
        return true;
    }

}
