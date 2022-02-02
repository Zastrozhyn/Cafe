package by.zastr.cafe.controller.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Xss filter.
 */
@WebFilter(filterName = "XssFilter", urlPatterns = {"/*"})
public class XssFilter implements Filter{
	 
 	/**
 	 * Instantiates a new xss filter.
 	 */
 	public XssFilter() {
	    }

	    /**
    	 *
    	 * @param config the config
    	 * @throws ServletException the servlet exception
    	 */
    	public void init(FilterConfig config) throws ServletException {
	    }

	    /**
    	 * Destroy.
    	 */
    	public void destroy() {
	    }

	    /**
    	 * Do filter.
    	 *
    	 * @param servletRequest the servlet request
    	 * @param servletResponse the servlet response
    	 * @param filterChain the filter chain
    	 * @throws IOException Signals that an I/O exception has occurred.
    	 * @throws ServletException the servlet exception
    	 */
    	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
	    		throws IOException, ServletException {
	        filterChain.doFilter(new XssRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
	    }

}
