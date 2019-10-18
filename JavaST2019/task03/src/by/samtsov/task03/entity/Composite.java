package by.samtsov.task03.entity;

import java.util.List;

public abstract class Composite implements Component{

    public List<Composite> childs;

    public Composite(List<Composite> newChilds){
        setChilds(newChilds);
    }

    public Composite(){
    }

    public List<Composite> getChilds(){
        return childs;
    }

    public void setChilds(List<Composite> newChilds){
        childs = newChilds;
    }
    public void addChild(Composite newChild){
        childs.add(newChild);
    }

    public String concatCurrentComposite() {// todo concat for
        String currentCompositeInString = "";
        for (Composite child : childs) {
            currentCompositeInString.concat(child.concatCurrentComposite());
        }
        return currentCompositeInString;
    }
}
