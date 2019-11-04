package by.samtsov.webxml.beans;

/**
 * <p>Java class for InternetPrices complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InternetPrices">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/tariffs}Prices">
 *       &lt;all>
 *         &lt;element name="overspendingFeeValueForMb" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/all>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class InternetPrices
        extends Price {

    protected double overspendingFeeValueForMb;

    /**
     * Gets the value of the overspendingFeeValueForMb property.
     */
    public double getOverspendingFeeValueForMb() {
        return overspendingFeeValueForMb;
    }

    /**
     * Sets the value of the overspendingFeeValueForMb property.
     */
    public void setOverspendingFeeValueForMb(double value) {
        this.overspendingFeeValueForMb = value;
    }

}
