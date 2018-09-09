package com.easyweb.sample.html5ajax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;

@WebServlet(name="sample.html5ajax.RecieveExcel",urlPatterns="/sample/html5ajax/RecieveExcel")
public class RecieveExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTf-8");
		response.setContentType("application/json; charset=UTF-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		JSONObject result = new JSONObject();
		result.put("status", "failed");
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			if (items != null) {
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (!item.isFormField()) {
						System.out.println(item.getName());
						String extFileName = FilenameUtils.getExtension(item.getName());
						System.out.println(extFileName);
						String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extFileName;
						System.out.println(fileName);
						System.out.println(getServletContext().getRealPath("/files"));
						if (extFileName.equals("xls")) {
							try {
								String attrBaseName = "a_" + FilenameUtils.getBaseName(fileName);
								request.getSession().setAttribute(attrBaseName + "rowCount", 1);
								request.getSession().setAttribute(attrBaseName + "rowNo", 0);
								request.getSession().setAttribute(attrBaseName + "rowContent", "----");
								String fullFileName = getServletContext().getRealPath("/files") + File.separator + fileName;
								item.write(new File(fullFileName));
								result.put("status", "success");
								result.put("fileName", fileName);
								new Thread(new ProcessExcelFile(request.getSession(), fullFileName)).start();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		System.out.println(result.toString(4));
		response.getWriter().append(result.toString(4));
	}
}

class ProcessExcelFile implements Runnable {
	HttpSession httpSession;
	String fileName;

	ProcessExcelFile(HttpSession httpSession, String fileName) {
		this.httpSession = httpSession;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		Workbook wb = null;
		FileInputStream fis = null;
		String attrBaseName = "a_" + FilenameUtils.getBaseName(fileName);
		System.out.println(attrBaseName);
		try {
			fis = new FileInputStream(fileName);
			wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			httpSession.setAttribute(attrBaseName + "rowCount", sheet.getLastRowNum() + 1);
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				StringBuilder sb = new StringBuilder();
				Row row = sheet.getRow(i);
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					if (row.getCell(j) != null) {
						sb.append(row.getCell(j).toString());
						if (j != row.getLastCellNum() - 1)
							sb.append(",");
					}
				}
				System.out.println(sb.toString());
				httpSession.setAttribute(attrBaseName + "rowNo", i + 1);
				httpSession.setAttribute(attrBaseName + "rowContent", sb.toString());
				Thread.sleep(100);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (wb != null)
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		new File(fileName).delete();
	}
}
