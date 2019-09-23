package by.samtsov.travelagency.entities;

import by.samtsov.travelagency.enums.HealthCareType;

public class HealthCareTour extends Tour {

    private HealthCareType healthCareType;

    public Tour createTour(int id, double price, int duration, String name, HealthCareType healthCareType) {
        return new HealthCareTour(id, price, duration, name, healthCareType);

    }

    private HealthCareTour(int id, double price, int duration, String name, HealthCareType healthCareType) {
        super(id, price, duration, name);
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
        final StringBuilder sb = new StringBuilder("HealthCareTour{");
        sb.append("healthCareType=").append(healthCareType);
        sb.append('}');
        return sb.toString();
    }
}
