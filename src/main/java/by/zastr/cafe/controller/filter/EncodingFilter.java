/*
 * 
 */
package by.zastr.cafe.controller.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

/**
 * The type Encoding filter.
 */
@WebFilter(filterName = "encodingFilter", urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "encoding", 
value = "UTF-8", description = "Encoding param")})

public class EncodingFilter implements Filter {
	private String code;

	/**
	 *
	 * @param filterConfig the filter config
	 */
	@Override
	public void init(FilterConfig filterConfig) {
			code = filterConfig.getInitParameter("encoding");
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
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String codeRequest = servletRequest.getCharacterEncoding();
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			servletRequest.setCharacterEncoding(code);
			servletResponse.setCharacterEncoding(code);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * Destroy.
	 */
	@Override
	public void destroy() {
		code = null;
	}
}