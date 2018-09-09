package com.easyweb.sample.html5ajax;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.easyweb.db.dac.UserDac;
import com.easyweb.db.model.Paging;
import com.easyweb.db.model.User;


@WebServlet(name="sample.html5ajax.AjaxAndJsonListUser",urlPatterns="/sample/html5ajax/AjaxAndJsonListUser")
public class AjaxAndJsonListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		if (account == null)
			account = "";
		account = account.trim();

		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null)
			pageNoStr = "0";
		int pageNo = Integer.parseInt(pageNoStr);

		String pageSizeStr = request.getParameter("pageSize");
		if (pageSizeStr == null)
			pageSizeStr = "10";
		int pageSize = Integer.parseInt(pageSizeStr);

		if (request.getParameter("lastPage") != null)
			pageNo = Integer.MAX_VALUE;
		if (request.getParameter("firstPage") != null)
			pageNo = 0;
		if (request.getParameter("previousPage") != null)
			pageNo--;
		if (request.getParameter("nextPage") != null)
			pageNo++;
		List<User> list = UserDac.getInstance().allUsers();
		if (!account.isEmpty()) {
			final String str = account;
			list = UserDac.getInstance().allUsers().stream().filter(o -> o.getAccount().contains(str)).collect(Collectors.toList());
		}
		Paging<User> paging = new Paging<User>(pageSize, pageNo, list);

		response.getWriter().append(new JSONObject(paging).toString(4));

	}
}
