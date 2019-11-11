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

    /**
     * method is under construction todo
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
            throw new BuilderException("Exception via xml I/O. check "
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

        /*List<Price> prices = getPrices(getElementTextContent(element,
                TariffsXMLTags.PRICES.getValue()));*/
        List<Price> prices = getPrices(element); // todo передаются тарифы, а
        // не цены. исправить.
        tariff.setPrices(prices);
        List<Parameter> parameters = getParameters(element);
        tariff.setParameters(parameters);

        String creationTariffDay = getElementTextContent(element,
                TariffsXMLTags.CREATIONTARIFFDAY.getValue());
        tariff.setCreationTariffDay(LocalDate.parse(creationTariffDay));
        String smsPrice = getElementTextContent(element,
                TariffsXMLTags.SMSPRICES.getValue());
        tariff.setSmsPrice(Double.parseDouble(smsPrice));
        return tariff;
    }

    private List<Price> getPrices(Element element) throws BuilderException {
        List<Price> prices = new ArrayList<>();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element currentPriceElement = (Element) nodeList.item(i);
            Price price;
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

    private List<Parameter> getParameters(Element element) throws BuilderException {
        List<Parameter> parameters = new ArrayList<>();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element currentParameterElement = (Element) nodeList.item(i);
            Parameter parameter;
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

    private Price buildCallPrice(Element element) {
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

    private Price buildInternetPrice(Element element) {
        InternetPrices price = new InternetPrices();
        price.setOverspendingFeeValueForMb(
                Double.parseDouble(
                        getElementTextContent(element,
                                TariffsXMLTags.OVERSPENDINGFEEVALUEFORMB
                                        .getValue())));
        return price;
    }

    private Parameter buildVoiceParameters(Element element) {
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

    private Parameter buildInternetParameters(Element element) {
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
