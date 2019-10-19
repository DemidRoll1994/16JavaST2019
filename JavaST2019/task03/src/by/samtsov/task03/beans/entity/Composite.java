package by.samtsov.task03.beans.entity;

import by.samtsov.task03.beans.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    public List<Component> childs = new ArrayList<>();

    public Composite(ComponentType newComponentType) {
        componentType = newComponentType;
    }

    public List<Component> getChilds() {
        return childs;
    }

    public void setChilds(List<Component> newChilds) {
        childs = newChilds;
    }

    public void addChild(Component newChild) {
        childs.add(newChild);
    }

}
