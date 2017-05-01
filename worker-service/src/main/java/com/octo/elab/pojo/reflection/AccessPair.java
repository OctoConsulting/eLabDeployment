package com.octo.elab.pojo.reflection;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class will be used for all id-val mapping for /access API.
 * 
 * @author Sumit Dang
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessPair {

	int id;
	
	int _id;

	String val;
	
	Boolean isSelected;

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	// Constructor
	public AccessPair() {

	}

	public AccessPair(int id) {
		super();
		this.id = id;
	}

	public AccessPair(int id, String val) {
		super();
		this.id = id;
		this.val = val;
	}
	
	public AccessPair(int id, String val, Boolean isSelected) {
		super();
		this.id = id;
		this.val = val;
		this.isSelected = isSelected;
	}

}