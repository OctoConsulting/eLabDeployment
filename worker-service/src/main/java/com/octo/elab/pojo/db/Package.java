package com.octo.elab.pojo.db;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Package {

	private String packageName;
	private Integer evidenceType;
	private List<Evidence> items;
	private Integer id;
	private Integer _id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getEvidenceType() {
		return evidenceType;
	}

	public void setEvidenceType(Integer evidenceType) {
		this.evidenceType = evidenceType;
	}

	public List<Evidence> getItems() {
		return items;
	}

	public void setItems(List<Evidence> items) {
		this.items = items;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

}