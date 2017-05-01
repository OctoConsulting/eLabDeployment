package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

	@Query(value = "select e.*,ex.examiner_name ,et.description from elab.Exam e inner join elab.examiner as ex on e.examiner_id = ex.id inner join elab.exam_type as et on e.exam_type = et.id where (e.id = :ExamID)", nativeQuery = true)
	public Exam getExamByID(@Param("ExamID") Integer ExamID);

	@Query(value = "select e.*,ex.examiner_name as examinerName ,et.description from elab.Exam e inner join elab.examiner as ex on e.examiner_id = ex.id inner join elab.exam_type as et on e.exam_type = et.id order by e.id", nativeQuery = true)
	public List<Exam> getAllExams();

	@Query(value = "select e.id from elab.Exam e order by e.id", nativeQuery = true)
	public Integer[] getAllExamIDs();

	@Query(value = "select max(id) from elab.Exam", nativeQuery = true)
	public Integer getMaxExamID();

	@Query(value = "select e.*,ex.examiner_name as examinerName ,et.description from elab.Exam e inner join elab.examiner as ex on e.examiner_id = ex.id inner join elab.exam_type as et on e.exam_type = et.id where (e.case_id = :CaseID)", nativeQuery = true)
	public List<Exam> getExamsByCaseID(@Param("CaseID") Integer CaseID);

	@Query(value = "select e.evidence_id from elab.Exam e where (e.case_id = :CaseID)", nativeQuery = true)
	public Integer[] getAllEvidencesByCaseID(@Param("CaseID") Integer CaseID);

	@Query(value = "select e.*,ex.examiner_name ,et.description from elab.Exam e inner join elab.examiner as ex on e.examiner_id = ex.id inner join elab.exam_type as et on e.exam_type = et.id where (e.examiner_id = :ExaminerID)", nativeQuery = true)
	public Exam getExamByExaminerID(@Param("ExaminerID") Integer ExaminerID);
	
	@Query(value = "select e.evidence_id from elab.Exam e where (e.case_id = :CaseID)", nativeQuery = true)
	public Integer[] getExamEvidencesByCaseID(@Param("CaseID") Integer CaseID);
	
	@Query(value = "select e.* from elab.Exam e where (e.case_id = :CaseID) and (e.evidence_id = :EvidenceID)", nativeQuery = true)
	public Exam getExamIDByCaseIDAndEvidenceID(@Param("CaseID") Integer CaseID,@Param("EvidenceID") Integer EvidenceID);
	
	@Query(value = "select e.* from elab.Exam e where (e.case_id = :CaseID) and (e._id = :_id)", nativeQuery = true)
	public List<Exam> getExamIDByCaseIDAnd_id(@Param("CaseID") Integer CaseID,@Param("_id")Integer _id);
	
	@Query(value = "select distinct e._id from elab.Exam e where (e.case_id = :CaseID)", nativeQuery = true)
	public Integer[] get_idByCaseID(@Param("CaseID") Integer CaseID);
	
	@Query(value = "select e.evidence_id from elab.Exam e where (e.case_id = :CaseID) and (e._id = :_id)", nativeQuery = true)
	public Integer[] getExamEvidenceIDsByCaseIDAnd_id(@Param("CaseID") Integer CaseID,@Param("_id")Integer _id);
	
	@Query(value = "select max(_id) from elab.Exam where case_id = :caseID", nativeQuery = true)
	public Integer getMaxEvidence_ID(@Param("caseID") Integer caseID);
	
	@Query(value = "select e.* from elab.Exam e where (e.case_id = :CaseID) and (e._id = :_id)", nativeQuery = true)
	public List<Exam> getExamsEvidenceIDsByCaseIDAnd_id(@Param("CaseID") Integer CaseID,@Param("_id")Integer _id);
}
