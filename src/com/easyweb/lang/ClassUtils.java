package com.easyweb.lang;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.easyweb.io.FileHandler;
import com.easyweb.io.FileWalker;
/**
 * 与类有关的工具类。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public final class ClassUtils {
	private ClassUtils() {
	}
	/**
	 * 获取ClassLoader加载的所有文件，包括存放在jar文件中的。
	 */
	public static List<String> getAllFilesInClassPath(URLClassLoader urlClassLoader) throws IOException {
		List<String> list = new ArrayList<String>();
		URL[] classPath = urlClassLoader.getURLs();
		for (URL url : classPath) {
			File cpFile = new File(url.getFile());
			if (cpFile.isDirectory()) {
				List<File> fileList = new ArrayList<File>();
				new FileWalker(new File(url.getFile()), new FileHandler() {
					@Override
					public void handle(File file) {
						if (file.isFile())
							fileList.add(file);
					}
				});
				int beginIndex = cpFile.getCanonicalPath().length();
				for (File f : fileList) {
					list.add(f.getAbsolutePath().substring(beginIndex).replace(File.separatorChar, '/'));
				}
			}
			if (url.getPath().endsWith(".jar")) {
				JarURLConnection jarURLConnection = (JarURLConnection) new URL("jar:" + url + "!/").openConnection();
				JarFile jarFile = jarURLConnection.getJarFile();
				Enumeration<JarEntry> jarEntries = jarFile.entries();
				while (jarEntries.hasMoreElements()) {
					JarEntry jarEntry = jarEntries.nextElement();
					if (!jarEntry.isDirectory()) {
						list.add("/"+jarEntry.getName());
					}
				}
			}
		}
		list = new ArrayList<String>(new HashSet<String>(list));
		return list;
	}
}
