package com.easyweb.io;

import java.io.File;
/**
 * 对文件进行遍历。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class FileWalker {
	FileHandler fileHandler = null;

	public FileWalker(File file, FileHandler fileHandler) {
		if (fileHandler == null)
			throw new NullPointerException("fileHandler must not be null");
		this.fileHandler = fileHandler;
		traverse(file);
	}

	private void traverse(File file) {
		fileHandler.handle(file);
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children != null) {
				for (File child : children)
					traverse(child);
			}
		}
	}
}
