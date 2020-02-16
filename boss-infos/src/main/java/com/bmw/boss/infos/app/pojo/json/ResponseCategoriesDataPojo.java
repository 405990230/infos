package com.bmw.boss.infos.app.pojo.json;

import java.util.List;

public class ResponseCategoriesDataPojo {

	private String oid;
	private List<String> categories;
	private List<String> names;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
}
