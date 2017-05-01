package com.example.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Metadata.ResponseObject;
import com.example.entities.StudentsInfo;
import com.example.service.StudentInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@RequestMapping("/students")
public class StudentsInfoController {

	@Autowired
	StudentInfoService studentInfoService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllStudents() {
		ResponseObject responseObject = new ResponseObject();
		responseObject.setDataResponse(studentInfoService.getAllStudents());
		return new ResponseEntity<>(responseObject, HttpStatus.OK);

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseObject> deleteStudentById(@RequestBody Map<String, String> studentIdJson) {
		ResponseObject responseObject = new ResponseObject();
		String studentId = studentIdJson.get("studentId");

		if (studentId != null || !studentId.equals("")) {
			if (studentInfoService.getStudentByID(studentId) != null) {
				boolean isDeleted = studentInfoService.deleteStudentByID(studentId);
				if (isDeleted) {
					responseObject.setSuccessResponse("Succesfully deleted!");
					return new ResponseEntity<>(responseObject, HttpStatus.OK);
				} else {
					responseObject.setErrorResponse("Sorry! Student cannot be deleted.");
					return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
				}

			} else {
				responseObject.setErrorResponse("Sorry! No Student found based on the Student Id you provided.");
				return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
			}
		} else {
			responseObject.setErrorResponse("Please provide the Student Id");
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> saveStudentInformation(@RequestBody StudentsInfo studentInfoJson) {
		ResponseObject responseObject = new ResponseObject();
		if (studentInfoJson.equals("") || studentInfoJson == null) {
			responseObject.setErrorResponse("Sorry! Student Information not saved. Please Try again");
			return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
		} else {
			try {
				studentInfoService.saveStudentInfo(studentInfoJson);
				responseObject.setSuccessResponse("Student Information has been Saved Successfully");
				return new ResponseEntity<>(responseObject, HttpStatus.OK);
			} catch (DataIntegrityViolationException e) {
				responseObject.setErrorResponse("Sorry! Student lastname cannot be null");
				return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
			}

		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ResponseObject> updateStudentInfo(@RequestBody JsonNode json) {
		ResponseObject responseObject = new ResponseObject();
		StudentsInfo studentInfo_DB = null;
		ObjectWriter ow = null;
		Iterator<String> itr = json.fieldNames();
		String value = null;
		String studentId_Json = json.get("studentId").textValue();

		if (studentId_Json != null) {
			studentInfo_DB = studentInfoService.getStudentByID(studentId_Json);
			ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		}

		if (studentInfo_DB != null) {
			try {

				JSONObject studentsInfoJsonObj = new JSONObject(ow.writeValueAsString(studentInfo_DB));

				while (itr.hasNext()) {
					String resJsonKey = itr.next().toString();
					value = json.get(resJsonKey).textValue();
					Iterator<String> studentsInfoJsonObjItr = studentsInfoJsonObj.keys();
					while (studentsInfoJsonObjItr.hasNext()) {
						String studentsInfoJsonObjKey = studentsInfoJsonObjItr.next().toString();
						if (resJsonKey.toString().equalsIgnoreCase(studentsInfoJsonObjKey.toString())) {
							studentsInfoJsonObj.put(resJsonKey, value);
							break;
						}

					}
				}
				String jsonObjToString = studentsInfoJsonObj.toString();

				StudentsInfo studentsInfoToSave = new ObjectMapper().readValue(jsonObjToString, StudentsInfo.class);
				try {
					studentInfoService.updateStudentInfo(studentsInfoToSave);
					responseObject.setSuccessResponse("Student information updated successfully.");
					return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
				} catch (DataIntegrityViolationException e) {
					responseObject.setErrorResponse("Student last name should not be NULL");
					return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.BAD_REQUEST);
				}

			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
		} else {
			responseObject.setErrorResponse("No Student information found!");
			return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.BAD_REQUEST);
		}
		responseObject.setSuccessResponse("Student ID should not be null");
		return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.BAD_REQUEST);
	}

}
