/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.lecture_mgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tharindu_m
 */
public class DbConnection {

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
        try {
            String query = "INSERT INTO `lecturer`(`nic`, `name`, `gender`, `subject_code`, `email`, `contact`, `address`) VALUES (?,?,?,(select subject_code from subject where subject_name = ? ),?,?,?) ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecbean.getNic());
            stmt.setString(2, lecbean.getName());
            stmt.setString(3, lecbean.getGender());
            stmt.setString(4, lecbean.getSubjectName());
            stmt.setString(5, lecbean.getEmail());
            stmt.setString(6, lecbean.getContact());
            stmt.setString(7, lecbean.getAddress());

            int result = stmt.executeUpdate();
            if (result == 1) {
                status = true;
            }

        } catch (Exception ex) {

        }

        stmt.close();

        return status;

    }
}
