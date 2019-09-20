package by.samtsov.travelagency.beans.entities;

public class SightseeingTour extends Tour {
    private String country;

    public Tour createTour(int id, double price, int duration, String name, String country) {
        return new SightseeingTour(id, price, duration, name, country);

    }

    private SightseeingTour(int id, double price, int duration, String name, String country) {
        super(id, price, duration, name);
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
        final StringBuilder sb = new StringBuilder("SightseeingTour{");
        sb.append("country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
