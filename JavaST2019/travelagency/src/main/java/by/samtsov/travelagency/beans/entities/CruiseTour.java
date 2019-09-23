package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

import java.util.List;

public class CruiseTour extends Tour {
    private String country;
    private boolean onSea;

    public CruiseTour() {
        super();
    }



    public CruiseTour(final int id, final double price, final int duration,
                      final String name, final Transport transport,
                      final Feed feed, final String country1,
                      final boolean onSea1) {
        super(id, price, duration, name, transport, feed);
        this.country = country1;
        this.onSea = onSea1;
    }

    public Tour createTour(final List<String> values) {
        return new CruiseTour(Integer.parseInt(values.get(0)),
                Double.parseDouble(values.get(1)),
                Integer.parseInt(values.get(2)),
                values.get(3), Transport.valueOf(values.get(4).toUpperCase()),
                Feed.valueOf(values.get(5).toUpperCase()), values.get(6),
                Boolean.getBoolean(values.get(7)));

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country1) {
        this.country = country1;
    }

    public boolean isOnSea() {
        return onSea;
    }

    public void setOnSea(final boolean onSea1) {
        this.onSea = onSea1;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CruiseTour)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CruiseTour that = (CruiseTour) o;

        if (onSea != that.onSea) {
            return false;
        }
        return country != null
                ? country.equals(that.country) : that.country == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (onSea ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CruiseTour{" + super.toString()
                + "country='" + country + '\''
                + ", onSea=" + onSea
                + "} ";
    }
}
