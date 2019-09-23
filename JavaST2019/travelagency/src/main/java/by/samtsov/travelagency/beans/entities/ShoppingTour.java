package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.CurrencyEnum;
import by.samtsov.travelagency.beans.enums.Feed;
import by.samtsov.travelagency.beans.enums.Transport;

import java.util.List;

public class ShoppingTour extends Tour {
    CurrencyEnum currency;

    public ShoppingTour() {
    }

    private ShoppingTour(final int id, final double price, final int duration,
                         final String name, final Transport transport,
                         final Feed feed, final CurrencyEnum currency1) {
        super(id, price, duration, name, transport, feed);
        this.currency = currency1;
    }

    public Tour createTour(final List<String> values) {
        return new ShoppingTour(Integer.parseInt(values.get(0)),
                Double.parseDouble(values.get(1)),
                Integer.parseInt(values.get(2)), values.get(3),
                Transport.valueOf(values.get(4).toUpperCase()),
                Feed.valueOf(values.get(5).toUpperCase()),
                CurrencyEnum.valueOf(values.get(6).toUpperCase()));
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(final CurrencyEnum currency1) {
        this.currency = currency1;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingTour)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ShoppingTour that = (ShoppingTour) o;
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
