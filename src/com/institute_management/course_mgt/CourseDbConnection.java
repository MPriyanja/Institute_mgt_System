/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import com.institute_management.student.BEAN.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author acer
 */
public class CourseDbConnection {

    Connection con = getConnection();

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/institute_management", "root", "");
        } catch (Exception ex) {

        }
        return conn;
    }

    public int createPaymentTableForNewCourse(int courseID) {

        Statement stmt;
        ResultSet result;
        int x = 0;
        try {
            Date date = new Date();
            String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            String course_id = Integer.toString(courseID);
            String tableName = "payments_" + year + "_" + course_id;

            String query = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ("
                    + "  `student_id` int(11) NOT NULL,"
                    + "  `january` varchar(11) NOT NULL,"
                    + "  `february` varchar(11) NOT NULL,"
                    + "  `march` varchar(11) NOT NULL,"
                    + "  `april` varchar(11) NOT NULL,"
                    + "  `may` varchar(11) NOT NULL,"
                    + "  `june` varchar(11) NOT NULL,"
                    + "  `july` varchar(11) NOT NULL,"
                    + "  `august` varchar(11) NOT NULL,"
                    + "  `september` varchar(11) NOT NULL,"
                    + "  `october` varchar(11) NOT NULL,"
                    + "  `november` varchar(11) NOT NULL,"
                    + "  `december` varchar(11) NOT NULL,"
                    + "  KEY `student_id` (`student_id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            String query2 = "ALTER TABLE `" + tableName + "`"
                    + "  ADD CONSTRAINT `student_id_fk" + course_id + "` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE";

            stmt = con.createStatement();
            x = stmt.executeUpdate(query);

            stmt = con.createStatement();
            stmt.executeUpdate(query2);

        } catch (SQLException ex) {

        }
        return x;//if x is 0 , that mean successfully created that table
    }

    public void insertCourseData(courseBean bean) throws Exception {
        PreparedStatement stmt;
        ResultSet result;

        try {
            String query = "INSERT INTO `course`(`course_id`,`course_description`,subject_id,lecturer_id, "
                    + " `total_course_fee`, `monthly_fee`, `course_duration`, `grade`, `class_type`, `medium`,`batch_number`) VALUES "
                    + " (?,?,(Select SUBJECT_ID from subject where SUBJECT_NAME=?),(SELECT ID FROM LECTURER WHERE NAME = ?),?,?,?,?,?,?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, bean.getCourseID());
            stmt.setString(2, bean.getCourseDescription());
            stmt.setString(3, bean.getSubject());
            stmt.setString(4, bean.getLecturerName());
            stmt.setDouble(5, bean.getTotalCourseFee());
            stmt.setDouble(6, bean.getMonthlyFee());
            stmt.setInt(7, bean.getCourseDuration());
            stmt.setString(8, bean.getGrade());
            stmt.setString(9, bean.getCourseType());
            stmt.setString(10, bean.getCourseMedium());
            stmt.setInt(11, bean.getBatchNumber());
            System.out.println(stmt);
            stmt.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void insertClassDays(HashMap<String, classDaysBean> beanMap, String CourseId) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        classDaysBean bean = new classDaysBean();
        SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");
        String values = "";//       VALUES (?,?,?,?,?,?,?,?)
        int count = 0;
        try {
            Map<String, classDaysBean> map = beanMap;
            for (Map.Entry<String, classDaysBean> entry : map.entrySet()) {
                //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                count++;
                if (count == 1) {
                    bean = entry.getValue();
                    String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                    String query = "Insert into courses_dates (`course_id`," + bean.getDay() + ") values (?,?)";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, CourseId);
                    stmt.setString(2, StartEndTime);
                    stmt.executeUpdate();
                } else if (count > 1) {
                    if (beanMap.size() > 1) {

                        bean = entry.getValue();
                        String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                        String query = "update courses_dates set `course_id` = ? ," + bean.getDay() + " = ? ";
                        stmt = con.prepareStatement(query);
                        stmt.setString(1, CourseId);
                        stmt.setString(2, StartEndTime);
                        stmt.executeUpdate();

                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String GenerateCourseID(String Grade, String subject, String ClassType, String Lecturer, int batchNo) {

        String CourseID = Grade.replace(" ", "") + "/" + subject + "/" + ClassType.charAt(0) + "/" + Lecturer.substring(0, Lecturer.indexOf(" ")) + "/B" + (Integer.toString(batchNo));
        return CourseID;
    }

    public boolean checkDuplicateCourseID(String CourseID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        boolean isDuplicateID = false;

        try {
            String query = "SELECT * FROM COURSE WHERE COURSE_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, CourseID);
            result = stmt.executeQuery();
            if (result.next()) {
                isDuplicateID = true;
            }

            return isDuplicateID;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public boolean checkstudentExistancy(String studentID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        boolean isExist = false;

        try {
            String query = "SELECT * FROM student WHERE student_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                isExist = true;
            }

            return isExist;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public Student getStudentDetails(String studentID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        Student stdn = new Student();

        try {
            String query = "SELECT * FROM student WHERE student_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                stdn.setAddress(result.getString("address"));
                //stdn.setDob(result.getString(""));
                // stdn.setEmail(result.getString(""));
                stdn.setGender(result.getString("gender"));
                stdn.setName(result.getString("student_name"));
                stdn.setSchool(result.getString("school"));
                stdn.setTelephn(result.getString("mobile_number"));
                stdn.setYearOfReg(result.getString("register_date"));
                stdn.setpContactNo(result.getString("parents_mobile_no"));
                stdn.setStudentID(studentID);

            }
            stmt.close();
            result.close();
            return stdn;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public boolean checkstudentinCourse(String studentID, String courseID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        boolean isExist = false;

        try {
            String query = "SELECT * FROM `student-course` WHERE student_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            while (result.next()) {
                String cID = result.getString("course_id");
                String status = result.getString("status");

                if (cID.equals(courseID) && status.equals("ACT")) {
                    isExist = true;
                    return isExist;
                }
            }

            return isExist;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int studentRegistrationForCourse(String studentID, String courseID, int cardType) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "INSERT INTO `student-course`(`student_id`, `course_id`, `registation_date`,`cardType`, `status`) VALUES (?,?,CURDATE(),?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);
            stmt.setInt(3, cardType);
            stmt.setString(4, "ACT");

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int studentDeleteFromCourse(String studentID, String courseID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "DELETE FROM `student-course`  WHERE `student_id`=? and `course_id`=? and `status`='ACT'";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int updateStudentCardType(String studentID, String courseID, int cardType) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "UPDATE `student-course` SET `cardType`=? WHERE `student_id`=? and  `status`=? and `course_id`=? ";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, cardType);
            stmt.setString(2, studentID);
            stmt.setString(3, "ACT");
            stmt.setString(4, courseID);

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public courseBean classDetailsUpdate(String courseID) throws Exception {

        courseBean cb = new courseBean();
        HashMap<String,classDaysBean> map = new HashMap<String,classDaysBean>();
        PreparedStatement stmt;
        ResultSet result;
        int success;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        try {
            String query = "select * from courses_dates where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            result = stmt.executeQuery();
            while (result.next()) {
                if (result.getString("monday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("monday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("monday");
                    map.put("monday",cdays);
                }
                if (result.getString("tuesday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("tuesday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("tuesday");
                    map.put("tuesday",cdays);
                }
                if (result.getString("wednesday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("wednesday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("wednesday");
                    map.put("wednesday",cdays);
                }
                if (result.getString("thursday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("thursday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("thursday");
                    map.put("thursday",cdays);
                }
                if (result.getString("friday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("friday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("friday");
                    map.put("friday",cdays);
                }
                if (result.getString("saturday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("saturday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("saturday");
                    map.put("saturday",cdays);
                }
                if (result.getString("sunday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("sunday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("sunday");
                    map.put("sunday",cdays);
                }
                cb.setClassDaysMap(map);

            }
            PreparedStatement stmt1;
            ResultSet result1;
            String query1 = "Select total_course_fee,monthly_fee from course where course_id=?";
            stmt1 = con.prepareStatement(query1);
            stmt1.setString(1, courseID);
            result1 = stmt1.executeQuery();
            while (result1.next()) {
                cb.setCourseID(courseID);
                cb.setTotalCourseFee(result1.getDouble("total_course_fee"));
                cb.setMonthlyFee(result1.getDouble("monthly_fee"));
            }
            return cb;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
    public int addExtraClass(String courseID, Date clzDate , String StartTime,String EndTime) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "INSERT INTO `extraclasses`(`courseID`, `date`, `startTime`, `endTime`, `status`) VALUES (?,?,?,?,?) ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            stmt.setDate(2, (java.sql.Date)clzDate);
            stmt.setString(3, StartTime);
            stmt.setString(4, EndTime);
            stmt.setString(5, "ACT");

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }
}
