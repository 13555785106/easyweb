package com.easyweb.listener;

import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitApp implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		registerPropertyEditor();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 用来注册属性编辑器。
	 */
	private void registerPropertyEditor() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream("/PropertyEditor.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			try {
				PropertyEditorManager.registerEditor(Class.forName(entry.getKey().toString()),
						Class.forName(entry.getValue().toString()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
