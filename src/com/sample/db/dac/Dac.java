package com.sample.db.dac;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Dac {
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
