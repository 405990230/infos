package com.bmw.boss.infos.app.pojo.api;

import java.util.List;

public class NewsListAPIPojo {

	private int currentPage;
	private List<NewsItemPojo> item;
	private int itemSize;
	private int newsSum;
	private String status;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<NewsItemPojo> getItem() {
		return item;
	}
	public void setItem(List<NewsItemPojo> item) {
		this.item = item;
	}
	public int getItemSize() {
		return itemSize;
	}
	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
	}
	public int getNewsSum() {
		return newsSum;
	}
	public void setNewsSum(int newsSum) {
		this.newsSum = newsSum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
