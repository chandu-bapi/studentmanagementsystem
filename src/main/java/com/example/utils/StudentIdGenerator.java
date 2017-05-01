package com.example.utils;

public class StudentIdGenerator {
	public static String getCustomStudentID(String maxStudentID) {

		int studentSerialNum = 0;
		if (maxStudentID!=null) {
			maxStudentID=maxStudentID.trim();
			studentSerialNum = Integer.parseInt(maxStudentID.substring(2,5));
		}
		String CustomStudentID = "S" + String.format("%04d", studentSerialNum + 1);
		return CustomStudentID;
	}
}
