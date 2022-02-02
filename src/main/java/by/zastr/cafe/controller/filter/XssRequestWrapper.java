package by.zastr.cafe.controller.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * The type Request wrapper.
 */
public class XssRequestWrapper extends HttpServletRequestWrapper{

	/**
	 * Instantiates a new xss request wrapper.
	 *
	 * @param request the request
	 */
	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * Gets the parameter values.
	 *
	 * @param parameter the parameter
	 * @return the parameter values
	 */
	public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        } else {
            int count = values.length;
            String[] encodedValues = new String[count];

            for(int i = 0; i < count; ++i) {
                encodedValues[i] = this.stripXss(values[i]);
            }

            return encodedValues;
        }
    }

    /**
     * Gets the parameter.
     *
     * @param parameter the parameter
     * @return the parameter
     */
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return value == null ? null : this.stripXss(value);
    }

    private String stripXss(String value) {
        return value.replaceAll("[<>]", "");
    }

}
