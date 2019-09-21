package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.HealthCareType;
import by.samtsov.travelagency.beans.enums.Transport;

public class HealthCareTour extends Tour {

    private HealthCareType healthCareType;

    public Tour createTour(String[] values) {
        return new HealthCareTour(Integer.parseInt(values[0]), Double.parseDouble(values[1])
                , Integer.parseInt(values[2]), values[3], Transport.valueOf(values[4]), Feed.valueOf(values[5])
                , HealthCareType.valueOf(values[6]));

    }

    private HealthCareTour(int id, double price, int duration, String name, Transport transport, Feed feed
            , HealthCareType healthCareType) {
        super(id, price, duration, name, transport, feed);
        this.healthCareType = healthCareType;
    }

    public HealthCareType getHealthCareType() {
        return healthCareType;
    }

    public void setHealthCareType(HealthCareType healthCareType) {
        this.healthCareType = healthCareType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthCareTour)) return false;
        if (!super.equals(o)) return false;

        HealthCareTour that = (HealthCareTour) o;

        return healthCareType == that.healthCareType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (healthCareType != null ? healthCareType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HealthCareTour{" + super.toString()
                + "healthCareType=" + healthCareType
                + "} " ;
    }
}
