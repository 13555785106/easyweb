package com.sample.db.model;

public class TurningPageForm {
	private String account;
	private int pageNo = 0;
	private int pageSize = 3;
	private String lastPage;
	private String firstPage;
	private String previousPage;
	private String nextPage;
	private String search;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getLastPage() {
		return lastPage;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public String getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	public String getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	@Override
	public String toString() {
		return "TurningPageForm [account=" + account + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", lastPage="
				+ lastPage + ", firstPage=" + firstPage + ", previousPage=" + previousPage + ", nextPage=" + nextPage
				+ ", search=" + search + "]";
	}

}
