package java.by.samtsov.task03.beans.entity;

import java.by.samtsov.task03.beans.enums.ComponentType;

import java.util.Objects;

public class Leaf extends Component {
    /**
     * value in Leaf.
     */
    private String value;

    /**
     * Constructor with init value as a ComponentType.
     *
     * @param newComponentType - type of component for create
     */
    public Leaf(final ComponentType newComponentType) {
        setComponentType(newComponentType);
    }

    /**
     * Constructor with init value as a String value.
     *
     * @param newValue - value
     */
    public Leaf(final String newValue) {
        value = newValue;
    }

    /**
     * getter for value field.
     *
     * @return value as a string
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter for a String value field.
     *
     * @param newValue - new value for set at field
     */
    public void setValue(final String newValue) {
        value = newValue;
    }

    /**
     * Standard equals method for entity.
     *
     * @param o - another object to compare
     * @return true if object is equals or false is not equals
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Leaf)) {
            return false;
        }
        Leaf leaf = (Leaf) o;
        return Objects.equals(value, leaf.value);
    }

    /**
     * Standard hashcode method for entity.
     *
     * @return hashcode of entity.
     */
    @Override
    public int hashCode() {
        final int modifier = 31;
        int result = super.hashCode();
        return result * modifier + Objects.hash(value);
    }

    /**
     * Standard toString method for entity.
     *
     * @return information about entity state
     */
    @Override
    public String toString() {
        return super.toString() + "Leaf{" + "value='" + value + '\'' + "} ";
    }
}
