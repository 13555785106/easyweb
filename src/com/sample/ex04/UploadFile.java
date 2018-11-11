package com.sample.ex04;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.FilenameUtils;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.auth.AuthUtils;
import com.sample.auth.Constants;
import com.sample.db.dac.DocDac;
import com.sample.db.model.Doc;
import com.sample.db.model.User;


@WebServlet(name="com.sample.ex04.UploadFile",urlPatterns="/sample/ex04/UploadFile")
@MultipartConfig
public class UploadFile extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		Set<Integer> authoritiesSet = AuthUtils.getAuthOfManageFiles((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (!authoritiesSet.contains(Constants.AUTH_UPLOAD_FILE)) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return ;
		}
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		Set<Integer> authoritiesSet = AuthUtils.getAuthOfManageFiles((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (!authoritiesSet.contains(Constants.AUTH_UPLOAD_FILE)) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return ;
		}
		Doc doc = hrr.convertAndValidate(Doc.class);
		if (doc != null && !hrr.hasErrors()) {
			doc.setDateTime(new Timestamp(new java.util.Date().getTime()));
			doc.setPath("/files/"+UUID.randomUUID().toString()+"." +FilenameUtils.getExtension(doc.getFileName()));
			hrr.getRequest().getPart("file").write(getServletContext().getRealPath(doc.getPath()));
			hrr.setReqResult(doc);
			DocDac.getInstance().addDoc(doc);
			if(doc.getId()>0) {
				hrr.forwardByViewName("Result.jsp");
				return;
			}else {
				hrr.addExeError("添加文件失败");
			}
		}
		hrr.forwardByViewName("Input.jsp");
	}
}
