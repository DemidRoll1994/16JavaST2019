package by.samtsov.bean;

import java.util.HashMap;
import java.util.Map;

public class ForwardPage {

    private String forwardURL;
    private boolean redirect;
    private Map<String, Object> sessionAttributes = new HashMap<>();

    public ForwardPage(String forward, boolean redirect) {
        this.forwardURL = forward;
        this.redirect = redirect;
    }

    public ForwardPage(String forward) {
        this(forward, true);
    }

    public String getForward() {
        return forwardURL;
    }

    public void setForward(String newForwardURL) {
        forwardURL = newForwardURL;
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

}
