package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

public class RelaxTour extends Tour{

    private boolean onSea;

    public Tour createTour(String[] values) {
        return new RelaxTour(Integer.parseInt(values[0]), Double.parseDouble(values[1])
                , Integer.parseInt(values[2]), values[3], Transport.valueOf(values[4]), Feed.valueOf(values[5])
                , Boolean.valueOf(values[6]));
    }

    private RelaxTour(int id, double price, int duration, String name, Transport transport, Feed feed, boolean onSea) {
        super(id, price, duration, name, transport, feed);
        this.onSea = onSea;
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
        if (!(o instanceof RelaxTour)) return false;
        if (!super.equals(o)) return false;

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
        return "RelaxTour{"  + super.toString()
                + "onSea=" + onSea
                + "} ";
    }
}
