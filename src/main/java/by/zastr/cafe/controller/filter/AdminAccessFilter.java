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

import by.zastr.cafe.controller.command.AttributeName;

@WebFilter(urlPatterns = {"/jsp/admin/*"})
public class AdminAccessFilter implements Filter{

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
        filterChain.doFilter(request, response);
    }

    public void destroy() {
    }

}
