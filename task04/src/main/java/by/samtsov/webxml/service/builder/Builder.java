package by.samtsov.webxml.service.builder;

import by.samtsov.webxml.beans.Tariff;
import by.samtsov.webxml.service.exception.BuilderException;

import java.util.ArrayList;
import java.util.List;

public abstract class Builder {

    protected List<Tariff> tariffs;

    protected Builder() {
        this.tariffs = new ArrayList<>();
    }

    public List<Tariff> getTariffs() {
        return new ArrayList<>(tariffs);
    }

    protected void setTariffs(final List<Tariff> newTariffs) {
        tariffs = newTariffs;
    }

    public abstract void buildTariffs(String filename) throws BuilderException;
}
