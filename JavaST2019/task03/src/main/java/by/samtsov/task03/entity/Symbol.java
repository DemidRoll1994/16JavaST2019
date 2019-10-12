package by.samtsov.task03.entity;

import java.util.List;

public class Symbol extends Composite {

    private String symbolValue;

    public Symbol(String newSymbolValue) {
        super();
        symbolValue=newSymbolValue;
    }

    @Override
    public List<Composite> getChilds() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void setChilds(List<Composite> newChilds) {
        throw new UnsupportedOperationException();
    }
}
