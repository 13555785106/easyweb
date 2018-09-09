package com.easyweb.core;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/**
 * 
 *
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class EasyHttpServletResponse extends HttpServletResponseWrapper {

	public EasyHttpServletResponse(HttpServletResponse response, String charset) {
		super(response);
		setCharacterEncoding("UTF-8");
		setContentType("text/html; charset=UTF-8");
	}

}
