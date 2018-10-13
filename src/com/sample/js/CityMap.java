package com.sample.js;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;

@WebServlet(name = "sample.js.CityMap", urlPatterns = "/sample/js/CityMap")
public class CityMap extends EasyHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("areaName", "沈阳市");
		jsonObject.put("rangeColor", new String[] {"gray","blue","green","red","yellow","orange"});
		String[] names = new String[]{"皇姑区","大东区","东陵区","法库县","和平区","康平县","辽中县","沈北新区","沈河区","苏家屯区","铁西区","新民市","于洪区"};
    	int[] values = new int[]{500,750,1400,100,900,350,400,1900,1800,1000,1500,1200,250}; 
		List<Map<String,Object>> seriesData = new ArrayList<Map<String,Object>>();
		for(int i=0;i<names.length;i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", names[i]);
			map.put("value", values[i]);
			seriesData.add(map);
		}
		jsonObject.put("seriesData", seriesData);
		hrr.setReqResult(jsonObject);
		hrr.forwardByViewName("Result.jsp");
	}
}
