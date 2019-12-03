package by.samtsov.webxml.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Java class for CallPrices complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CallPrices">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.com/tariffs}Prices">
 *       &lt;all>
 *         &lt;element name="inner" type="{http://www.example.com/tariffs}price"/>
 *         &lt;element name="outer" type="{http://www.example.com/tariffs}price"/>
 *         &lt;element name="linear" type="{http://www.example.com/tariffs}price"/>
 *       &lt;/all>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class CallPrices
        extends Prices {

    protected double inner;
    protected double outer;
    protected double linear;

    /**
     * Gets the inner calls tariff value.
     *
     * @return inner calls tariff value
     */
    public double getInner() {
        return inner;
    }

    /**
     * Sets the inner calls tariff value.
     *
     * @param newValue inner calls tariff value
     */
    public void setInner(double newValue) {
        this.inner = newValue;
    }

    /**
     * Gets the outer calls tariff value.
     *
     * @return outer calls tariff value
     */
    public double getOuter() {
        return outer;
    }

    /**
     * Sets the outer calls tariff value.
     *
     * @param newValue outer calls tariff value
     */
    public void setOuter(double newValue) {
        this.outer = newValue;
    }

    /**
     * Gets the linear calls tariff value.
     *
     * @return linear calls tariff value
     */
    public double getLinear() {
        return linear;
    }

    /**
     * Sets the linear calls tariff value.
     *
     * @param newValue linear calls tariff value
     */
    public void setLinear(double newValue) {
        this.linear = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CallPrices)) return false;
        CallPrices that = (CallPrices) o;
        return Double.compare(that.inner, inner) == 0 &&
                Double.compare(that.outer, outer) == 0 &&
                Double.compare(that.linear, linear) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inner, outer, linear);
    }

    @Override
    Map<String, Double> getMap() {
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("inner",inner);
        hashMap.put("outer",outer);
        hashMap.put("linear",linear);
        return hashMap;
    }

}
