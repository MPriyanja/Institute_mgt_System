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
            String query = "SELECT su.*,(select count(course_id) from `student-course` where s_id =? and cardType = 0 ) as freeCards,(select count(course_id) from `student-course` where s_id =? and cardType = 1 ) as HalfFree,(select count(course_id) from `student-course` where s_id =? and cardType = 2 )as normalCards,(select count(course_id) from `student-course` where s_id =? ) as TotalCourses FROM student su,`student-course` stuc WHERE su.s_id = ? and su.s_id = stuc.s_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, studentID);
            stmt.setString(3, studentID);
            stmt.setString(4, studentID);
            stmt.setString(5, studentID);
            result = stmt.executeQuery();
            while(result.next()){
                map.put("student_name", result.getString("S_NAME"));
                map.put("nic", result.getString("S_NIC"));
                map.put("address", result.getString("S_ADDRESS"));
                map.put("personal_contact", result.getString("S_TELEPHONE"));
                map.put("regDate", result.getString("S_YOR"));
                map.put("school", result.getString("S_SCHOOL"));
                map.put("parents_mobile", result.getString("S_PARENT_CONTACT_NO"));
                map.put("gender", result.getString("S_GENDER"));
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
