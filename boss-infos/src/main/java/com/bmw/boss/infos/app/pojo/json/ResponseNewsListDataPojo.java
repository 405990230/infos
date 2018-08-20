package com.bmw.boss.infos.app.pojo.json;

import java.util.List;

public class ResponseNewsListDataPojo {

	private String oid;
	private List<ResponseNewsItemPojo> items;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public List<ResponseNewsItemPojo> getItems() {
		return items;
	}
	public void setItems(List<ResponseNewsItemPojo> items) {
		this.items = items;
	}
}
