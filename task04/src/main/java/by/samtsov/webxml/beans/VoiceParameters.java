package by.samtsov.webxml.beans;

import java.util.Objects;

/**
 * <p>Java class for VoiceParameters complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="VoiceParameters">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/tariffs}Parameters">
 *       &lt;all>
 *         &lt;element name="favoriteNumberCount" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="billingInSec" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="prepayment" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/all>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class VoiceParameters extends Parameters {
    /**
     * number of favorite numbers with special prices
     */
    protected int favoriteNumberCount;
    /**
     * number of seconds to rounding. usually is up rounding.
     */
    protected int billingInSec;
    /**
     * minimal fee for connection to tariff.
     */
    protected double prepayment;

    /**
     * Gets the value of the favoriteNumberCount property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getFavoriteNumberCount() {
        return favoriteNumberCount;
    }

    /**
     * Sets the value of the favoriteNumberCount property.
     *
     * @param value allowed object is
     *              {@link int }
     */
    public void setFavoriteNumberCount(int value) {
        this.favoriteNumberCount = value;
    }

    /**
     * Gets the value of the billingInSec property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getBillingInSec() {
        return billingInSec;
    }

    /**
     * Sets the value of the billingInSec property.
     *
     * @param value allowed object is
     *              {@link int }
     */
    public void setBillingInSec(int value) {
        this.billingInSec = value;
    }

    /**
     * Gets the value of the prepayment property.
     */
    public double getPrepayment() {
        return prepayment;
    }

    /**
     * Sets the value of the prepayment property.
     */
    public void setPrepayment(double value) {
        this.prepayment = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoiceParameters)) return false;
        VoiceParameters that = (VoiceParameters) o;
        return favoriteNumberCount == that.favoriteNumberCount &&
                billingInSec == that.billingInSec &&
                Double.compare(that.prepayment, prepayment) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteNumberCount, billingInSec, prepayment);
    }
}
