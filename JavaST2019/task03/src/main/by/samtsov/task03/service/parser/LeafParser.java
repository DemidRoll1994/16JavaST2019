package by.samtsov.task03.service.parser;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Leaf;

public class LeafParser extends ComponentParser {
    /**
     * convert initial string for leaf Component.
     *
     * @param initialString string contains initial value for parsing Component
     * @return parsed component
     */
    @Override
    public Component parseComponent(final String initialString) {
        return new Leaf(initialString);
    }
}
