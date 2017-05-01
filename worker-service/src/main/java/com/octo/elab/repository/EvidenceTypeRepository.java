package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.EvidenceType;

public interface EvidenceTypeRepository extends JpaRepository<EvidenceType, Integer> {

	@Query(value = "select e.* from elab.evidence_type e where (e.id = :EvidenceTypeID)", nativeQuery = true)
	public EvidenceType getEvidenceTypeByID(@Param("EvidenceTypeID") Integer EvidenceTypeID);

	@Query(value = "select e.* from elab.evidence_type e order by e.id", nativeQuery = true)
	public List<EvidenceType> getAllEvidenceTypes();

}
