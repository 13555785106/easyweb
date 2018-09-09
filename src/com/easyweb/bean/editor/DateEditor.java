package com.easyweb.bean.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {
	private SimpleDateFormat sdf;
	private Class<?> clz;

	public DateEditor() {
		this(java.util.Date.class);
	}

	public DateEditor(Class<?> clz) {
		this.clz = clz;
		if (clz == java.util.Date.class)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else if (clz == java.sql.Date.class)
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		else if (clz == java.sql.Time.class)
			sdf = new SimpleDateFormat("HH:mm:ss");
		else if (clz == java.sql.Timestamp.class)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date = null;
		try {
			date = sdf.parse(text);
			setValue(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getAsText() {
		return sdf.format(getValue());
	}

	@Override
	public Object getValue() {
		Date date = (Date) super.getValue();
		if (clz == java.sql.Date.class)
			return new java.sql.Date(date.getTime());
		else if (clz == java.sql.Time.class)
			return new java.sql.Time(date.getTime());
		else if (clz == java.sql.Timestamp.class)
			return new java.sql.Timestamp(date.getTime());
		return date;
	}

}
