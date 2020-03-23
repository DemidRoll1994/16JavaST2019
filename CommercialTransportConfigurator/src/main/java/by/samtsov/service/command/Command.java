package by.samtsov.service.command;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.sql.SQLServiceFactory;
import by.samtsov.view.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class Command {
    private Set<Role> allowedRoles = new HashSet<>();
    private User authorizedUser;
    private String name;
    private HashMap<String , String > menu = new HashMap<>();

    protected SQLServiceFactory factory;

    public Set<Role> getAllowedRoles() {
        return allowedRoles;
    }

    public User getAuthorizedUser() throws ServiceException {
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

    abstract public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException;

    public HashMap<String, String> getMenu() {
        return menu;
    }

}
