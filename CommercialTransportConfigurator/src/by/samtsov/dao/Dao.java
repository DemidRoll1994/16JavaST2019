package by.samtsov.dao;

import java.util.List;

public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    int add(T t);

    void update(T t);

    void delete(int id);
}
