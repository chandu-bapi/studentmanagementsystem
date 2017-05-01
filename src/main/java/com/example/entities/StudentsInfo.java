package com.example.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="students_inf")
@NamedQuery(name="StudentsInfo.findAll", query="SELECT e FROM StudentsInfo e")
public class StudentsInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="STU_ID")
	private String studentId;
	
	@Column(name="STU_FRST_NM")
	private String firstName;
	
	@Column(name="STU_LST_NM")
	private String lastName;
	
	@Column(name="STU_GENDER")
	private String gender;
	
	@Column(name="STU_DOB")
	private Date dateOfBirth;
	
	@Column(name="COURSE_NAME")
	private String courseName;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

		
	
		
	
	
	
	

}
