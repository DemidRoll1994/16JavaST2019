package by.samtsov.bean.entity;

import java.util.List;

public class Model {
    private int id;
    private String name;
    private double price;
    private List<Option> availableOptions;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Option> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(List<Option> availableOptions) {
        this.availableOptions = availableOptions;
    }
}
