package com.easyweb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/*
 * 根据文件名或者文件扩展名获取对应的MIME类型
 * */
public class MimeType {
	private static MimeType instance = null;

	public static MimeType getInstance() {
		if (instance == null)
			instance = new MimeType();
		return instance;
	}

	Map<String, String> extNameMimeMap = new HashMap<>();

	private MimeType() {
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					this.getClass().getResourceAsStream("/" + this.getClass().getName().replace('.', '/') + ".txt")));
			while ((line = br.readLine()) != null) {
				String[] segments = line.split("[\\s]+");
				extNameMimeMap.put(segments[0], segments[1]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
	public String getMime(String extName) {
		return extNameMimeMap.get(extName);
	}
	
	public String getMimeByFileName(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if(pos != -1) {
			return getMime(fileName.substring(pos+1));
		}
		return null;
	}	
	
	public static void main(String[] args) {
		System.out.println(MimeType.getInstance().getMimeByFileName("12312312.doc"));
	}
}
