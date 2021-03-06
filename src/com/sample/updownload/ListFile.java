package com.sample.updownload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.db.dac.DocDac;


@WebServlet(name = "com.sample.updownload.ListFile", urlPatterns = "/sample/updownload/ListFile")
public class ListFile extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		
		hrr.setReqResult(DocDac.getInstance().allDocs());
		hrr.forwardByViewName("Result.jsp");
	}
}
