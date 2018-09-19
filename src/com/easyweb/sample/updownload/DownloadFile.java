package com.easyweb.sample.updownload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.easyweb.db.dac.DocDac;
import com.easyweb.db.model.Doc;
import com.easyweb.utils.MimeType;

@WebServlet(name="sample.updownload.DownloadFile",urlPatterns="/sample/updownload/DownloadFile")

public class DownloadFile extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		int id= Integer.parseInt(hrr.getRequest().getParameter("id"));
		Doc doc = DocDac.getInstance().getDoc(id);
		String userAgent = hrr.getRequest().getHeader("User-Agent");
		if (doc != null) {
			String  fileName = doc.getFileName();
			hrr.getResponse().setContentType(MimeType.getInstance().getMimeByFileName(fileName));
			if (userAgent.indexOf("Trident") != -1 || userAgent.indexOf("MSIE") != -1)
				hrr.getResponse().addHeader("Content-Disposition",
						"attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			else
				hrr.getResponse().addHeader("Content-Disposition",
						"attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
			byte[] buffer = new byte[8192];
			int count = 0;
			OutputStream os = hrr.getResponse().getOutputStream();
			FileInputStream fis = new FileInputStream(getServletContext().getRealPath(doc.getPath()));
			while ((count = fis.read(buffer)) > 0) {
				os.write(buffer, 0, count);
			}
			fis.close();
		}
	}
}
