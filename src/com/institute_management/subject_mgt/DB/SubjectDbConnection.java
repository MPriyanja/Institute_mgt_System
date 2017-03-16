/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.subject_mgt.DB;

import com.institute_management.student.BEAN.Student;
import com.institute_management.subject_mgt.BEAN.SubjectBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mihiran_p
 * @date Feb 22, 2017
 */
public class SubjectDbConnection {

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

    /**
     *
     * @param sb
     * @return
     * @throws Exception
     */
    public int insertIntoSubject(SubjectBean sb) throws Exception {
        int count = 0;

        try {
            query = "insert into subject(SUBJECT_NAME,SUBJECT_MEDIUM,SUBJECT_CODE) values (?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, sb.getSubjectName());
            pst.setString(2, sb.getSubjectMedium());
            pst.setString(3, sb.getSubjectCode());
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

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<Object[]> selectAllSubject() throws Exception {
        ArrayList<Object[]> subjectList = new ArrayList<Object[]>();
        Object[] tableData = null;
        try {
            query = "SELECT SUBJECT_NAME,SUBJECT_CODE,SUBJECT_MEDIUM FROM subject";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                tableData = new Object[4];
                tableData[0] = false;
                tableData[1] = rs.getString("SUBJECT_NAME");
                tableData[2] = rs.getString("SUBJECT_CODE");
                tableData[3] = rs.getString("SUBJECT_MEDIUM");

                subjectList.add(tableData);
            }

        } catch (Exception e) {
        } finally {
            try {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
            } catch (Exception ee) {
                throw ee;
            }
        }
        return subjectList;
    }

    public int insertStudent(Student st) throws Exception {
        int count = 0;

        try {
            query = "INSERT INTO student(S_NAME, S_DOB, S_ADDRESS, S_EMAIL, S_GENDER, S_YOR, S_TELEPHONE, S_SCHOOL) VALUES (?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDob());
            pst.setString(3, st.getAddress());
            pst.setString(4, st.getEmail());
            pst.setString(5, st.getGender());
            pst.setString(6, st.getYearOfReg());
            pst.setString(7, st.getTelephn());
            pst.setString(8, st.getSchool());
            //pst.setString(6, st.getYearOfReg());
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

    public ArrayList<Object[]> selectAllStudent() throws Exception{
        ArrayList<Object[]> studentList = new ArrayList<Object[]>();
        Object[] tableData = null;
        try {
            query = "SELECT  S_NAME,S_DOB,S_ADDRESS,S_EMAIL,S_GENDER,S_YOR,S_TELEPHONE,S_SCHOOL FROM student";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                tableData = new Object[8];
                //tableData[0] = false;
                tableData[0] = rs.getString("S_NAME");
                tableData[1] = rs.getString("S_DOB");
                tableData[2] = rs.getString("S_ADDRESS");
                tableData[3] = rs.getString("S_EMAIL");
                tableData[4] = rs.getString("S_GENDER");
                tableData[5] = rs.getString("S_YOR");
                tableData[6] = rs.getString("S_TELEPHONE");
                tableData[7] = rs.getString("S_SCHOOL");

                studentList.add(tableData);
            }

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
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
            } catch (Exception ee) {
                throw ee;
            }
        }
        return studentList;
    }
}
