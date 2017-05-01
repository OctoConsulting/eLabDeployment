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

import com.octo.elab.pojo.db.NoteDetailType;
import com.octo.elab.repository.NoteDetailTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "NoteDetailType", description = "Endpoint pertaining to NoteDetailTypes")
public class NoteDetailTypeController {

	private static final Logger log = LoggerFactory.getLogger(NoteDetailTypeController.class);

	@Resource
	Environment environment;

	@Autowired
	private NoteDetailTypeRepository noteDetailTypeRepo;

	/**
	 * This method is used to fetch all noteDetailTypes from database
	 *
	 * @return ResponseEntity<List<NoteDetailType>>
	 */
	@RequestMapping(value = "/noteDetailTypes", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all NoteDetailTypes")
	public ResponseEntity<List<NoteDetailType>> getNoteDetailTypes() throws Exception {
		log.info("GET /noteDetailTypes API to fetch all noteDetailTypes");
		List<NoteDetailType> noteDetailTypes = noteDetailTypeRepo.getAllNoteDetailTypes();
		return new ResponseEntity<List<NoteDetailType>>(noteDetailTypes, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * noteDetailType information
	 *
	 * @param noteDetailTypeID
	 *            The id of the noteDetailType to be retrieved
	 * @return ResponseEntity<NoteDetailType>
	 */
	@RequestMapping(value = "/noteDetailTypes/{noteDetailTypeID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a noteDetailType by ID")
	public ResponseEntity<NoteDetailType> getNoteDetailTypeByID(
			@ApiParam(value = "noteDetailTypeID value", required = true) @PathVariable Integer noteDetailTypeID) throws Exception {
		log.info("GET /noteDetailTypes/" + noteDetailTypeID);
		NoteDetailType noteDetailType = noteDetailTypeRepo.getNoteDetailTypeByID(noteDetailTypeID);
		return new ResponseEntity<NoteDetailType>(noteDetailType, HttpStatus.OK);
	}
}
