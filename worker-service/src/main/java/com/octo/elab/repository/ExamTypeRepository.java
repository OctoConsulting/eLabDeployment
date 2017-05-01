package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.ExamType;

public interface ExamTypeRepository extends JpaRepository<ExamType, Integer> {

	@Query(value = "select e.* from elab.exam_type e where (e.id = :ExamTypeID)", nativeQuery = true)
	public ExamType getExamTypeByID(@Param("ExamTypeID") Integer ExamTypeID);

	@Query(value = "select e.* from elab.exam_type e order by e.id", nativeQuery = true)
	public List<ExamType> getAllExamTypes();

	@Query(value = "select e.id from elab.exam_type e order by e.id", nativeQuery = true)
	public Integer[] getAllExamTypeIDs();

	@Query(value = "select max(_id) from elab.exam_type", nativeQuery = true)
	public Integer getMaxExamTypeID();

}
