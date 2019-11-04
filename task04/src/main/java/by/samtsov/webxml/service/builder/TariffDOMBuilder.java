package by.samtsov.webxml.service.builder;

import by.samtsov.webxml.beans.Parameter;
import by.samtsov.webxml.beans.Price;
import by.samtsov.webxml.beans.Tariff;
import by.samtsov.webxml.beans.enums.Operator;
import by.samtsov.webxml.beans.enums.TariffsXMLTags;
import by.samtsov.webxml.service.exception.BuilderException;
import javafx.scene.Group;
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

    /**
     * Defines a factory API that enables applications to obtain a parser that
     * produces DOM object trees from XML documents.
     */
    private DocumentBuilderFactory factory;
    /**
     * Defines the API to obtain DOM Document instances from an XML document.
     */
    private DocumentBuilder builder;
    private List<Tariff> tariffs = new ArrayList<>();


    public TariffDOMBuilder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        builder = factory.newDocumentBuilder();
    }


    private static String getElementTextContent(Element element, String elementName) {
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
                Element xmlTariffElement = (Element) xmlTariffs.item(i);
                Tariff tariff = buildtariff(xmlTariffElement);
                tariffs.add(tariff);
            }
        } catch (Exception ex) {
            throw new BuilderException("Exception via xml I/O. check");
        }
    }

    private Tariff buildtariff(final Element element) {
        Tariff tariff = new Tariff();

        String id = element.getAttribute(TariffsXMLTags.ID.getValue());
        tariff.setId(id);
        String name = element.getAttribute(TariffsXMLTags.NAME.getValue());
        tariff.setName(name);
        String operator = getElementTextContent(element,
                TariffsXMLTags.OPERATOR.getValue());
        tariff.setOperator(Operator.valueOf(operator));
        String payroll = getElementTextContent(element,
                TariffsXMLTags.PAYROLL.getValue());
        tariff.setPayroll(Double.parseDouble(payroll));

        List<Price> prices = getPrices(element);
        tariff.setPrices(prices);
        List<Parameter> parameters = getPararmeters(element);
        tariff.setParameters(parameters);

        String creationTariffDay = getElementTextContent(element,
                TariffsXMLTags.CREATIONTARIFFDAY.getValue());
        tariff.setCreationTariffDay(LocalDate.parse(creationTariffDay));

        String smsPrice = getElementTextContent(element,
                TariffsXMLTags.SMSPRICES.getValue());
        tariff.setSmsPrice(Double.parseDouble(smsPrice));


        Group group = Group.valueOf(
                getElementTextContent(element, "group").toUpperCase());
        tariff.setGroup(group);

        getAnalogs(element, tariff);
        FormType[] formTypes = FormType.values();
        for (FormType formType : formTypes) {
            String formName = formType.name().toLowerCase();

            Element forms = (Element) element.getElementsByTagName(formName)
                    .item(0);

            Form tariffForm = new Form(formType);
            if (forms != null) {
                tariff.addVersion(tariffForm);
                NodeList pharmacies = forms.getElementsByTagName("pharmacy");

                for (int i = 0; i < pharmacies.getLength(); i++) {
                    Element pharmacyElement = (Element) pharmacies.item(i);

                    Pharmacy pharmacy = new Pharmacy();

                    Certificate certificate = getCertificate(pharmacyElement);
                    String dosage = getElementTextContent(pharmacyElement,
                            "dosage");
                    PackageType packageType = getPackage(tariffForm,
                            pharmacyElement);

                    pharmacy.setCertificate(certificate);
                    pharmacy.setDosage(Double.parseDouble(dosage));
                    pharmacy.setType(packageType);

                    tariffForm.addPharmacy(pharmacy);
                }
            }
        }

        return tariff;
    }

    private List<Price> getPrices(Element element) {
        List<Price> prices = new ArrayList<>();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            nodeList.item(i)

        }
    }

}
