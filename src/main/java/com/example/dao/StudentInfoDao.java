package com.example.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.StudentsInfo;

@Repository
public interface StudentInfoDao extends JpaRepository<StudentsInfo,String>{

	
	@Query("SELECT s FROM StudentsInfo s WHERE studentId=?1")
	public StudentsInfo getStudentInfoByID(String id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM StudentsInfo s WHERE studentId=?1")
	public int deleteStudentInfoByID(String studentId);
	
	@Query("SELECT max(studentId) from StudentsInfo s")
	public String getMaxStudentId();
	
}
