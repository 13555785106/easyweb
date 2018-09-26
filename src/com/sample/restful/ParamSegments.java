package com.sample.restful;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;

@WebServlet("/sample/restful/ParamSegments/*")
public class ParamSegments extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	protected void doPost(HttpReqResp hrr) throws ServletException, IOException {
		String[] paramSegments = hrr.getRequest().getParamSegments();
		hrr.setReqResult(paramSegments);
		hrr.forwardByViewName("Result.jsp");
	}

}
