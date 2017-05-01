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

import com.octo.elab.pojo.db.Examiner;
import com.octo.elab.repository.ExaminerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Examiner", description = "Endpoint pertaining to Examiners")
public class ExaminerController {

	private static final Logger log = LoggerFactory.getLogger(ExaminerController.class);

	@Resource
	Environment environment;

	@Autowired
	private ExaminerRepository examinerRepo;

	/**
	 * This method is used to fetch all examiners from database
	 *
	 * @return ResponseEntity<List<Examiner>>
	 */
	@RequestMapping(value = "/examiners", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Examiners")
	public ResponseEntity<List<Examiner>> getExaminers() throws Exception {
		log.info("GET /examiners API to fetch all examiners");
		List<Examiner> examiners = examinerRepo.getAllExaminers();
		return new ResponseEntity<List<Examiner>>(examiners, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * examiner information
	 *
	 * @param examinerID
	 *            The id of the examiner to be retrieved
	 * @return ResponseEntity<Examiner>
	 */
	@RequestMapping(value = "/examiners/{examinerID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a examiner by ID")
	public ResponseEntity<Examiner> getExaminerByID(
			@ApiParam(value = "examinerID value", required = true) @PathVariable Integer examinerID) throws Exception {
		log.info("GET /examiners/" + examinerID);
		Examiner examiner = examinerRepo.getExaminerByID(examinerID);
		return new ResponseEntity<Examiner>(examiner, HttpStatus.OK);
	}
}
