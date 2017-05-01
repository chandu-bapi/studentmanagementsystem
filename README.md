 "Student Management System"
 
 #Backend:
 This application has been developed by using Java - Spring boot framework
 

#Database:
1. To create Database schema
 
   CREATE DATABASE `netgear_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
   
2. To create Table 
 
   SELECT * FROM netgear_db.students_inf;CREATE TABLE `students_inf` (
  `STU_ID` varchar(15) NOT NULL,
  `STU_FRST_NM` varchar(45) DEFAULT NULL,
  `STU_LST_NM` varchar(45) NOT NULL,
  `STU_GENDER` varchar(45) DEFAULT NULL,
  `STU_DOB` date DEFAULT NULL,
  `COURSE_NAME` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   "# studentmanagementsystem" 
