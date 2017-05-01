package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.NoteType;

public interface NoteTypeRepository extends JpaRepository<NoteType, Integer> {

	@Query(value = "select nd.* from elab.note_type nd where (nd.id = :id)", nativeQuery = true)
	public NoteType getNoteTypeByID(@Param("id") Integer id);

	@Query(value = "select nd.* from elab.note_type nd order by nd.id", nativeQuery = true)
	public List<NoteType> getAllNoteTypes();

	@Query(value = "select nd.id from elab.note_type nd order by nd.id", nativeQuery = true)
	public Integer[] getAllNoteTypeIDs();

	@Query(value = "select max(nd.id) from elab.note_type nd", nativeQuery = true)
	public Integer getMaxNoteTypeID();

}
