package com.sample.html5ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

@WebServlet(name="com.sample.html5ajax.ProcessExcelProgress",urlPatterns="/sample/html5ajax/ProcessExcelProgress")

public class ProcessExcelProgress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTf-8");
		response.setContentType("application/json; charset=UTF-8");
		/*
		 * Enumeration<String> attrNames = request.getSession().getAttributeNames();
		 * while (attrNames.hasMoreElements()) { String attrName =
		 * attrNames.nextElement(); Object attrValue =
		 * request.getSession().getAttribute(attrName); System.out.println(attrName +
		 * "=" + attrValue); }
		 */
		HttpSession httpSession = request.getSession();
		JSONObject result = new JSONObject();
		result.put("status", "failed");
		String fileName = request.getParameter("fileName");
		System.out.println(fileName);
		if (fileName == null)
			fileName = "";
		if (!fileName.isEmpty()) {
			String attrBaseName = "a_" + FilenameUtils.getBaseName(fileName);
			Integer rowCount = (Integer) httpSession.getAttribute(attrBaseName + "rowCount");
			Integer rowNo = (Integer) httpSession.getAttribute(attrBaseName + "rowNo");
			String rowContent = (String) httpSession.getAttribute(attrBaseName + "rowContent");
			if (rowCount != null && rowNo != null && rowContent != null) {
				result.put("status", "success");
				result.put("rowCount", rowCount);
				result.put("rowNo", rowNo);
				result.put("rowContent", rowContent);
			}
			if (rowNo == rowCount) {
				httpSession.removeAttribute(attrBaseName + "rowCount");
				httpSession.removeAttribute(attrBaseName + "rowNo");
				httpSession.removeAttribute(attrBaseName + "rowContent");
			}
		}
		response.getWriter().append(result.toString(4));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
