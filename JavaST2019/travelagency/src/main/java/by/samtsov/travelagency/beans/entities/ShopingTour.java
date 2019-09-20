package by.samtsov.travelagency.beans.entities;

import by.samtsov.travelagency.beans.enums.CurrencyEnum;

public class ShopingTour extends Tour {
    CurrencyEnum currency;

    public Tour createTour(int id, double price, int duration, String name, CurrencyEnum currency) {
        return new ShopingTour(id, price, duration, name, currency);
    }

    private ShopingTour(int id, double price, int duration, String name,CurrencyEnum currency) {
        super(id, price, duration, name);
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
        final StringBuilder sb = new StringBuilder("ShopingTour{");
        sb.append("currency=").append(currency);
        sb.append('}');
        return sb.toString();
    }
}
