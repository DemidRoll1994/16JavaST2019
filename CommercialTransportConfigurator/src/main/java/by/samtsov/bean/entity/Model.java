package by.samtsov.bean.entity;

import java.util.Map;

public class Model {
    private int id;
    private String name;
    private double basicPrice;
    private String imgPath;
    private Map<Integer, Option> availableOptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Map<Integer, Option> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(Map<Integer, Option> availableOptions) {
        this.availableOptions = availableOptions;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
