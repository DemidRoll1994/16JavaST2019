package by.samtsov.task03.service.combiner;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Leaf;

public class LeafCombiner implements ComponentCombiner {

    String suffixString = "";
    String prefixString = "";

    public LeafCombiner(String suffixString, String prefixString) {
        this.suffixString = suffixString;
        this.prefixString = prefixString;
    }

    @Override
    public String combine(Component componentToCombine) {
        Leaf leaf = (Leaf) componentToCombine;
        StringBuffer componentInString = new StringBuffer();
        componentInString.append(suffixString);
        componentInString.append(leaf.getSymbolValue());
        componentInString.append(prefixString);
        return componentInString.toString();
    }
}
