package by.samtsov.task03.entity;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends Composite {


    public Paragraph(){
        childs = new ArrayList<>();

    }


    public List<Composite> getChilds(){
        return childs;
    }
}
