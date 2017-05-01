package com.octo.elab.pojo.reflection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvidenceNew {

	private List<AccessPair> evidenceType = null;
	private List<AccessPair> parentType = null;
	private List<AccessPair> parentEvidenceNumber = null;
	private String Name = null;
	private Boolean forAnalysis =  null;
	
	public List<AccessPair> getParentEvidenceNumber(){
		return parentEvidenceNumber;
	}
	public void setParentEvidenceNumber(List<AccessPair> parentEvidenceNumber) {
		this.parentEvidenceNumber = parentEvidenceNumber;
	}
	public List<AccessPair> getParentType() {
		return parentType;
	}
	public void setParentType(List<AccessPair> parentType) {
		this.parentType = parentType;
	}
	public List<AccessPair> getEvidenceType() {
		return evidenceType;
	}
	public void setEvidenceType(List<AccessPair> evidenceType) {
		this.evidenceType = evidenceType;
	}
	public Boolean getForAnalysis() {
		return forAnalysis;
	}
	public void setForAnalysis(Boolean forAnalysis) {
		this.forAnalysis = forAnalysis;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
}