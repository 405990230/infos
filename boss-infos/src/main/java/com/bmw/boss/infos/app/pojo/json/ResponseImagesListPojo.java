package com.bmw.boss.infos.app.pojo.json;

public class ResponseImagesListPojo {
	private ResponseImagesItemPojo preview;
	private ResponseImagesItemPojo thumb;
	private ResponseImagesItemPojo full;
	public ResponseImagesItemPojo getPreview() {
		return preview;
	}
	public void setPreview(ResponseImagesItemPojo preview) {
		this.preview = preview;
	}
	public ResponseImagesItemPojo getThumb() {
		return thumb;
	}
	public void setThumb(ResponseImagesItemPojo thumb) {
		this.thumb = thumb;
	}
	public ResponseImagesItemPojo getFull() {
		return full;
	}
	public void setFull(ResponseImagesItemPojo full) {
		this.full = full;
	}
}
