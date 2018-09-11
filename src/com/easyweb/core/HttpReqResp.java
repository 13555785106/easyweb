package com.easyweb.core;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.easyweb.validator.SingletonValidator;

/**
 * 将ExtHttpServletRequest与ExtHttpServletResponse包装到一个类中方便使用。
 *
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class HttpReqResp {
	EasyHttpServletRequest request;
	EasyHttpServletResponse response;
	Map<String, String> messagesMap;
	String viewBaseName;

	public HttpReqResp(EasyHttpServletRequest request, EasyHttpServletResponse response,
			Map<String, String> messagesMap, String viewBaseName) {
		this.request = request;
		this.response = response;
		this.messagesMap = messagesMap;
		this.viewBaseName = viewBaseName;
	}
	public void setAttribute(String name,Object val) {
		request.setAttribute(name, val);
	}
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}
	// 便于forward
	public void forward(String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

	public void forwardByViewName(String viewName) throws ServletException, IOException {
		request.getRequestDispatcher(viewBaseName + "-" + viewName).forward(request, response);
	}

	// 便于include
	public void include(String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).include(request, response);
	}

	public void includeByViewName(String viewName) throws ServletException, IOException {
		System.out.println(viewBaseName + "-" + viewName);
		request.getRequestDispatcher(viewBaseName + "-" + viewName).include(request, response);
	}

	public EasyHttpServletRequest getRequest() {
		return request;
	}

	public EasyHttpServletResponse getResponse() {
		return response;
	}

	// 设置需要forward的结果。
	public void setReqResult(Object value) {
		request.setAttribute(EasyHttpServletRequest.REQ_RESULT, value);
	}

	// 获取存放在request的attribute中的请求参数
	@SuppressWarnings("unchecked")
	public Map<String, String> getReqParams() {
		return (Map<String, String>) request.getAttribute(EasyHttpServletRequest.REQ_PARAMS);
	}

	// 在request的attribute中设置请求参数
	public void setReqParams(Map<String, String> parameters) {
		request.setAttribute(EasyHttpServletRequest.REQ_PARAMS, parameters);
	}

	// 获取存放在request的attribute中的字段错误参数
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getParamErrors() {
		return (Map<String, List<String>>) request.getAttribute(EasyHttpServletRequest.PARAM_ERRORS);
	}

	// 获取存放在request的attribute中的错误参数
	@SuppressWarnings("unchecked")
	public List<String> getExeErrors() {
		return (List<String>) request.getAttribute(EasyHttpServletRequest.EXE_ERRORS);
	}

	public boolean hasErrors() {
		if (getParamErrors().size() > 0 || getExeErrors().size() > 0)
			return true;
		return false;
	}

	public boolean addExeError(String message) {
		return getExeErrors().add(message);
	}

	// 向request的attribute中的错误信息中添加信息
	public boolean addParamError(String key, String message) {
		Map<String, List<String>> errorMap = getParamErrors();
		if (errorMap != null) {
			List<String> fieldErrors = errorMap.get(key);
			if (fieldErrors == null) {
				fieldErrors = new ArrayList<String>();
				errorMap.put(key, fieldErrors);
			}
			fieldErrors.add(message);
			return true;
		}
		return false;
	}

	protected String getConversionErrorMessage(String key, Class<?> type) {
		String msg = messagesMap.get("error.conversion.datatype." + type.getName());

		if (msg == null)
			msg = messagesMap.get("error.conversion." + key);

		if (msg == null)
			msg = messagesMap.get("error.conversion.common");
		return msg;
	}

	/*
	 * 对请求参数进行转换并校验
	 */
	public <T> T convertAndValidate(Class<T> clazz) {
		T t = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		if (beanInfo != null && t != null) {
			Map<String, PropertyDescriptor> pdMap = new HashMap<String, PropertyDescriptor>();
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				pdMap.put(propertyDescriptor.getName(), propertyDescriptor);
			}
			for (Map.Entry<String, String> entry : getReqParams().entrySet()) {
				String propName = entry.getKey();
				String propValue = entry.getValue();
				PropertyDescriptor propertyDescriptor = pdMap.get(propName);
				Class<?> propType = null;
				if (propertyDescriptor != null)
					propType = propertyDescriptor.getPropertyType();
				try {
					PropertyEditor editor = PropertyEditorManager.findEditor(propType);
					editor.setAsText(propValue);
					propertyDescriptor.getWriteMethod().invoke(t, editor.getValue());
				} catch (Exception e) {
					//e.printStackTrace();
					if (!propValue.trim().isEmpty() && propType != null) {
						addParamError(propName, getConversionErrorMessage(propName, propType));
					}
				}
			}
			Validator validator = SingletonValidator.getInstance().getValidator();
			Set<ConstraintViolation<T>> set = validator.validate(t);
			for (ConstraintViolation<T> constraintViolation : set) {
				String field = constraintViolation.getPropertyPath().toString();
				addParamError(field, constraintViolation.getMessage());
			}
		}
		return t;
	}
}
