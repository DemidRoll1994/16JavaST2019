package by.samtsov.view;

import java.util.HashMap;
import java.util.Map;

public class ResponsePage {

    private String jspName;
    private boolean redirect;
    private Map<String, Object> redirectedAttributes = new HashMap<>();

    public ResponsePage(String forward, boolean redirect) {
        this.jspName = forward;
        this.redirect = redirect;
    }

    public ResponsePage(String forward) {
        this(forward, true);
    }

    public String getJspName() {
        return jspName;
    }

    public void setJspName(String newJspName) {
        jspName = newJspName;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public Map<String, Object> getRedirectedAttributes() {
        return redirectedAttributes;
    }

}
