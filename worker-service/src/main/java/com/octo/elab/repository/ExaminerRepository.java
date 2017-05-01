package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Examiner;

public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {

	@Query(value = "select e.* from elab.Examiner e where (e.id = :ExaminerID)", nativeQuery = true)
	public Examiner getExaminerByID(@Param("ExaminerID") Integer ExaminerID);

	@Query(value = "select e.* from elab.Examiner e order by e.id", nativeQuery = true)
	public List<Examiner> getAllExaminers();

	@Query(value = "select e.id from elab.Examiner e order by e.id", nativeQuery = true)
	public Integer[] getAllExaminerIDs();

	@Query(value = "select max(id) from elab.Examiner", nativeQuery = true)
	public Integer getMaxExaminerID();

}