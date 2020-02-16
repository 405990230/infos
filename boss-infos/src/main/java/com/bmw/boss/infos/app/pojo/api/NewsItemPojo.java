package com.bmw.boss.infos.app.pojo.api;

import java.util.List;

public class NewsItemPojo {

	private String abstracts;
	private String author;
	private String content;
	private String newsId;
	private String newsType;
	private int picCount;
	private List<PictureItemPojo> pictures;
	private String pubDate;
	private String smallpicurl;
	private String source;
	private String title;
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = (content == null ? "" : content.trim());

	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public int getPicCount() {
		return picCount;
	}
	public void setPicCount(int picCount) {
		this.picCount = picCount;
	}
	public List<PictureItemPojo> getPictures() {
		return pictures;
	}
	public void setPictures(List<PictureItemPojo> pictures) {
		this.pictures = pictures;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getSmallpicurl() {
		return smallpicurl;
	}
	public void setSmallpicurl(String smallpicurl) {
		this.smallpicurl = smallpicurl;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
