package by.samtsov.bean.entity;

import by.samtsov.bean.enums.OrderStatus;

import java.util.List;

public class Order {
    int id;
    OrderStatus orderStatus;
    List<Configuration> configurations;
    Double cost;
}
