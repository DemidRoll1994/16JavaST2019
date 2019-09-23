package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

import java.util.List;

public class RelaxTour extends Tour {

    private boolean onSea;

    public RelaxTour() {
    }

    private RelaxTour(final int id, final double price, final int duration,
                      final String name, final Transport transport,
                      final Feed feed, final boolean onSea1) {
        super(id, price, duration, name, transport, feed);
        this.onSea = onSea1;
    }

    public Tour createTour(final List<String> values) {
        return new RelaxTour(Integer.parseInt(values.get(0)),
                Double.parseDouble(values.get(1)),
                Integer.parseInt(values.get(2)), values.get(3),
                Transport.valueOf(values.get(4).toUpperCase()),
                Feed.valueOf(values.get(5).toUpperCase()),
                Boolean.valueOf(values.get(6)));
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
        if (!(o instanceof RelaxTour)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        RelaxTour relaxTour = (RelaxTour) o;

        return onSea == relaxTour.onSea;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (onSea ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RelaxTour{" + super.toString()
                + "onSea=" + onSea
                + "} ";
    }
}
