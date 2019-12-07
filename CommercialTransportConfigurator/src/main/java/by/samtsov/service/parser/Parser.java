package by.samtsov.service.parser;

import by.samtsov.bean.exceptions.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public interface Parser<T> {
    T parse(HttpServletRequest request) throws IncorrectDataException;
}
