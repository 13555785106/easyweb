package com.easyweb.core;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

/**
 * 公共信息类，用来获取定义的键值对信息。 使用一般属性文件存储，并放在classpath的根目录。
 * 这个文件必须与本类同名，并以properties作为扩展名。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class Messages {
	private static Messages instance = null;
	private Properties properties = new Properties();

	private Messages() {
		try {
			String messagesFileName = "/" + this.getClass().getName().replace('.', '/') + ".properties";
			URL url = getClass().getClassLoader().getResource(messagesFileName);
			if (url != null) {// 判断属性文件是否存在，存在后加载到属性实例中。
				properties.load(getClass().getResourceAsStream(messagesFileName));
			}
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Messages getInstance() {
		if (instance == null)
			instance = new Messages();
		return instance;
	}

	public Map<String, String> getMessages() {
		Map<String, String> messagesMap = new HashMap<String, String>();
		for (String key : properties.stringPropertyNames()) {
			messagesMap.put(key, properties.getProperty(key));
		}
		return messagesMap;
	}
}
