package by.samtsov.task03.entity;

import java.util.List;

public interface Component {

    public List<Composite> getChilds();

    public void setChilds(List<Composite> newChilds);

    public String concatCurrentComposite() ;
}
