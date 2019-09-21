package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.CurrencyEnum;
import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

public class ShopingTour extends Tour {
    CurrencyEnum currency;

    public Tour createTour(String[] values) {
        return new ShopingTour(Integer.parseInt(values[0]), Double.parseDouble(values[1]), Integer.parseInt(values[2])
                , values[3], Transport.valueOf(values[4]), Feed.valueOf(values[5]), CurrencyEnum.valueOf(values[6]));
    }

    private ShopingTour(int id, double price, int duration, String name, Transport transport, Feed feed
            , CurrencyEnum currency) {
        super(id, price, duration, name, transport, feed);
        this.currency = currency;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopingTour)) return false;
        if (!super.equals(o)) return false;

        ShopingTour that = (ShopingTour) o;

        return currency == that.currency;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShopingTour{" + super.toString()
                + "currency=" + currency.toString()
                + "} ";
    }
}
