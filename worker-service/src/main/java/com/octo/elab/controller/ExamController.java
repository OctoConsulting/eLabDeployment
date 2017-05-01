 package com.octo.elab.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.Evidence;
import com.octo.elab.pojo.db.Exam;
import com.octo.elab.pojo.db.ExamType;
import com.octo.elab.pojo.db.Examiner;
import com.octo.elab.pojo.reflection.AccessPair;
import com.octo.elab.pojo.reflection.ExaminationNew;
import com.octo.elab.repository.EvidenceRepository;
import com.octo.elab.repository.ExamRepository;
import com.octo.elab.repository.ExamTypeRepository;
import com.octo.elab.repository.ExaminerRepository;

import edu.emory.mathcs.backport.java.util.Arrays;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Exam", description = "Endpoint pertaining to Exams")
public class ExamController {

	private static final Logger log = LoggerFactory.getLogger(ExamController.class);

	@Resource
	Environment environment;

	@Autowired
	private ExamRepository examRepo;

	@Autowired
	private ExamTypeRepository examTypeRepo;

	@Autowired
	private EvidenceRepository evidenceRepo;

	@Autowired
	private ExaminerRepository examinerRepo;

	/**
	 * This method is used to fetch all exams from database
	 *
	 * @return ResponseEntity<List<Exam>>
	 */
	@RequestMapping(value = "/exams", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Exams")
	public ResponseEntity<List<Exam>> getExams(@RequestParam(value = "caseId", required = false) Integer caseId)
			throws Exception {
		log.info("GET /exams API to fetch all exams");
		List<Exam> exams = new ArrayList<Exam>();

		List<ExamType> examTypeNameList = examTypeRepo.getAllExamTypes();
		List<Examiner> examinerNameList = examinerRepo.getAllExaminers();

		HashMap<Integer, String> examTypeHashMap = new HashMap<>();
		HashMap<Integer, String> examinerHashMap = new HashMap<>();

		for (ExamType examType : examTypeNameList) {
			examTypeHashMap.put(examType.getId(), examType.getDescription());
		}

		for (Examiner examiner : examinerNameList) {
			examinerHashMap.put(examiner.getId(), examiner.getExaminerName());
		}
		if (caseId == null) {
			exams = examRepo.getAllExams();
			return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
		} else {
			List<Integer> examIDPerCaseList = Arrays.asList(examRepo.get_idByCaseID(caseId));
			for (Integer examIDPerCase : examIDPerCaseList) {
				Integer[] evidenceIDs = examRepo.getExamEvidenceIDsByCaseIDAnd_id(caseId, examIDPerCase);
				List<Exam> examsByCaseAnd_ID = examRepo.getExamsEvidenceIDsByCaseIDAnd_id(caseId, examIDPerCase);
				Exam exam = examsByCaseAnd_ID.get(0);
				List<Evidence> evidences = evidenceRepo.getEvidencesByID(evidenceIDs);
				Exam examNew = new Exam();
				examNew.setCaseId(caseId);
				examNew.setEndDate(exam.getEndDate());
				examNew.setStartDate(exam.getStartDate());
				examNew.setExamName(exam.getExamName());
				examNew.setItems(evidences);
				examNew.set_id(examIDPerCase);
				examNew.setEvidenceId(null);
				examNew.setExamTypeName(examTypeHashMap.get(exam.getExamType()));
				examNew.setExaminerName(examinerHashMap.get(exam.getExaminerId()));
				exams.add(examNew);
			}

		}
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}

	/**
	 * This method is used to populate dropdowns for creating/viewing/editing an
	 * exam
	 *
	 * @return ResponseEntity<ExaminationNew>
	 */

	@RequestMapping(value = "/ui/exams/", method = RequestMethod.GET)
	@ApiOperation(value = "Populating info for creating/viewing/editing an exam")
	public ResponseEntity<ExaminationNew> getExamsInfo(@RequestParam(value = "mode", required = true) String mode,
			@RequestParam(value = "examID", required = false) Integer examID,
			@RequestParam(value = "caseID", required = false) Integer caseID) throws Exception {
		log.info("GET /exams API to fetch all exams");
		ExaminationNew examinationNew = new ExaminationNew();
		List<ExamType> examTypeList = examTypeRepo.getAllExamTypes();
		List<Examiner> examinerList = examinerRepo.getAllExaminers();
		List<Evidence> evidenceList = evidenceRepo.getAllEvidencesForExam();
		List<AccessPair> examTypeAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> examinerAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> evidenceAccessPairList = new ArrayList<AccessPair>();
		Exam examToBeEdited = null;
		List<Exam> examToBeEditedMultiple = null;
		List<Integer> evidenceIDs = new ArrayList<>();

		if (mode.equalsIgnoreCase("edit")) {
			if (examID != null) {
				// examToBeEdited = examRepo.getExamByID(examID);
				examToBeEditedMultiple = examRepo.getExamIDByCaseIDAnd_id(caseID, examID);
				examToBeEdited = examToBeEditedMultiple.get(0);
				if (examToBeEdited == null) {
					return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.BAD_REQUEST);
				} else {
					for (Exam readEvidenceForExam : examToBeEditedMultiple) {
						evidenceIDs.add(readEvidenceForExam.getEvidenceId());
					}
					examinationNew.setAssignedDate(examToBeEdited.getAssignedDate());
					examinationNew.setStartDate(examToBeEdited.getStartDate());
					examinationNew.setEndDate(examToBeEdited.getEndDate());
					examinationNew.setName(examToBeEdited.getExamName());
				}
			} else {
				return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.BAD_REQUEST);
			}

		}
		// Set ExamType
		AccessPair examTypeAccessPair;
		for (ExamType examType : examTypeList) {
			examTypeAccessPair = new AccessPair();
			examTypeAccessPair.setId(examType.getId());
			examTypeAccessPair.setVal(examType.getDescription());
			if (examToBeEdited != null && (examToBeEdited.getExamType() == examType.getId())) {
				examTypeAccessPair.setIsSelected(true);
			}
			examTypeAccessPairList.add(examTypeAccessPair);
		}

		// Set Examiners
		AccessPair examinerAccessPair;
		for (Examiner examiner : examinerList) {
			examinerAccessPair = new AccessPair();
			examinerAccessPair.setId(examiner.getId());
			examinerAccessPair.setVal(examiner.getExaminerName());
			if (examToBeEdited != null && (examToBeEdited.getExaminerId() == examiner.getId())) {
				examinerAccessPair.setIsSelected(true);
			}
			examinerAccessPairList.add(examinerAccessPair);
		}

		// Set Evidences
		AccessPair evidenceAccessPair;
		for (Evidence evidence : evidenceList) {
			evidenceAccessPair = new AccessPair();
			evidenceAccessPair.setId(evidence.getId());
			evidenceAccessPair.setVal(evidence.getEvidenceName());
			evidenceAccessPair.set_id(evidence.get_id());
			if (evidenceIDs.contains(evidence.getId())) {
				evidenceAccessPair.setIsSelected(true);
			}

			evidenceAccessPairList.add(evidenceAccessPair);
		}

		examinationNew.setEvidences(evidenceAccessPairList);
		examinationNew.setExaminers(examinerAccessPairList);
		examinationNew.setExamType(examTypeAccessPairList);
		if (examToBeEdited != null) {
			examinationNew.set_id(examToBeEdited.get_id());
		}

		return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified Exam
	 * information
	 *
	 * @param examID
	 *            The id of the Exam to be retrieved
	 * @return ResponseEntity<Exam>
	 */
	@RequestMapping(value = "/exams/{examID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a Exam by ID")
	public ResponseEntity<Exam> getExamByID(
			@ApiParam(value = "examID value", required = true) @PathVariable Integer examID) throws Exception {
		log.info("GET /exams/" + examID);
		Exam exam = examRepo.getExamByID(examID);
		return new ResponseEntity<Exam>(exam, HttpStatus.OK);
	}

	/**
	 * This method is used to add/update exam
	 * 
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/exams/", method = RequestMethod.POST)
	@ApiOperation(value = "Add new exam or edit new exam")
	public ResponseEntity<String> updateExam(@RequestBody Exam exam) throws Exception {
		log.info("POST /exams/");
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		Integer caseID = exam.getCaseId();
		Integer _id = exam.get_id();
		Integer[] evidenceIDs = exam.getEvidenceIds();
		if(evidenceIDs == null)
			{
			 	evidenceIDs = new Integer[1];
			 	evidenceIDs[0] = 0;
			}

		// delete
		if (caseID == null) {
			return new ResponseEntity<String>("Please provide caseID", HttpStatus.BAD_REQUEST);
		}
		if (_id != null) {
			List<Exam> examToBeDeleted = examRepo.getExamIDByCaseIDAnd_id(caseID, _id);
			examRepo.delete(examToBeDeleted);
		}

		Integer max_id = examRepo.getMaxEvidence_ID(caseID);
		if(_id == null){
			exam.set_id((max_id != null ? max_id : 0) + 1);
		}
		
		for (Integer newEvidences : evidenceIDs) {
			// Insert new
			Integer maxID = examRepo.getMaxExamID();
			exam.setID((maxID != null ? maxID : 0) + 1);
			exam.setCreatedBy("elab");
			exam.setUpdatedBy("elab");
			exam.setCreatedDate(timeStamp);
			exam.setEvidenceId(newEvidences);
			exam.setUpdatedDate(timeStamp);
			examRepo.saveAndFlush(exam);
		}
		return new ResponseEntity<String>("Success!!", HttpStatus.CREATED);
	}
}
