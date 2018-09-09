package com.easyweb.bean.editor;
/**
 * java.sql.Date与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlDateEditor extends DateEditor{
	public SqlDateEditor() {
		super(java.sql.Date.class);
	}
}