package by.samtsov.task03.service.combiner;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Composite;

public class CompositeCombiner implements ComponentCombiner {

    String suffixString = "";
    String prefixString = "";
    ComponentCombiner nextComponentCombiner;


    public CompositeCombiner(ComponentCombiner nextComponentCombiner
            , String suffixString, String prefixString) {
        this.suffixString = suffixString;
        this.prefixString = prefixString;
        this.nextComponentCombiner = nextComponentCombiner;
    }


    public String combine(Component componentToCombine) {
        Composite composite = (Composite) componentToCombine;
        StringBuffer componentInString = new StringBuffer();
        for (Component child : composite.getChilds()) {
            componentInString.append(suffixString);
            componentInString.append(nextComponentCombiner.combine(child));
            componentInString.append(prefixString);
        }
        return componentInString.toString();
    }


}
