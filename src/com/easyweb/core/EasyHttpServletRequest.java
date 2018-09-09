package com.easyweb.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

/**
 * 对HttpServletRequest接口进行封装，提供一些额外的功能。
 *
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class EasyHttpServletRequest extends HttpServletRequestWrapper {
	public final static String REQ_PARAMS = "reqParams";// 请求参数的属性名称
	public final static String PARAM_ERRORS = "paramErrors";// 字段转换或校验错误信息的属性名称
	public final static String EXE_ERRORS = "exeErrors";// 程序执行时遇到的非字段校验错误信息的属性名称
	public final static String REQ_RESULT = "reqResult";// forward请求结果的属性名称

	public final static String FIELD_NOT_NEED_TRIM = "fieldNotNeedTrim";// 不需要trim的表单字段名称
	public final static String ARRAY_FIELDS = "arrayFields";// 数组类型的字段使用"[字段名][字段名]..."格式
	public final static String ARRAY_SEPARATOR = "arraySeparator";// 在字符串数组参数合并成字符串时使用的分隔符号
	public final static String[] IGNORED_FIELDS = { FIELD_NOT_NEED_TRIM, ARRAY_FIELDS, ARRAY_SEPARATOR };

	// 构造函数
	public EasyHttpServletRequest(HttpServletRequest request, String charset) {
		super(request);
		try {
			setCharacterEncoding(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setAttribute(REQ_PARAMS, convertParamsToString());
		setAttribute(PARAM_ERRORS, new HashMap<String, List<String>>());
		setAttribute(EXE_ERRORS, new ArrayList<String>());
	}

	public boolean isMultiPart() {
		String contentType = getContentType();
		if (contentType != null && contentType.contains("multipart/form-data;"))
			return true;
		return false;
	}

	// 将请求参数同一放到一个Map里，键值都为字符串形式，
	// 此处字符串数组被转换成了使用分隔符(默认是逗号)隔开的字符串，
	// 便于后面向Bean转化。
	private Map<String, String> convertParamsToString() {
		String fieldNotNeedTrim = getParameter(FIELD_NOT_NEED_TRIM);
		if (fieldNotNeedTrim == null)
			fieldNotNeedTrim = "";
		String arrayFields = getParameter(ARRAY_FIELDS);
		if (arrayFields == null)
			arrayFields = "";
		String arraySeparator = getParameter(ARRAY_SEPARATOR);
		if (arraySeparator == null)
			arraySeparator = ",";

		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, String[]> parameterMap = null;
		if (!isMultiPart()) {// 如果不是multipart则直接获取参数的Map
			parameterMap = getParameterMap();
		} else {// 如果是multipart
			parameterMap = new HashMap<String, String[]>();
			Collection<Part> parts = null;
			try {
				parts = getParts();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			if (parts != null) {
				Map<String, List<String>> tempMap = new HashMap<String, List<String>>();
				for (Part part : parts) {
					String partName = part.getName();
					if (part.getContentType() == null) {
						parameterMap.put(partName, getParameterValues(partName));
					} else {
						/**
						 * 文件输入域会生成两个以"Name"和"Size"结尾的两个输入参数对应的两个输入参数， “Name”结尾的对应文件名，"Size"结尾的对应文件大小。
						 * 例如<input name="jpegFile" type="file"/> jpegFileName存储的是文件名；
						 * jpegFileSize存储的是文件大小，单位为字节。
						 */
						List<String> fileNameList = tempMap.get(partName + "Name");
						if (fileNameList == null) {
							fileNameList = new ArrayList<String>();
							tempMap.put(partName + "Name", fileNameList);
						}
						fileNameList.add(getFilename(part));
						List<String> fileSizeList = tempMap.get(partName + "Size");
						if (fileSizeList == null) {
							fileSizeList = new ArrayList<String>();
							tempMap.put(partName + "Size", fileSizeList);
						}
						fileSizeList.add(Long.toString(part.getSize()));
					}
				}
				for (Map.Entry<String, List<String>> entry : tempMap.entrySet()) {
					parameterMap.put(entry.getKey(), entry.getValue().toArray(new String[] {}));
				}
			}
		}
		List<String> ignoredFieldsList = Arrays.asList(IGNORED_FIELDS);
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			if (!ignoredFieldsList.contains(key)) {
				String[] values = entry.getValue();
				if (arrayFields.contains("[" + key + "]")) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < values.length; i++) {
						sb.append(values[i]);
						if (i != values.length - 1)
							sb.append(arraySeparator);
					}
					parameters.put(key, sb.toString());
				} else {
					if (fieldNotNeedTrim.contains("[" + key + "]"))
						parameters.put(key, values[0]);
					else
						parameters.put(key, values[0].trim());
				}
			}
		}
		return parameters;
	}

	// 用于获取上传文件时的文件名
	protected String getFilename(Part part) {
		String contentDispositionHeader = part.getHeader("content-disposition");
		String[] elements = contentDispositionHeader.split(";");
		for (String element : elements) {
			if (element.trim().startsWith("filename")) {
				return FilenameUtils.getName(element.substring(element.indexOf('=') + 1).trim().replace("\"", ""));
			}
		}
		return null;
	}

	public String[] getParamSegments() {
		String basePath = getContextPath() + getServletPath();
		if (getRequestURI().length() > basePath.length()) {
			return getRequestURI().substring(basePath.length() + 1).split("/");
		}
		return null;
	}
}
