package by.samtsov.webxml.service.validator;

import by.samtsov.webxml.beans.Tariff;
import by.samtsov.webxml.service.builder.Builder;
import by.samtsov.webxml.service.builder.BuilderFactory;
import by.samtsov.webxml.service.exception.ServiceException;
import by.samtsov.webxml.service.exception.ValidateException;

import java.util.List;

public class TariffXMLParser {

    private ValidatorService validator = new ValidatorService();

    public List<Tariff> parseXML(String path, String parserName)
            throws ServiceException {
        if (!validator.isStringEmpty(parserName) ||
                validator.isValidDocument(path)) {
            Builder builder = new BuilderFactory().createTariffBuilder(parserName);
            builder.buildTariffs(path);
            return builder.getTariffs();
        } else {
            throw new ValidateException("file is invalid");
        }
    }
}
