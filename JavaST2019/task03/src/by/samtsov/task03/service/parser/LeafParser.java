package by.samtsov.task03.service.parser;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Leaf;

public class LeafParser extends ComponentParser {

    @Override
    public Component parseComponent(String initialString) {
        return new Leaf(initialString);
    }
}
