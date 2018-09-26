package com.sample.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.db.model.User;


//@WebFilter(filterName = "AuthenticateIdentityFilter", urlPatterns = "/sample/*")
public class AuthenticateIdentityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		User user = (User) httpServletRequest.getSession().getAttribute("curUser");
		if (user != null)
			chain.doFilter(request, response);
		else {
			((HttpServletResponse) response).sendRedirect(httpServletRequest.getContextPath() + "/Login");
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}
}
