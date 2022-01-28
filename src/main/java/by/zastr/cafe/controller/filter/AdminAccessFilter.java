package by.zastr.cafe.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.controller.command.AttributeName;

@WebFilter(urlPatterns = {"/jsp/admin/*"})
public class AdminAccessFilter implements Filter{
	private static final Logger logger = LogManager.getLogger();

    public AdminAccessFilter() {
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeName.ADMIN) == null) {
            response.sendRedirect(request.getContextPath());
        }
        try {
        	filterChain.doFilter(request, response);
        	}
        catch (IllegalStateException e) {
        	logger.log(Level.INFO,"Illegal URI", e);
			
		}
    }

    public void destroy() {
    }

}
