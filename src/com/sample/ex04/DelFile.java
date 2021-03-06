package com.sample.ex04;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.auth.AuthUtils;
import com.sample.auth.Constants;
import com.sample.db.dac.DocDac;
import com.sample.db.model.Doc;
import com.sample.db.model.User;


@WebServlet(name = "com.sample.ex04.DelFile", urlPatterns = "/sample/ex04/DelFile")
public class DelFile extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		Set<Integer> authoritiesSet = AuthUtils
				.getAuthOfManageFiles((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (!authoritiesSet.contains(Constants.AUTH_DEL_FILE)) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return;
		}
		int id = Integer.parseInt(hrr.getRequest().getParameter("id"));
		Doc doc = DocDac.getInstance().getDoc(id);
		if (DocDac.getInstance().delDoc(id)) {
			new File(getServletContext().getRealPath(doc.getPath())).delete();
		}
		hrr.getResponse().sendRedirect("ListFile");
	}

}
