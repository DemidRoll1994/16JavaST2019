package by.samtsov.task03.service.combiner;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Composite;

public class CompositeCombiner extends ComponentCombiner {


    /**
     * combiner that should combine child Components.
     */
    private ComponentCombiner nextComponentCombiner;

    /**
     * Constructor with next init data:.
     *
     * @param newNextComponentCombiner - next combiner Component
     * @param newSuffixString          - text that appears before current
     *                                 component String
     * @param newPrefixString          - text that appears after current
     *                                 component String
     */
    public CompositeCombiner(final ComponentCombiner newNextComponentCombiner,
                             final String newSuffixString,
                             final String newPrefixString) {
        setSuffixString(newSuffixString);
        setPrefixString(newPrefixString);
        nextComponentCombiner = newNextComponentCombiner;
    }

    /**
     * concat string values from component to one string with prefix and suffix.
     *
     * @param componentToCombine - component, that should be concatenated
     *                           into the string
     * @return - concatenation result.
     */
    public String combine(final Component componentToCombine) {
        Composite composite = (Composite) componentToCombine;
        StringBuilder componentInString = new StringBuilder();
        for (Component child : composite.getChilds()) {
            componentInString.append(getSuffixString());
            componentInString.append(nextComponentCombiner.combine(child));
            componentInString.append(getPrefixString());
        }
        return componentInString.toString();
    }

    /**
     * getter for nextComponent field.
     *
     * @return value in fiend nextComponent
     */
    public ComponentCombiner getNextComponentCombiner() {
        return nextComponentCombiner;
    }

    /**
     * setter for nextComponent field.
     *
     * @param newNextComponentCombiner - value in fiend nextComponent
     */
    public void setNextComponentCombiner(final ComponentCombiner
                                                 newNextComponentCombiner) {
        nextComponentCombiner = newNextComponentCombiner;
    }
}
