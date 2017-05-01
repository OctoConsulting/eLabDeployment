package com.octo.elab.pojo.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
@Entity
@Table(name = "note_detail_items")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteDetailItems {

		@Id
		@Column
		private int id;
		
		@Column(name = "item_type")
		private String itemType;
		
		@Column(name = "identifier")
		private String identifier;
		
		@Column(name = "dropdownlabel")
		private String dropDownLabel;
		
		@Column(name = "value")
		private String value;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getItemType() {
			return itemType;
		}

		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public String getDropDownLabel() {
			return dropDownLabel;
		}

		public void setDropDownLabel(String dropDownLabel) {
			this.dropDownLabel = dropDownLabel;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}


}
