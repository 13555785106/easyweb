package com.sample.ex02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.db.dac.UserDac;


@WebServlet(name="com.sample.ex02.DelUser",urlPatterns="/sample/ex02/DelUser/*")
public class DelUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		String id = hrr.getRequest().getParamSegments()[0];
		if (id != null) {
			UserDac.getInstance().delUser(Integer.parseInt(id));
		}
		hrr.getResponse().sendRedirect("../ListUser");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
