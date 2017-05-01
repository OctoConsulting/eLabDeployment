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

import com.octo.elab.pojo.db.EvidenceType;
import com.octo.elab.repository.EvidenceTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "EvidenceType", description = "Endpoint pertaining to EvidenceTypes")
public class EvidenceTypeController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceTypeController.class);

	@Resource
	Environment environment;

	@Autowired
	private EvidenceTypeRepository evidenceTypeRepo;

	/**
	 * This method is used to fetch all evidenceTypes from database
	 *
	 * @return ResponseEntity<List<EvidenceType>>
	 */
	@RequestMapping(value = "/evidencetypes", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all EvidenceTypes")
	public ResponseEntity<List<EvidenceType>> getEvidenceTypes() throws Exception {
		log.info("GET /evidenceTypes API to fetch all evidenceTypes");
		List<EvidenceType> evidenceTypes = evidenceTypeRepo.getAllEvidenceTypes();
		return new ResponseEntity<List<EvidenceType>>(evidenceTypes, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * EvidenceType information
	 *
	 * @param evidenceTypeID
	 *            The id of the EvidenceType to be retrieved
	 * @return ResponseEntity<EvidenceType>
	 */
	@RequestMapping(value = "/evidencetypes/{evidenceTypeID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a EvidenceType by ID")
	public ResponseEntity<EvidenceType> getEvidenceTypeByID(
			@ApiParam(value = "evidenceTypeID value", required = true) @PathVariable Integer evidenceTypeID)
			throws Exception {
		log.info("GET /evidenceTypes/" + evidenceTypeID);
		EvidenceType evidenceType = evidenceTypeRepo.getEvidenceTypeByID(evidenceTypeID);
		return new ResponseEntity<EvidenceType>(evidenceType, HttpStatus.OK);
	}
}
