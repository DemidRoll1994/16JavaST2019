package by.samtsov.travelagency.entities;

public class RelaxTour extends Tour{

    private boolean onSea;

    public Tour createTour(int id, double price, int duration, String name, boolean onSea) {
        return new RelaxTour(id, price, duration, name, onSea);

    }

    private RelaxTour(int id, double price, int duration, String name, boolean onSea) {
        super(id, price, duration, name);
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
        final StringBuilder sb = new StringBuilder("RelaxTour{");
        sb.append("onSea=").append(onSea);
        sb.append('}');
        return sb.toString();
    }
}
