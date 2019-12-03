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

        Element callPricesElement = getFirstNodeElement(priceElement,
                TariffsXMLTags.CALLPRICES.getValue());

        if (callPricesElement != null) {
            CallPrices callPrices = new CallPrices();
            callPrices.setInner(Double.parseDouble(getElementTextContent(
                    callPricesElement, TariffsXMLTags.INNER.getValue())));
            callPrices.setOuter(Double.parseDouble(getElementTextContent(
                    callPricesElement, TariffsXMLTags.OUTER.getValue())));
            callPrices.setLinear(Double.parseDouble(getElementTextContent(
                    callPricesElement, TariffsXMLTags.LINEAR.getValue())));
            pricesList.add(callPrices);
        }


        Element internetPricesElement = getFirstNodeElement(priceElement,
                TariffsXMLTags.INTERNETPRICES.getValue());
        if (internetPricesElement != null) {
            InternetPrices internetPrices = new InternetPrices();
            internetPrices.setOverspendingFeeValueForMb(Double.parseDouble(
                    getElementTextContent(internetPricesElement, TariffsXMLTags
                            .OVERSPENDINGFEEVALUEFORMB.getValue())));
            pricesList.add(internetPrices);
        }

        return pricesList;
    }

    // принимает тариф
    private static List<Parameters> getParameters(Element paramsElement) {
        List<Parameters> parametersList = new ArrayList<>();

        Element voiceParametersElement = getFirstNodeElement(paramsElement,
                TariffsXMLTags.VOICEPARAMETERS.getValue());
        if (voiceParametersElement != null) {
            VoiceParameters voiceParameters = new VoiceParameters();
            voiceParameters.setBillingInSec(Integer.parseInt(
                    getElementTextContent(voiceParametersElement,
                            TariffsXMLTags.BILLINGINSEC.getValue())));
            voiceParameters.setFavoriteNumberCount(Integer.parseInt(
                    getElementTextContent(voiceParametersElement,
                            TariffsXMLTags.FAVORITENUBERCOUNT.getValue())));
            voiceParameters.setPrepayment(Double.parseDouble(
                    getElementTextContent(voiceParametersElement,
                            TariffsXMLTags.PREPAYMENT.getValue())));
            parametersList.add(voiceParameters);
        }
        Element internetParametersElement = getFirstNodeElement(paramsElement,
                TariffsXMLTags.INTERNETPARAMETERS.getValue());
        if (internetParametersElement != null) {
            InternetParameters internetParameters = new InternetParameters();
            internetParameters.setBillingInMB(Double.parseDouble(
                    getElementTextContent(internetParametersElement,
                            TariffsXMLTags.BILLINGINMB.getValue())));
            internetParameters.setIncludedTraffic(Integer.parseInt(
                    getElementTextContent(internetParametersElement,
                            TariffsXMLTags.INCLUDEDTRAFFIC.getValue())));
            internetParameters.setPrepayment(Double.parseDouble(
                    getElementTextContent(internetParametersElement,
                            TariffsXMLTags.PREPAYMENT.getValue())));
            parametersList.add(internetParameters);
        }

        return parametersList;
    }

    private static Element getFirstNodeElement(Element priceElement, String childElementName) {
        NodeList nodeList = priceElement.getElementsByTagName(childElementName);
        if (nodeList.getLength() > 0
                && nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
            return (Element) nodeList.item(0);
        }
        return null;
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

    private Tariff buildTariff(final Element element)  {
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


}
