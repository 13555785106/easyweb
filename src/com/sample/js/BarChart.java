package com.sample.js;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;

@WebServlet(name = "sample.js.BarChart", urlPatterns = "/sample/js/BarChart")
public class BarChart extends EasyHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("xAxisData", new String[] {"周一", "周二", "周三", "周四", "周五", "周六", "周日"});
		jsonObject.put("seriesData", new int[] {10, 52, 200, 334, 390, 330, 220});
		hrr.setReqResult(jsonObject);
		hrr.forwardByViewName("Result.jsp");
	}
}
