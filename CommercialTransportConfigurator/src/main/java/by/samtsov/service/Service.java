package by.samtsov.service;

import java.util.List;

public interface Service<T> {
    T get(int id);

    List<T> getAll();

    int add(T t);

    void update(T t);

    void delete(int id);
}
