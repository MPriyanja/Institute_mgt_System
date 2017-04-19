/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.lecture_mgt;

import com.institute_management.course_mgt.*;
import com.institute_management.course_mgt.courseBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tharindu_m
 */
public class LectureDbConnection {

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

    public boolean addLecture(lectureBean lecbean) throws SQLException {

        PreparedStatement stmt = null;
        boolean status = false;
        Date date = new Date();
        try {
            String query = "INSERT INTO `lecturer`(`nic`, `name`, `gender`, `subject_code`, `email`, `contact`, `address`,`RegDate`) VALUES (?,?,?,(select subject_code from subject where subject_name = ? ),?,?,?,?) ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecbean.getNic());
            stmt.setString(2, lecbean.getName());
            stmt.setString(3, lecbean.getGender());
            stmt.setString(4, lecbean.getSubjectName());
            stmt.setString(5, lecbean.getEmail());
            stmt.setString(6, lecbean.getContact());
            stmt.setString(7, lecbean.getAddress());
            stmt.setString(8, new SimpleDateFormat("yyyy-MM-dd").format(date));

            int result = stmt.executeUpdate();
            if (result == 1) {
                status = true;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        stmt.close();

        return status;

    }

    public lectureBean getLecturerDetails(String lecID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result;
        lectureBean lb = new lectureBean();
        boolean status = false;
        try {
            String query = "SELECT le.*,su.subject_name FROM lecturer le, subject su WHERE le.id= ? and le.subject_code=su.subject_code";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecID);

            result = stmt.executeQuery();
            while (result.next()) {
                lb.setAddress(result.getString("address"));
                lb.setContact(result.getString("contact"));
                lb.setEmail(result.getString("email"));
                lb.setGender(result.getString("gender"));
                lb.setName(result.getString("name"));
                lb.setNic(result.getString("nic"));
                lb.setSubjectName(result.getString("subject_name"));
                lb.setRegDate(result.getString("RegDate"));
                lb.setLecID(lecID);

            }
            stmt.close();
            return lb;

        } catch (Exception ex) {
            throw ex;
        }

    }

    public int[] totalFreeCards(String LecturerID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;
        int array[] = new int[4];
        try {
            String query = "SELECT DISTINCT (select count(cardType) from `student-course` where cardType=0 ) as free,(select count(cardType) from `student-course` where cardType=1 ) as half,(select count(cardType) from `student-course` where cardType=2 ) as normal, (select count(cardType) from `student-course`)as total FROM `student-course`,`Lecturer`,`course` where Lecturer.id = ? and course.lecturer_id =  Lecturer.id and course.course_id = `student-course`.course_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, LecturerID);

            result = stmt.executeQuery();
            while (result.next()) {
                array[0] = result.getInt("free");
                array[1] = result.getInt("half");
                array[2] = result.getInt("normal");
                array[3] = result.getInt("total");

            }
            return array;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int courseCount(String lecID) throws Exception {

        PreparedStatement stmt = null;
        ResultSet result;
        int count = 0;
        try {
            String query = "SELECT count(*)as count FROM `course` WHERE lecturer_id = ?;";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecID);

            result = stmt.executeQuery();
            while (result.next()) {
                count = result.getInt("count");

            }
            stmt.close();
            return count;

        } catch (Exception ex) {
            throw ex;
        }

    }

    public boolean updateLecture(lectureBean lecbean) throws SQLException {

        PreparedStatement stmt = null;
        boolean status = false;
        try {
            String query = "update `lecturer` set `nic`=?,`name`=?, `gender`=? ,`subject_code`=(select subject_code from subject where subject_name = ?), `email`=?, `contact`=? ,`address`=? where id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecbean.getNic());
            stmt.setString(2, lecbean.getName());
            stmt.setString(3, lecbean.getGender());
            stmt.setString(4, lecbean.getSubjectName());
            stmt.setString(5, lecbean.getEmail());
            stmt.setString(6, lecbean.getContact());
            stmt.setString(7, lecbean.getAddress());
            stmt.setString(8, lecbean.getLecID());

            int result = stmt.executeUpdate();
            if (result == 1) {
                status = true;
            }

        } catch (Exception ex) {
            throw ex;
        }

        stmt.close();

        return status;

    }

    public double calculateIncomePrecentage(courseBean cb) {
        try {
            int array[] = totalFreeCards(cb.getCourseID());
            if (cb.getMonthlyFee() > 0.00) {
                double totalNormalCardIncome = array[2] * cb.getMonthlyFee();
                double totalHalfCardIncome = array[1] * cb.getMonthlyFee();
                double precentage = (totalHalfCardIncome+totalNormalCardIncome);
            }
            else{
                double totalNormalCardIncome = array[2] * cb.getTotalCourseFee();
                double totalHalfCardIncome = array[1] * cb.getTotalCourseFee();
            }
        } catch (Exception ex) {
            Logger.getLogger(LectureDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.00;
    }

}
