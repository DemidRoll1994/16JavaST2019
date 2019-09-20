package by.samtsov.travelagency.beans.entities;

public class CruiseTour extends Tour{
    private String country;
    private boolean onSea;

    public Tour createTour(int id, double price, int duration, String name, String country, boolean onSea) {
        return new CruiseTour(id, price, duration, name, country, onSea);

    }

    private CruiseTour(int id, double price, int duration, String name, String country, boolean onSea) {
        super(id, price, duration, name);
        this.country=country;
        this.onSea=onSea;
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
        return country != null ? country.equals(that.country) : that.country == null;
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
        final StringBuilder sb = new StringBuilder("CruiseTour{");
        sb.append("country='").append(country).append('\'');
        sb.append(", onSea=").append(onSea);
        sb.append('}');
        return sb.toString();
    }
}
