package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

import java.util.List;

public abstract class Tour {
    /**
     * A tour identifier.
     */
    private int id;
    /**
     * A tour price.
     */
    private double price;
    /**
     * A tour duration in days.
     */
    private int duration;
    /**
     * A tour name.
     */
    private String name;
    /**
     * A tour transport.
     */
    private Transport transport;
    /**
     * A tour feed.
     */
    private Feed feed;

    /**
     * Empty constructor.
     */
    public Tour() {

    }

    /**
     * Full-parameter constructor.
     *
     * @param id1        - Tour id
     * @param price1     - Tour price
     * @param duration1  - Tour duration
     * @param name1      - Tour name
     * @param transport1 - Tour transport
     * @param feed1      - Tour feed
     * @see Transport enum
     * @see Feed enum
     */
    public Tour(final int id1, final double price1, final int duration1,
                final String name1, final Transport transport1,
                final Feed feed1) {
        this.id = id1;
        this.price = price1;
        this.duration = duration1;
        this.name = name1;
        this.transport = transport1;
        this.feed = feed1;
    }

    /**
     * Factory method.
     *
     * @param strings - list of input values for creating object
     * @return tour implementation.
     */

    public abstract Tour createTour(List<String> strings);

    /**
     * Get-method for id.
     *
     * @return Tour Id
     */
    public int getId() {
        return id;
    }

    /**
     * Set-method for id.
     *
     * @param id1 value to set
     */
    public void setId(final int id1) {
        this.id = id1;
    }

    /**
     * Get-method for price.
     *
     * @return Tour price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set-method for price.
     *
     * @param price1 value to set
     */
    public void setPrice(final double price1) {
        this.price = price1;
    }

    /**
     * Get-method for duration.
     *
     * @return Tour duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set-method for duration.
     *
     * @param duration1 value to set
     */
    public void setDuration(final int duration1) {
        this.duration = duration1;
    }

    /**
     * Get-method for name.
     *
     * @return Tour name
     */
    public String getName() {
        return name;
    }

    /**
     * Set-method for name.
     *
     * @param name1 value to set
     */
    public void setName(final String name1) {
        this.name = name1;
    }

    /**
     * Get-method for Transport.
     *
     * @return Tour transport
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * Set-method for Transport.
     *
     * @param transport1 - a value to set
     * @see Transport enum.
     */
    public void setTransport(final Transport transport1) {
        this.transport = transport1;
    }

    /**
     * Get-method for Feed.
     *
     * @return Tour feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Set-method for Feed.
     *
     * @param feed1 - a value to set
     * @see Feed enum.
     */
    public void setFeed(final Feed feed1) {
        this.feed = feed1;
    }

    /**
     * equals-method for Tour.
     *
     * @param o - comparing object
     * @see Object.equals() method.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tour)) {
            return false;
        }
        Tour tour = (Tour) o;
        if (duration != tour.duration) {
            return false;
        }
        if (id != tour.id) {
            return false;
        }
        if (Double.compare(tour.price, price) != 0) {
            return false;
        }
        if (feed != tour.feed) {
            return false;
        }
        if (name != null ? !name.equals(tour.name) : tour.name != null) {
            return false;
        }
        return transport == tour.transport;
    }

    /**
     * hashcode method.
     *
     * @return hashcode of object.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + duration;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        result = 31 * result + (feed != null ? feed.hashCode() : 0);
        return result;
    }

    /**
     * toString method.
     *
     * @return String with all parameters of object.
     */
    @Override
    public String toString() {
        return "Tour{"
                + "id=" + id
                + ", price=" + price
                + ", duration=" + duration
                + ", name='" + name + '\''
                + ", transport=" + transport
                + ", feed=" + feed
                + '}';
    }

}
