/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author Malinda Ranabahu
 */
public class DashBoardMethods {

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

    public HashMap<String, String> getTodayClassList(String today) throws Exception {
        HashMap<String, String> classList = new HashMap<String, String>();
        PreparedStatement stmt;
        ResultSet result;
        try {
            today = today.toLowerCase();
            String query = "SELECT course_id," + today + " FROM `courses_dates` WHERE " + today + " <> 'NULL'";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                classList.put(result.getString("course_id"), result.getString(today));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return classList;
    }

    public int getTotalCourses() throws Exception {
        int totalCourses = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT count(course_id) as count FROM `course`";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totalCourses = result.getInt("count");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return totalCourses;
    }

    public int getTotalLecturers() throws Exception {
        int totallecturers = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT count(id) as count FROM `lecturer`";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totallecturers = result.getInt("count");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return totallecturers;
    }

    public int getTotalStudents() throws Exception {
        int totalStudent = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT count(s_id) as count FROM `student`";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totalStudent = result.getInt("count");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return totalStudent;
    }

    public double getTotalIncome() throws Exception {
        int totalStudent = 0;
        PreparedStatement stmt;
        ResultSet result;
        PreparedStatement stmt1;
        ResultSet result1;
        int paymentcount = 0;
        double amount = 0;
        String tName;
        Calendar c = Calendar.getInstance();

        String thisMonth = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toLowerCase();
        String curYear = (new SimpleDateFormat("yyyy").format(c.getTime())).toLowerCase();
        try {

            String query = "select amount from payments where month = " + thisMonth +" and year = "+curYear;

            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                amount = amount+result.getInt("amount");

            }
        } catch (Exception ex) {
            throw ex;
        }
        return amount;
    }

    public double getCourseFee(String CourseID) throws Exception {
        double totallecturers = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT monthly_fee FROM `course` where course_id = '" + CourseID + "'";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totallecturers = result.getInt("monthly_fee");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return totallecturers;
    }
    
//    public void selectTodayClasses(){
//        double totallecturers = 0;
//        PreparedStatement stmt;
//        ResultSet result;
//        try {
//
//            String query = "SELECT course_id FROM `course` where course_id = '" + CourseID + "'";
//            stmt = con.prepareStatement(query);
//            result = stmt.executeQuery();
//            while (result.next()) {
//                totallecturers = result.getInt("monthly_fee");
//            }
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }

}
