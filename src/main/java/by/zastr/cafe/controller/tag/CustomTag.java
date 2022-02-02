package by.zastr.cafe.controller.tag;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

/**
 * class CustomTag
 * @author A.Zastrozhyn
 *
 */
@SuppressWarnings("serial")
public class CustomTag extends TagSupport{
	private static final String AUTHOR = "made by A.Zastrozhyn";
	
	@Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(AUTHOR);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}
