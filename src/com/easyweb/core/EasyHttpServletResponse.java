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

	public EasyHttpServletResponse(HttpServletResponse response,String mimeType, String charset) {
		super(response);
		setCharacterEncoding(charset);
		setContentType(mimeType);
	}

}
