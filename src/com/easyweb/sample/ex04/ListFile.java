package com.easyweb.sample.ex04;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.auth.AuthUtils;
import com.easyweb.auth.Constants;
import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.easyweb.db.dac.DocDac;
import com.easyweb.db.model.User;

@WebServlet(name = "sample.ex04.ListFile", urlPatterns = "/sample/ex04/ListFile")
public class ListFile extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		Set<Integer> authoritiesSet = AuthUtils.getAuthOfManageFiles((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (!authoritiesSet.contains(Constants.AUTH_LIST_FILE)) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return ;
		}
		hrr.setAttribute("authOfManageFiles", authoritiesSet);
		hrr.setReqResult(DocDac.getInstance().allDocs());
		hrr.forwardByViewName("Result.jsp");
	}
}
