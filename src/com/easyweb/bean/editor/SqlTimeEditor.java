package com.easyweb.bean.editor;

/**
 * java.sql.Time与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlTimeEditor extends DateEditor {

	public SqlTimeEditor() {
		super(java.sql.Time.class);
	}

}