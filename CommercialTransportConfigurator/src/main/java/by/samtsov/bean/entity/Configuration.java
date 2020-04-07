package by.samtsov.bean.entity;

import java.util.List;

public class Configuration {
    private int id;
    private String name;
    private Model model;
    private int ownerId;
    private List<OptionValue> selectedOptionValues;
    private double price;

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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<OptionValue> getSelectedOptionValues() {
        return selectedOptionValues;
    }

    public void setSelectedOptionValues(List<OptionValue> selectedOptionValues) {
        this.selectedOptionValues = selectedOptionValues;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }}
