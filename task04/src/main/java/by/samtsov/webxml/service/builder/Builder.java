package by.samtsov.webxml.service.builder;

import by.samtsov.webxml.beans.Tariff;

import java.util.ArrayList;
import java.util.List;

public abstract class Builder {


    /**
     * List of drug entities.
     */
    private List<Tariff> tariffs;

    /**
     * The default constructor.
     */
    protected Builder() {
        this.tariffs = new ArrayList<>();
    }

    /**
     * Sets drugs.
     *
     * @param drugsValue value of drugs.
     */
    protected void setTariffs(final List<Tariff> drugsValue) {
        tariffs = drugsValue;
    }

    /**
     * Gets drugs.
     *
     * @return value of drugs.
     */
    public List<Tariff> getTariffs() {
        return tariffs;
    }

    /**
     * Builds the set of drug entities using DOM, SAX or StAX parsers.
     *
     * @param filename the provided xml-document.
     */
    public abstract void buildSetDrugs(String filename);
}
