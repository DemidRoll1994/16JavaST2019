package by.samtsov.webxml.beans;

import by.samtsov.webxml.beans.enums.Operator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="operator">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="A1"/>
 *               &lt;enumeration value="MTC"/>
 *               &lt;enumeration value="Life"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="payroll" type="{http://www.example.com/tariffs}price"/>
 *         &lt;element name="prices">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.example.com/tariffs}prices" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="smsPrices" type="{http://www.example.com/tariffs}price"/>
 *         &lt;element name="parameters">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.example.com/tariffs}parameters" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="creationTariffDay" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/all>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}ID">
 *             &lt;pattern value="(tariff)[1-9][\d]{0,5}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Tariff {
    /**
     * operator name. stored in enum Operator.
     */
    protected Operator operator;
    /**
     * tariff subscription fee.
     */
    protected double payroll;
    /**
     * prices for using some functions (calls,internet, etc.).
     */
    protected List<Price> prices;
    /**
     * sms price using.
     */
    protected double smsPrice;
    /**
     * other parameters of tariff as a billing, included traffic and other.
     */
    protected List<Parameter> parameters;
    /**
     * date of tariff creation.
     */
    protected LocalDate creationTariffDay;
    /**
     * tariff name.
     */
    protected String name;
    /**
     * tariff id.
     */
    protected String id;

    /**
     * Gets operator name.
     *
     * @return Operator name
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Sets operator name.
     *
     * @param newOperator operator name
     */
    public void setOperator(Operator newOperator) {
        operator = newOperator;
    }

    /**
     * Gets subscription fee .
     *
     * @return tariff subscription fee
     */
    public double getPayroll() {
        return payroll;
    }

    /**
     * Sets subscription fee.
     *
     * @param newPayroll subscription fee.
     */
    public void setPayroll(double newPayroll) {
        this.payroll = newPayroll;
    }

    /**
     * Gets list of prices.
     *
     * @return List of Prices
     */
    public List<Price> getPrices() {
        return prices;
    }

    /**
     * Sets list of prices.
     *
     * @param newPrices list of prices
     */
    public void setPrices(List<Price> newPrices) {
        prices = newPrices;
    }

    /**
     * Gets sms price.
     *
     * @return sms price
     */
    public double getSmsPrice() {
        return smsPrice;
    }

    /**
     * Sets sms price.
     *
     * @param newValue sms price
     */
    public void setSmsPrice(double newValue) {
        this.smsPrice = newValue;
    }

    /**
     * Gets list of parameters.
     *
     * @return list of parameters
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets list of parameters.
     *
     * @param newParameters list of new parameters
     */
    public void setParameters(List<Parameter> newParameters) {
        this.parameters = newParameters;
    }

    /**
     * Gets creation Tariff Day.
     *
     * @return creation Tariff Day
     */
    public LocalDate getCreationTariffDay() {
        return creationTariffDay;
    }

    /**
     * Sets creation Tariff Day.
     *
     * @param newCreationTariffDay creation Tariff Day
     */
    public void setCreationTariffDay(LocalDate newCreationTariffDay) {
        this.creationTariffDay = newCreationTariffDay;
    }

    /**
     * Gets tariff name.
     *
     * @return tariff name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets tariff name.
     *
     * @param newTariffName tariff name
     */
    public void setName(String newTariffName) {
        this.name = newTariffName;
    }

    /**
     * Gets Id.
     *
     * @return Id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id.
     *
     * @param newId Id
     */
    public void setId(String newId) {
        this.id = newId;
    }

}
