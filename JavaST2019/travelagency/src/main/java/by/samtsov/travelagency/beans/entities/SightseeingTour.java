package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

import java.util.List;

public class SightseeingTour extends Tour {
    private String country;

    public SightseeingTour() {
    }

    private SightseeingTour(final int id, final double price,
                            final int duration, final String name,
                            final Transport transport, final Feed feed,
                            final String country1) {
        super(id, price, duration, name, transport, feed);
        this.country = country1;
    }

    public Tour createTour(final List<String> values) {
        return new SightseeingTour(Integer.parseInt(values.get(0)),
                Double.parseDouble(values.get(1)),
                Integer.parseInt(values.get(2)), values.get(3),
                Transport.valueOf(values.get(4).toUpperCase()),
                Feed.valueOf(values.get(5).toUpperCase()), (values.get(6)));
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SightseeingTour)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        SightseeingTour that = (SightseeingTour) o;

        return country != null
                ? country.equals(that.country) : that.country == null;
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
