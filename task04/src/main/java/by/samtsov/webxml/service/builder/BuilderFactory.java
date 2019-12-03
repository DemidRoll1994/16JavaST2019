package by.samtsov.webxml.service.builder;

import by.samtsov.webxml.beans.enums.ParserTypes;
import by.samtsov.webxml.service.exception.BuilderException;

public class BuilderFactory {

    private static final String INCORRECT_BUILDER_TYPE_EXCEPTION =
            "Incorrect builder type. BuilderFactory cannot create a builder " +
                    "with name:";

    public Builder createTariffBuilder(final String typeParser)
            throws BuilderException {
        switch (ParserTypes.valueOf(typeParser.toUpperCase())) {
            case DOM:
                return new TariffDOMBuilder();
            case SAX:
                return new TariffSAXBuilder();
            case STAX:
                return new TariffStaXBuilder();
            default:
                throw new BuilderException(String.format("%s %s",
                        INCORRECT_BUILDER_TYPE_EXCEPTION, typeParser));
        }
    }
}
