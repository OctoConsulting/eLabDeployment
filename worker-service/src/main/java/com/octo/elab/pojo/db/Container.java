package com.octo.elab.pojo.db;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Container {
	private String containerName;
	private Integer evidenceType;
	private Integer id;
	private Integer _id;
	private List<Package> packages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public Integer getEvidenceType() {
		return evidenceType;
	}

	public void setEvidenceType(Integer evidenceType) {
		this.evidenceType = evidenceType;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

}