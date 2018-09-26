package com.easyweb.core;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServlet的子类，进一步简化各种方法的实现。
 *
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */

public class EasyHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String viewPath = "/WEB-INF/jsp";
	private String viewBaseName = viewPath + "/" + this.getClass().getName().replace('.', '/');
	private String contentType = "text/html";
	private String charSet = "UTF-8";
	private Map<String, String> messagesMap = new HashMap<String, String>(Messages.getInstance().getMessages());

	protected EasyHttpServlet() {
		super();
		try {
			Properties servletMessages = new Properties();
			// 此处加载对应某个Servlet的属性文件，必须与此Servlet同名并以properties做扩展名。
			String messagesFileName = "/" + this.getClass().getName().replace('.', '/') + ".properties";
			URL url = getClass().getClassLoader().getResource(messagesFileName);
			if (url != null) {
				servletMessages.load(getClass().getResourceAsStream(messagesFileName));
				for (String key : servletMessages.stringPropertyNames()) {
					messagesMap.put(key, servletMessages.getProperty(key));
				}
			}
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	protected String getViewBaseName() {
		return this.viewBaseName;
	}

	protected Map<String, String> getMessagesMap() {
		return messagesMap;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doDelete(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHead(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doOptions(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	@Override
	protected void doTrace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doTrace(new HttpReqResp(new EasyHttpServletRequest(request, charSet),
				new EasyHttpServletResponse(response, contentType, charSet), messagesMap, viewBaseName));
	}

	protected void doGet(HttpReqResp hrr) throws ServletException, IOException {

	}

	protected void doPost(HttpReqResp hrr) throws ServletException, IOException {

	}

	protected void doPut(HttpReqResp hrr) throws ServletException, IOException {

	}

	protected void doDelete(HttpReqResp hrr) throws ServletException, IOException {

	}

	protected void doHead(HttpReqResp hrr) throws ServletException, IOException {

	}

	protected void doOptions(HttpReqResp hrr) throws ServletException, IOException {

	}

	protected void doTrace(HttpReqResp hrr) throws ServletException, IOException {

	}
}
