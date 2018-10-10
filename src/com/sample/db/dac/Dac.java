package com.sample.db.dac;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
/*
 * 所有数据访问类的基类，完成SQL语句属性文件的加载。
 * 所有Dac子类的属性文件必须与子类同名，并以.xml作为文件后缀。
 * */
public abstract class Dac {
	private Properties propSQL = new Properties();

	public Dac() {
		try {
			String sqlFileName = getClass().getName();
			sqlFileName = sqlFileName.replace('.', '/');
			sqlFileName = "/" + sqlFileName + ".xml";
			propSQL.loadFromXML(getClass().getResourceAsStream(sqlFileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String getSql(String key) {
		return propSQL.getProperty(key);
	}
}
