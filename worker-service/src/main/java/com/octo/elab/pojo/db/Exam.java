package com.octo.elab.pojo.db;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.octo.elab.utilities.CustomTimestampDeserializer;
import com.octo.elab.utilities.CustomTimestampSerializer;

@Entity
@Table(name = "exam")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exam {

	@Id
	@Column(name = "id")
	@JsonProperty("id")
	private Integer ID;

	@Column(name = "case_id")
	private Integer caseId;

	@Column(name = "_id")
	private Integer _id;

	@Column(name = "exam_name")
	private String examName;

	@Column(name = "exam_type")
	private Integer examType;

	@Column(name = "examiner_id")
	private Integer examinerId;

	@Column(name = "evidence_id")
	private Integer evidenceId;

	@Transient
	private Integer[] evidenceIds;

	@Transient
	private String examTypeName;

	public String getExamTypeName() {
		return examTypeName;
	}

	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}

	public Integer getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(Integer evidenceId) {
		this.evidenceId = evidenceId;
	}

	public Integer[] getEvidenceIds() {
		return evidenceIds;
	}

	public void setEvidenceIds(Integer[] evidenceIds) {
		this.evidenceIds = evidenceIds;
	}

	@Column(name = "assigned_date")
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	private Timestamp assignedDate;

	@Column(name = "start_date")
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	private Timestamp startDate;

	@Column(name = "end_date")
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	private Timestamp endDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	private Timestamp createdDate;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	@JsonSerialize(using = CustomTimestampSerializer.class)
	@JsonDeserialize(using = CustomTimestampDeserializer.class)
	private Timestamp updatedDate;

	@Transient
	private List<Evidence> items;

	@Transient
	private String description;

	@Transient
	private String examinerName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getID() {
		return ID;
	}

	public String getExaminerName() {
		return examinerName;
	}

	public void setExaminerName(String examinerName) {
		this.examinerName = examinerName;
	}

	public List<Evidence> getItems() {
		return items;
	}

	public void setItems(List<Evidence> items) {
		this.items = items;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getExamType() {
		return examType;
	}

	@Transient
	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public Integer getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Integer examinerId) {
		this.examinerId = examinerId;
	}

	public Timestamp getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Timestamp assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

}