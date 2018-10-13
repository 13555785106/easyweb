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

@WebServlet(name = "sample.js.ProvinceMap", urlPatterns = "/sample/js/ProvinceMap")
public class ProvinceMap extends EasyHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("seriesName", "辽宁");
		jsonObject.put("rangeColor", new String[] { "gray", "blue", "green", "red", "yellow", "orange", "BlueViolet",
				"Aquamarine", "LightSeaGreen", "YellowGreen", "OrangeRed", "DeepPink" });
		String[] names = new String[] { "沈阳市", "抚顺市", "铁岭市", "本溪市", "丹东市", "大连市", "辽阳市", "鞍山市", "营口市", "盘锦市", "锦州市",
				"葫芦岛市", "朝阳市", "阜新市" };
		int[] values = new int[] { 100, 300, 500, 700, 900, 1100, 1300, 1500, 1700, 1900, 1500, 1300, 900, 300 };
		List<Map<String, Object>> seriesData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", names[i]);
			map.put("value", values[i]);
			seriesData.add(map);
		}
		jsonObject.put("seriesData", seriesData);
		hrr.setReqResult(jsonObject);
		hrr.forwardByViewName("Result.jsp");
	}
}
