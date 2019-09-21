package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

public class SightseeingTour extends Tour {
    private String country;

    public Tour createTour(String[] values) {
        return new SightseeingTour(Integer.parseInt(values[0]), Double.parseDouble(values[1])
                , Integer.parseInt(values[2]), values[3], Transport.valueOf(values[4]), Feed.valueOf(values[5])
                , (values[6]));
    }

    private SightseeingTour(int id, double price, int duration, String name, Transport transport, Feed feed
            , String country) {
        super(id, price, duration, name, transport, feed);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SightseeingTour)) return false;
        if (!super.equals(o)) return false;

        SightseeingTour that = (SightseeingTour) o;

        return country != null ? country.equals(that.country) : that.country == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SightseeingTour{" + super.toString()
                + "country='" + country + '\''
                + "} ";
    }
}
