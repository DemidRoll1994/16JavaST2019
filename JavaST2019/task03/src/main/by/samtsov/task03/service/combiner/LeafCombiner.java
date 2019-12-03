package by.samtsov.task03.service.combiner;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Leaf;

public class LeafCombiner extends ComponentCombiner {
    /**
     * full-parameter constructor.
     *
     * @param newSuffixString - text that appears before current component
     *                        String
     * @param newPrefixString - text that appears after current component
     *                        String
     */
    public LeafCombiner(final String newSuffixString, final String
            newPrefixString) {
        setSuffixString(newSuffixString);
        setPrefixString(newPrefixString);
    }

    /**
     * Empty-parameter constructor.
     */
    public LeafCombiner() {
    }

    /**
     * concat string values from Leaf to one string with prefix and suffix.
     *
     * @param componentToCombine - component, that should be concatenated
     *                           into the string
     * @return - concatenation result.
     */
    @Override
    public String combine(final Component componentToCombine) {
        Leaf leaf = (Leaf) componentToCombine;
        StringBuilder componentInString = new StringBuilder();
        componentInString.append(getPrefixString());
        componentInString.append(leaf.getValue());
        componentInString.append(getSuffixString());
        return componentInString.toString();
    }
}
