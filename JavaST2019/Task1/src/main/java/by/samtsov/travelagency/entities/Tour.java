package by.samtsov.travelagency.entities;

public abstract class Tour {
    /**A tour identifier. */
    private int id;
    /**A tour price. */
    private double price;
    /** A tour duration in days. */
    private int duration;
    /** A tour name.*/
    private String name;

    /**Factory method.
     * @return tour implementation.
     */
    //public abstract Tour createTour();

    public Tour(int id, double price, int duration, String name) {
        this.id = id;
        this.price = price;
        this.duration = duration;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;

        Tour tour = (Tour) o;

        if (id != tour.id) return false;
        if (Double.compare(tour.price, price) != 0) return false;
        if (duration != tour.duration) return false;
        return name != null ? name.equals(tour.name) : tour.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + duration;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tour{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", duration=").append(duration);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
