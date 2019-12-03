package by.samtsov.service.parser;

import javax.servlet.http.HttpServletRequest;

public interface Parser<T> {
    T parse(HttpServletRequest request);
}
