package com.bmw.boss.infos.app.pojo.json;

import java.util.List;

public class ResponseNewsItemPojo {

	private String title;
	private List<String> paragraphs;
	private String timestamp;
	private List<ResponseImagesListPojo> images;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(List<String> paragraphs) {
		this.paragraphs = paragraphs;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public List<ResponseImagesListPojo> getImages() {
		return images;
	}
	public void setImages(List<ResponseImagesListPojo> images) {
		this.images = images;
	}

}
