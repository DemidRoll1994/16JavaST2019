package by.samtsov.controller.command;

import by.samtsov.bean.User;
import by.samtsov.bean.enums.Role;
import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Command {
    private Set<Role> allowRoles = new HashSet<>();
    private User authorizedUser;
    private String shortUri;

    protected ServiceFactory factory;

    private String nextPage; // todo это все пихать в респонс
    private boolean redirect;
    private Map<String, Object> sessionAttributes = new HashMap<>();

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public Set<Role> getAllowRoles() {
        return allowRoles;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public String getShortUri() {
        return shortUri;
    }

    public void setShortUri(String shortUri) {
        this.shortUri = shortUri;
    }

    public void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    abstract public void execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, IncorrectDataException;

}
