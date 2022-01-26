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

@WebFilter(filterName = "XssFilter", urlPatterns = {"/*"})
public class XssFilter implements Filter{
	 public XssFilter() {
	    }

	    public void init(FilterConfig config) throws ServletException {
	    }

	    public void destroy() {
	    }

	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
	    		throws IOException, ServletException {
	        filterChain.doFilter(new XssRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
	    }

}
