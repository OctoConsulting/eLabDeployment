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

import com.octo.elab.pojo.db.ExamType;
import com.octo.elab.repository.ExamTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "ExamType", description = "Endpoint pertaining to ExamTypes")
public class ExamTypeController {

	private static final Logger log = LoggerFactory.getLogger(ExamTypeController.class);

	@Resource
	Environment environment;

	@Autowired
	private ExamTypeRepository examTypeRepo;

	/**
	 * This method is used to fetch all examTypes from database
	 *
	 * @return ResponseEntity<List<ExamType>>
	 */
	@RequestMapping(value = "/examTypes", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all ExamTypes")
	public ResponseEntity<List<ExamType>> getExamTypes() throws Exception {
		log.info("GET /examTypes API to fetch all examTypes");
		List<ExamType> examTypes = examTypeRepo.getAllExamTypes();
		return new ResponseEntity<List<ExamType>>(examTypes, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * ExamType information
	 *
	 * @param examTypeID
	 *            The id of the ExamType to be retrieved
	 * @return ResponseEntity<ExamType>
	 */
	@RequestMapping(value = "/examTypes/{examTypeID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a ExamType by ID")
	public ResponseEntity<ExamType> getExamTypeByID(
			@ApiParam(value = "examTypeID value", required = true) @PathVariable Integer examTypeID) throws Exception {
		log.info("GET /examTypes/" + examTypeID);
		ExamType examType = examTypeRepo.getExamTypeByID(examTypeID);
		return new ResponseEntity<ExamType>(examType, HttpStatus.OK);
	}
}
