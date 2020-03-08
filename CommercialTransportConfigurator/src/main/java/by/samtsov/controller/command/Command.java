package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.entity.User;
import by.samtsov.bean.enums.Role;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.bean.exceptions.ServiceException;
import by.samtsov.service.sql.SQLServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public abstract class Command {
    private Set<Role> allowedRoles = new HashSet<>();
    private User authorizedUser;
    private String name;

    protected SQLServiceFactory factory;

    public Set<Role> getAllowedRoles() {
        return allowedRoles;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServiceFactory(SQLServiceFactory factory) {
        this.factory = factory;
    }

    abstract public ForwardPage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException;

}
