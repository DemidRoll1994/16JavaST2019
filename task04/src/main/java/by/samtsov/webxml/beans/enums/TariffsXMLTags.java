package by.samtsov.webxml.beans.enums;

public enum TariffsXMLTags {

    TARIFFS("tariffs"),
    TARIFF("tariff"),
    OPERATOR("operator"),
    PAYROLL("payroll"),
    PRICES("prices"),
    SMSPRICES("smsPrices"),
    PARAMETERS("parameters"),
    CREATIONTARIFFDAY("creationTariffDay"),
    NAME("name"),
    ID("id"),
    VOICEPARAMETERS("voiceParameters"),
    INTERNETPARAMETERS("internetParameters"),
    FAVORITENUBERCOUNT("favoriteNumberCount"),
    BILLINGINSEC("billingInSec"),
    PREPAYMENT("prepayment"),
    INCLUDEDTRAFFIC("includedTraffic"),
    BILLINGINMB("billingInMB"),
    CALLPRICES("callPrices"),
    INTERNETPRICES("internetPrices"),
    INNER("inner"),
    OUTER("outer"),
    LINEAR("linear"),
    OVERSPENDINGFEEVALUEFORMB("overspendingFeeValueForMb");

    private String value;

    /**
     * Constructs an instance with specific value.
     *
     * @param val the value of the element.
     */
    TariffsXMLTags(final String val) {
        this.value = val;
    }

    /**
     * Gets the value.
     *
     * @return the value of the element.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
