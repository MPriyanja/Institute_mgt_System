/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.subject_mgt.DB;

import com.institute_management.subject_mgt.BEAN.SubjectBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mihiran_p
 * @date Feb 22, 2017
 */
public class DbConnection {

    String query;
    PreparedStatement pst;
    Connection connection = getConnection();
    ResultSet rs;

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/institute_management", "root", "");
        } catch (Exception ex) {

        }
        return conn;
    }

    public int insertIntoSubject(SubjectBean sb) throws Exception {
        int count = 0;
        PreparedStatement pst=null;

        try {
            query = "insert into subject(SUBJECT_NAME,SUBJECT_MEDIUM) values (?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, sb.getSubjectName());
            pst.setString(2, sb.getSubjectMedium());
            count = pst.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
            } catch (Exception ee) {
                throw ee;
            }
        }
        return count;
    }
}
