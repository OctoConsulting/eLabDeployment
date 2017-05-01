package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Case;

public interface CaseRepository extends JpaRepository<Case, Integer> {

	@Query(value = "select c.* from elab.Case c where (c.id = :CaseID)", nativeQuery = true)
	public Case getCaseByID(@Param("CaseID") Integer CaseID);

	@Query(value = "select c.* from elab.Case c order by c.id", nativeQuery = true)
	public List<Case> getAllCases();

}
