package by.samtsov.webxml.service.builder;

import by.samtsov.webxml.beans.*;
import by.samtsov.webxml.beans.enums.Operator;
import by.samtsov.webxml.beans.enums.TariffsXMLTags;
import by.samtsov.webxml.service.exception.BuilderException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TariffDOMBuilder extends Builder {

    private final static String PARSER_CONFIGURATION_EXCEPTION
            = "Creating DOMBuilder exception";
    /**
     * Defines a factory API that enables applications to obtain a parser that
     * produces DOM object trees from XML documents.
     */
    private DocumentBuilderFactory factory;
    /**
     * Defines the API to obtain DOM Document instances from an XML document.
     */
    private DocumentBuilder builder;


    public TariffDOMBuilder() throws BuilderException {
        try {
            tariffs = new ArrayList<>();
            factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new BuilderException(PARSER_CONFIGURATION_EXCEPTION, pce);
        }

    }


    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    // принимает тариф
    private static List<Prices> getPrices(Element priceElement) {
        List<Prices> pricesList = new ArrayList<>();

        CallPrices callPrices = new CallPrices();
        Element callPricesElement = getFirstNodeElement(priceElement,
                TariffsXMLTags.CALLPRICES.getValue());
        if (callPricesElement != null) {
            callPrices.setInner(Double.parseDouble(getElementTextContent(
                    callPricesElement, TariffsXMLTags.INNER.getValue())));
            callPrices.setOuter(Double.parseDouble(getElementTextContent(
                    callPricesElement, TariffsXMLTags.OUTER.getValue())));
            callPrices.setLinear(Double.parseDouble(getElementTextContent(
                    callPricesElement, TariffsXMLTags.LINEAR.getValue())));
        }

        InternetPrices internetPrices = new InternetPrices();
        Element internetPricesElement = getFirstNodeElement(priceElement,
                TariffsXMLTags.INTERNETPRICES.getValue());
        if (internetPricesElement != null) {
            internetPrices.setOverspendingFeeValueForMb(Double.parseDouble(
                    getElementTextContent(internetPricesElement, TariffsXMLTags
                            .OVERSPENDINGFEEVALUEFORMB.getValue())));
        }
        return pricesList;
    }

    private static Element getFirstNodeElement(Element priceElement, String childElementName) {
        NodeList nodeList = priceElement.getElementsByTagName(childElementName);
        if (nodeList.getLength() > 0
                && nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
            return (Element) nodeList.item(0);
        }
        return null;
    }

    /**
     * method is under construction todo
     *
     * @param element
     * @param elementName
     * @return
     */
    private static String getChildElement(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    public void buildTariffs(final String filename) throws BuilderException {
        Document document;
        try {
            document = builder.parse(filename);
            Element root = document.getDocumentElement();

            NodeList xmlTariffs = root.getElementsByTagName("tariff");
            for (int i = 0; i < xmlTariffs.getLength(); i++) {
                if (xmlTariffs.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element xmlTariffElement = (Element) xmlTariffs.item(i);
                    Tariff tariff = buildTariff(xmlTariffElement);
                    tariffs.add(tariff);
                }
            }
        } catch (Exception ex) {
            throw new BuilderException("Exception via xml I/O. "
                    + ex.getMessage());
        }
    }

    private Tariff buildTariff(final Element element) throws BuilderException {
        Tariff tariff = new Tariff();
        String id = element.getAttribute(TariffsXMLTags.ID.getValue());
        tariff.setId(id);
        String name = element.getAttribute(TariffsXMLTags.NAME.getValue());
        tariff.setName(name);
        String operator = getElementTextContent(element,
                TariffsXMLTags.OPERATOR.getValue());
        tariff.setOperator(Operator.valueOf(operator.toUpperCase()));
        String payroll = getElementTextContent(element,
                TariffsXMLTags.PAYROLL.getValue());
        tariff.setPayroll(Double.parseDouble(payroll));

        NodeList nList = element
                .getElementsByTagName(TariffsXMLTags.PRICES.getValue());
        List<Prices> prices = getPrices((Element) nList.item(0));

        tariff.setPrices(prices);
        List<Parameters> parameters = getParameters(element);
        tariff.setParameters(parameters);

        String creationTariffDay = getElementTextContent(element,
                TariffsXMLTags.CREATIONTARIFFDAY.getValue());
        tariff.setCreationTariffDay(LocalDate.parse(creationTariffDay));
        String smsPrice = getElementTextContent(element,
                TariffsXMLTags.SMSPRICES.getValue());
        tariff.setSmsPrice(Double.parseDouble(smsPrice));
        return tariff;
    }


    private static List<Parameters> getParameters(Element priceElement) {
        List<Parameters> parametersList = new ArrayList<>();

        VoiceParameters voiceParameters = new VoiceParameters();
        Element voiceParametersElement = getFirstNodeElement(priceElement,
                TariffsXMLTags.VOICEPARAMETERS.getValue());
        if (voiceParametersElement != null) {
            voiceParameters.setBillingInSec(Integer.parseInt(
                    getElementTextContent(voiceParametersElement,
                            TariffsXMLTags.BILLINGINSEC.getValue())));
            voiceParameters.setFavoriteNumberCount(Integer.parseInt(
                    getElementTextContent(voiceParametersElement,
                            TariffsXMLTags.FAVORITENUBERCOUNT.getValue())));
            voiceParameters.setPrepayment(Double.parseDouble(
                    getElementTextContent(voiceParametersElement,
                            TariffsXMLTags.PREPAYMENT.getValue())));
        }
        parametersList.add(voiceParameters);
        InternetParameters internetParameters = new InternetParameters();
        Element internetParametersElement = getFirstNodeElement(priceElement,
                TariffsXMLTags.INTERNETPARAMETERS.getValue());
        if (internetParametersElement != null) {
            internetParameters.setBillingInMB(Double.parseDouble(
                    getElementTextContent(internetParametersElement,
                            TariffsXMLTags.BILLINGINMB.getValue())));
            internetParameters.setIncludedTraffic(Integer.parseInt(
                    getElementTextContent(internetParametersElement,
                            TariffsXMLTags.INCLUDEDTRAFFIC.getValue())));
            internetParameters.setPrepayment(Double.parseDouble(
                    getElementTextContent(internetParametersElement,
                            TariffsXMLTags.PREPAYMENT.getValue())));
        }
        parametersList.add(internetParameters);

        return parametersList;
    }

    /*private List<Prices> getPrices(Element element) throws BuilderException {
        List<Prices> prices = new ArrayList<>();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element currentPriceElement = (Element) nodeList.item(i);
            Prices price;
            if (TariffsXMLTags.valueOf(element.getTagName().toUpperCase()) ==
                    TariffsXMLTags.CALLPRICES) {
                price = buildCallPrice(currentPriceElement);
            } else if (TariffsXMLTags.valueOf(element.getTagName()
                    .toUpperCase()) == TariffsXMLTags.INTERNETPRICES) {
                price = buildInternetPrice(currentPriceElement);
            } else {
                throw new BuilderException("Unexpected Price in node " +
                        nodeList.item(i).toString());
            }
            prices.add(price);
        }
        return prices;
    }

    private List<Parameters> getParameters(Element element) throws BuilderException {
        List<Parameters> parameters = new ArrayList<>();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element currentParameterElement = (Element) nodeList.item(i);
            Parameters parameter;
            if (TariffsXMLTags.valueOf(element.getTagName().toUpperCase()) ==
                    TariffsXMLTags.CALLPRICES) {
                parameter = buildVoiceParameters(currentParameterElement);
            } else if (TariffsXMLTags.valueOf(element.getTagName()
                    .toUpperCase()) == TariffsXMLTags.INTERNETPRICES) {
                parameter = buildInternetParameters(currentParameterElement);
            } else {
                throw new BuilderException("Unexpected Price in node " +
                        nodeList.item(i).toString());
            }
            parameters.add(parameter);
        }
        return parameters;
    }
*/
    private Prices buildCallPrice(Element element) {
        CallPrices price = new CallPrices();
        price.setInner(
                Double.parseDouble(
                        getElementTextContent(
                                element, TariffsXMLTags.INNER.getValue())));
        price.setOuter(
                Double.parseDouble(
                        getElementTextContent(
                                element, TariffsXMLTags.OUTER.getValue())));
        price.setLinear(
                Double.parseDouble(
                        getElementTextContent(
                                element, TariffsXMLTags.LINEAR.getValue())));
        return price;
    }

    private Prices buildInternetPrice(Element element) {
        InternetPrices price = new InternetPrices();
        price.setOverspendingFeeValueForMb(
                Double.parseDouble(
                        getElementTextContent(element,
                                TariffsXMLTags.OVERSPENDINGFEEVALUEFORMB
                                        .getValue())));
        return price;
    }

    private Parameters buildVoiceParameters(Element element) {
        VoiceParameters parameters = new VoiceParameters();
        parameters.setBillingInSec(
                Integer.parseInt(
                        getElementTextContent(element,
                                TariffsXMLTags.BILLINGINSEC.getValue())));
        parameters.setFavoriteNumberCount(
                Integer.parseInt(
                        getElementTextContent(element,
                                TariffsXMLTags.FAVORITENUBERCOUNT.getValue())));
        parameters.setPrepayment(
                Double.parseDouble(
                        getElementTextContent(element,
                                TariffsXMLTags.PREPAYMENT.getValue())));
        return parameters;
    }

    private Parameters buildInternetParameters(Element element) {
        InternetParameters parameters = new InternetParameters();
        parameters.setBillingInMB(
                Double.parseDouble(
                        getElementTextContent(element,
                                TariffsXMLTags.BILLINGINMB.getValue())));
        parameters.setIncludedTraffic(
                Integer.parseInt(
                        getElementTextContent(element,
                                TariffsXMLTags.INCLUDEDTRAFFIC.getValue())));
        parameters.setPrepayment(
                Double.parseDouble(
                        getElementTextContent(element,
                                TariffsXMLTags.PREPAYMENT.getValue())));
        return parameters;
    }

}
