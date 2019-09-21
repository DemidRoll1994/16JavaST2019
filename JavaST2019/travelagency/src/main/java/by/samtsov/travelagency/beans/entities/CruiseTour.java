package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

public class CruiseTour extends Tour {
    private String country;
    private boolean onSea;

    public Tour createTour(String[] values) {
        return new CruiseTour(Integer.parseInt(values[0]), Double.parseDouble(values[1]), Integer.parseInt(values[2])
                , values[3], Transport.valueOf(values[4]), Feed.valueOf(values[5]), values[6]
                , Boolean.getBoolean(values[7]));

    }

    public CruiseTour(int id, double price, int duration, String name, Transport transport, Feed feed, String country
            , boolean onSea) {
        super(id, price, duration, name, transport, feed);
        this.country = country;
        this.onSea = onSea;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isOnSea() {
        return onSea;
    }

    public void setOnSea(boolean onSea) {
        this.onSea = onSea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CruiseTour)) return false;
        if (!super.equals(o)) return false;

        CruiseTour that = (CruiseTour) o;

        if (onSea != that.onSea) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
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
