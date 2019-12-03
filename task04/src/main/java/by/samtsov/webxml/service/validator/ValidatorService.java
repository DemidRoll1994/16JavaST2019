package by.samtsov.webxml.service.validator;

import by.samtsov.webxml.service.exception.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorService {

    private static final String SCHEMA_NAME = "WEB-INF/classes/data/tariffs.xsd";

    private static final Logger LOGGER = LogManager.getLogger(
            ValidatorService.class);


    public boolean isStringEmpty(final String value) {
        LOGGER.debug("string to validate is: " + value);
        return value == null || value.isEmpty();
    }


    public boolean isValidDocument(final String fileName) throws
            ValidateException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        LOGGER.debug("path of validating document is: " + fileName);
        LOGGER.debug("path of schema is: " + SCHEMA_NAME);
        LOGGER.debug("is schema exist: " + new File(SCHEMA_NAME).isFile());

        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(SCHEMA_NAME);
        LOGGER.debug("Schema location is exist: " + schemaLocation.exists());
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();

            validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            Source source = new StreamSource(fileName);
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }
}
