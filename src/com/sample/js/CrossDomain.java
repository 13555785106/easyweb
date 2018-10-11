package com.sample.js;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;

@WebServlet(name = "sample.js.CrossDomain", urlPatterns = "/sample/js/CrossDomain")
public class CrossDomain extends EasyHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		hrr.getResponse().setContentType("application/json; charset=UTF-8");
		hrr.getResponse().addHeader("Access-Control-Allow-Origin", "*");
		System.out.println(hrr.getReqParams());
		hrr.getResponse().getWriter().append(new JSONObject(hrr.getReqParams()).toString(4));

	}
}
