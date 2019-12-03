package by.samtsov.webxml.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        extends Prices {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternetPrices)) return false;
        InternetPrices that = (InternetPrices) o;
        return Double.compare(that.overspendingFeeValueForMb, overspendingFeeValueForMb) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(overspendingFeeValueForMb);
    }

    @Override
    Map<String, Double> getMap() {
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("overspendingFeeValueForMb", overspendingFeeValueForMb);
        return hashMap;
    }
}
