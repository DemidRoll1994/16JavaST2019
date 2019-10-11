package by.samtsov.task03.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite {

    public ArrayList<Composite> childs;

    /*default public List<Composite> getChilds(){
        throw new UnsupportedOperationException();
    }
*/
    public abstract List<Composite> getChilds();
}
