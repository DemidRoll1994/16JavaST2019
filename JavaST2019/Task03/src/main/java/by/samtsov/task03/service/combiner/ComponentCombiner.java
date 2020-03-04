package java.by.samtsov.task03.service.combiner;

import java.by.samtsov.task03.beans.entity.Component;

public abstract class ComponentCombiner {

    /**
     * string that concatenates after component.
     */
    private String suffixString = "";
    /**
     * string that concatenates before component.
     */
    private String prefixString = "";

    /**
     * getter for Suffix.
     *
     * @return suffix to concatenate with component string representation(
     * backward)
     */
    public String getSuffixString() {
        return suffixString;
    }

    /**
     * setter for suffix.
     *
     * @param newSuffixString string that concatenate after component
     */
    public void setSuffixString(final String newSuffixString) {
        suffixString = newSuffixString;
    }

    /**
     * getter for PrefixString field.
     *
     * @return string that concatenates before component.
     */
    public String getPrefixString() {
        return prefixString;
    }

    /**
     * setter for PrefixString field.
     *
     * @param newPrefixString string that concatenates before component.
     */
    public void setPrefixString(final String newPrefixString) {
        prefixString = newPrefixString;
    }


    /**
     * concat string values from component to one string with prefix and suffix.
     *
     * @param componentToCombine - component, that should be concatenated
     *                           into the string
     * @return concatenation result.
     */
    abstract String combine(Component componentToCombine);
}
