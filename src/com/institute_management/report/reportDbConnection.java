/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Malinda Ranabahu
 */
public class reportDbConnection {
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
    
    public HashMap getStudentDetails(String studentID) throws Exception{
        PreparedStatement stmt;
        ResultSet result;
        HashMap map = new HashMap();

        try {
            String query = "SELECT su.*,(select count(course_id) from `student-course` where student_id =? and cardType = 0 ) as freeCards,(select count(course_id) from `student-course` where student_id =? and cardType = 1 ) as HalfFree,(select count(course_id) from `student-course` where student_id =? and cardType = 2 )as normalCards,(select count(course_id) from `student-course` where student_id =? ) as TotalCourses FROM student su,`student-course` stuc WHERE su.student_id = ? and su.student_id = stuc.student_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, studentID);
            stmt.setString(3, studentID);
            stmt.setString(4, studentID);
            stmt.setString(5, studentID);
            result = stmt.executeQuery();
            while(result.next()){
                map.put("student_name", result.getString("student_name"));
                map.put("nic", result.getString("nic"));
                map.put("address", result.getString("address"));
                map.put("personal_contact", result.getString("mobile_number"));
                map.put("regDate", result.getString("register_date"));
                map.put("school", result.getString("school"));
                map.put("parents_mobile", result.getString("parents_mobile_no"));
                map.put("gender", result.getString("gender"));
                map.put("freeCards", result.getString("freeCards"));
                map.put("HalfFree", result.getString("HalfFree"));
                map.put("NormalCards", result.getString("normalCards"));
                map.put("totalCourses", result.getString("TotalCourses"));
  
            }

            return map;
        } catch (Exception ex) {
            throw ex;
        }

        
    }
}
