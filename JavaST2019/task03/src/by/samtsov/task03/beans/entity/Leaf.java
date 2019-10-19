package by.samtsov.task03.beans.entity;

import by.samtsov.task03.beans.enums.ComponentType;

public class Leaf extends Component {

    private String symbolValue;

    public Leaf(ComponentType newComponentType) {
        componentType= newComponentType;
    }

    public Leaf(String newSymbolValue) {
        symbolValue=newSymbolValue;
    }


    public String getSymbolValue() {
        return symbolValue;
    }

    public void setSymbolValue(String symbolValue) {
        this.symbolValue = symbolValue;
    }

}
