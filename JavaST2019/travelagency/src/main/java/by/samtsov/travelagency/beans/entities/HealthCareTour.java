package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.HealthCareType;
import by.samtsov.travelagency.beans.enums.Transport;

import java.util.List;

public class HealthCareTour extends Tour {

    private HealthCareType healthCareType;

    public HealthCareTour() {
    }

    private HealthCareTour(final int id, final double price, final int duration,
                           final String name, final Transport transport,
                           final Feed feed,
                           final HealthCareType healthCareType1) {
        super(id, price, duration, name, transport, feed);
        this.healthCareType = healthCareType1;
    }

    public Tour createTour(final List<String> values) {
        return new HealthCareTour(Integer.parseInt(values.get(0)),
                Double.parseDouble(values.get(1)),
                Integer.parseInt(values.get(2)), values.get(3),
                Transport.valueOf(values.get(4).toUpperCase()),
                Feed.valueOf(values.get(5).toUpperCase()),
                HealthCareType.valueOf(values.get(6).toUpperCase()));
    }

    public HealthCareType getHealthCareType() {
        return healthCareType;
    }

    public void setHealthCareType(final HealthCareType healthCareType1) {
        this.healthCareType = healthCareType1;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HealthCareTour)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        HealthCareTour that = (HealthCareTour) o;

        return healthCareType == that.healthCareType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (healthCareType != null
                ? healthCareType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HealthCareTour{" + super.toString()
                + "healthCareType=" + healthCareType
                + "} ";
    }
}
