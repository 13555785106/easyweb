package com.easyweb.utils;

import java.util.ArrayList;
import java.util.List;
/*
 * 本类用来分页
 * */
public class Paging<T> {
	private int pageNo = -1;
	private int pageSize;
	private List<T> datas = new ArrayList<T>();
	private int pageCount;

	public Paging(int pageSize, int pageNo, List<T> results) {
		if (pageSize <= 0)
			throw new IllegalArgumentException("无效的分页大小 pageSize=" + pageSize);
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		pageCount = results.size() / pageSize + (results.size() % pageSize == 0 ? 0 : 1);

		if (this.pageNo >= pageCount)
			this.pageNo = pageCount - 1;
		if (this.pageNo < 0)
			this.pageNo = 0;
		for (int i = this.pageNo * pageSize; 
				i < (this.pageNo + 1) * pageSize && 
				i < results.size(); i++) {
			datas.add(results.get(i));
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public int getPageCount() {
		return pageCount;
	}

	public static void main(String[] args) {

	}
}
