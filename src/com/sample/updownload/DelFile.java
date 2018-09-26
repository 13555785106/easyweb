package com.sample.updownload;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.db.dac.DocDac;
import com.sample.db.model.Doc;


@WebServlet(name = "com.sample.updownload.DelFile", urlPatterns = "/sample/updownload/DelFile")
public class DelFile extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		int id= Integer.parseInt(hrr.getRequest().getParameter("id"));
		Doc doc = DocDac.getInstance().getDoc(id);
		if(DocDac.getInstance().delDoc(id)) {
			new File(getServletContext().getRealPath(doc.getPath())).delete();
		}
		hrr.getResponse().sendRedirect("ListFile");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
