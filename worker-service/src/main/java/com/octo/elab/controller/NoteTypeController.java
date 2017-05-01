package com.octo.elab.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.NoteType;
import com.octo.elab.repository.NoteTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "NoteType", description = "Endpoint pertaining to NoteTypes")
public class NoteTypeController {

	private static final Logger log = LoggerFactory.getLogger(NoteTypeController.class);

	@Resource
	Environment environment;

	@Autowired
	private NoteTypeRepository noteTypeRepo;

	/**
	 * This method is used to fetch all noteTypes from database
	 *
	 * @return ResponseEntity<List<NoteType>>
	 */
	@RequestMapping(value = "/noteTypes", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all NoteTypes")
	public ResponseEntity<List<NoteType>> getNoteTypes() throws Exception {
		log.info("GET /noteTypes API to fetch all noteTypes");
		List<NoteType> noteTypes = noteTypeRepo.getAllNoteTypes();
		return new ResponseEntity<List<NoteType>>(noteTypes, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * noteType information
	 *
	 * @param noteTypeID
	 *            The id of the noteType to be retrieved
	 * @return ResponseEntity<NoteType>
	 */
	@RequestMapping(value = "/noteTypes/{noteTypeID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a noteType by ID")
	public ResponseEntity<NoteType> getNoteTypeByID(
			@ApiParam(value = "noteTypeID value", required = true) @PathVariable Integer noteTypeID) throws Exception {
		log.info("GET /noteTypes/" + noteTypeID);
		NoteType noteType = noteTypeRepo.getNoteTypeByID(noteTypeID);
		return new ResponseEntity<NoteType>(noteType, HttpStatus.OK);
	}
}
