package by.samtsov.webxml.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Java class for InternetParameters complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InternetParameters">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/tariffs}Parameters">
 *       &lt;all>
 *         &lt;element name="includedTraffic" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="billingInMB" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="prepayment" type="{http://www.example.com/tariffs}price"/>
 *       &lt;/all>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class InternetParameters
        extends Parameters {

    protected int includedTraffic;
    protected double billingInMB;
    protected double prepayment;

    /**
     * Gets the value of the includedTraffic property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getIncludedTraffic() {
        return includedTraffic;
    }

    /**
     * Sets the value of the includedTraffic property.
     *
     * @param value allowed object is
     *              {@link int }
     */
    public void setIncludedTraffic(int value) {
        this.includedTraffic = value;
    }

    /**
     * Gets the value of the billingInMB property.
     *
     * @return possible object is
     * {@link double }
     */
    public double getBillingInMB() {
        return billingInMB;
    }

    /**
     * Sets the value of the billingInMB property.
     *
     * @param value allowed object is
     *              {@link double }
     */
    public void setBillingInMB(double value) {
        this.billingInMB = value;
    }

    /**
     * Gets the value of the prepayment property.
     *
     * @return possible object is
     * {@link double }
     */
    public double getPrepayment() {
        return prepayment;
    }

    /**
     * Sets the value of the prepayment property.
     *
     * @param value allowed object is
     *              {@link double }
     */
    public void setPrepayment(double value) {
        this.prepayment = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternetParameters)) return false;
        InternetParameters that = (InternetParameters) o;
        return includedTraffic == that.includedTraffic &&
                Double.compare(that.billingInMB, billingInMB) == 0 &&
                Double.compare(that.prepayment, prepayment) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(includedTraffic, billingInMB, prepayment);
    }


    @Override
    public Map<String, Object> getMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("includedTraffic", includedTraffic);
        hashMap.put("billingInMB", billingInMB);
        hashMap.put("prepayment", prepayment);
        return hashMap;
    }
}
