package by.samtsov.view.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Footer extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(" <br> ОЛЕГ <br> <br>");
            out.write(" <br> ОЛЕГ <br> <br>");

            out.write( "<!--FOOTER-->  ");
            out.write( "<footer> ");
            out.write( "<div class=\"row\"> ");
            out.write( "<div class=\"col-9\"> </div>  ");
            out.write( "<div class=\"col-3\"> Artyom Samtsov, <br> " +
                    "training.by, 2019 </div> ");
            out.write( "</div> ");
            out.write( "</footer> ");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
