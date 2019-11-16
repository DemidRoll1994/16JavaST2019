package by.samtsov.bean;

import java.util.List;

public class Order {
    int id;
    OrderStatus orderStatus;
    List<Configuration> configurations;
    Double cost;
}
