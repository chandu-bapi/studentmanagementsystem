package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentInfoDao;
import com.example.entities.StudentsInfo;
import com.example.utils.StudentIdGenerator;

@Service
public class StudentInfoService {

	@Autowired
	StudentInfoDao studentInfoDao;

	public List<StudentsInfo> getAllStudents() {
		return (List<StudentsInfo>) studentInfoDao.findAll();
	}

	public StudentsInfo saveStudentInfo(StudentsInfo studentRecord)  {
		String maxStudentId = studentInfoDao.getMaxStudentId();
		String studentID = StudentIdGenerator.getCustomStudentID(maxStudentId);
		studentRecord.setStudentId(studentID);
		System.out.println("studentInfoDao.saveAndFlush(studentRecord) ::::::"+studentInfoDao.saveAndFlush(studentRecord));
		return studentInfoDao.saveAndFlush(studentRecord);
	}

	public StudentsInfo updateStudentInfo(StudentsInfo studentRecord) {
		return studentInfoDao.saveAndFlush(studentRecord);
	}

	public boolean deleteStudentByID(String studentId) {
		int isDeleted = studentInfoDao.deleteStudentInfoByID(studentId);
		if (isDeleted == 1) {
			return true;
		}

		return true;

	}

	public StudentsInfo getStudentByID(String studentId) {

		return studentInfoDao.getStudentInfoByID(studentId);
	}

}
