package com.sample.db.model;

import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class Doc {
	private int id = -1;
	@Pattern(regexp = ".*(.png)$", message = "必须是 png 文件")
	private String fileName;
	@Min(value = 1024, message = "文件最小长度{value}")
	private long fileSize;

	private String path;
	private Timestamp dateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Doc [id=" + id + ", fileName=" + fileName + ", fileSize=" + fileSize + ", path=" + path + ", dateTime="
				+ dateTime + "]";
	}
}
