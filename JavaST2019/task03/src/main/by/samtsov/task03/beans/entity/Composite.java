package by.samtsov.task03.beans.entity;

import by.samtsov.task03.beans.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite extends Component {
    /**
     * child Components. for example symbols is child components for words.
     */
    private List<Component> childs = new ArrayList<>();

    /**
     * Constructor with init value.
     *
     * @param newComponentType - type of component for create
     */
    public Composite(final ComponentType newComponentType) {
        setComponentType(newComponentType);
    }

    /**
     * getter for childs field.
     *
     * @return list of childs Component
     */
    public List<Component> getChilds() {
        return childs;
    }

    /**
     * setter for childs field.
     *
     * @param newChilds new values to set in Composite
     */
    public void setChilds(final List<Component> newChilds) {
        childs = newChilds;
    }

    /**
     * method for adding new child component in current component.
     *
     * @param newChild - adding component
     */
    public void addChild(final Component newChild) {
        childs.add(newChild);
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
        if (!(o instanceof Composite)) {
            return false;
        }
        Composite composite = (Composite) o;
        return Objects.equals(childs, composite.childs);
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
        return result * modifier + Objects.hash(childs);
    }


    /**
     * Standard toString method for entity.
     *
     * @return information about entity state
     */
    @Override
    public String toString() {
        return super.toString() + "Composite{" + "childs=" + childs + "} ";
    }
}
