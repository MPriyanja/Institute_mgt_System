/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

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

    public void insertClassDays(ArrayList<classDaysBean> beanList, String CourseId) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        classDaysBean bean = new classDaysBean();
        SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");
        String values = "";//       VALUES (?,?,?,?,?,?,?,?)
        try {
            bean = beanList.get(0);
            String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
            String query = "Insert into courses_dates (`course_id`," + bean.getDay() + ") values (?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, CourseId);
            stmt.setString(2, StartEndTime);
            stmt.executeUpdate();

            if (beanList.size() > 1) {
                for (int i = 1; i < beanList.size(); i++) {
                    bean = beanList.get(i);
                    StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                    query = "update courses_dates set `course_id` = ? ," + bean.getDay() + " = ? ";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, CourseId);
                    stmt.setString(2, StartEndTime);
                    stmt.executeUpdate();
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

}
