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
import java.util.HashMap;

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
                tableData = new Object[3];
                // tableData[0] = false;
                tableData[0] = rs.getString("SUBJECT_NAME");
                tableData[1] = rs.getString("SUBJECT_CODE");
                tableData[2] = rs.getString("SUBJECT_MEDIUM");

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
            query = "INSERT INTO student(S_NAME, S_DOB, S_ADDRESS, S_EMAIL, S_GENDER, S_YOR, S_TELEPHONE, S_SCHOOL,S_PARENT_CONTACT_NO) VALUES (?,?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDob());
            pst.setString(3, st.getAddress());
            pst.setString(4, st.getEmail());
            pst.setString(5, st.getGender());
            pst.setString(6, st.getYearOfReg());
            pst.setString(7, st.getTelephn());
            pst.setString(8, st.getSchool());
            pst.setString(9, st.getpContactNo());
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

    public ArrayList<Student> selectAllStudent() throws Exception {
        ArrayList<Student> studentList = new ArrayList<Student>();
        Object[] tableData = null;
        try {
            query = "SELECT  S_NAME,S_DOB,S_ADDRESS,S_EMAIL,S_GENDER,S_YOR,S_TELEPHONE,S_SCHOOL FROM student";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                //tableData[0] = false;
                st.setName(rs.getString("S_NAME"));
                st.setDob(rs.getString("S_DOB"));
//                tableData[2] = rs.getString("S_ADDRESS");
//                tableData[3] = rs.getString("S_EMAIL");
//                tableData[4] = rs.getString("S_GENDER");
//                tableData[5] = rs.getString("S_YOR");
//                tableData[6] = rs.getString("S_TELEPHONE");
//                tableData[7] = rs.getString("S_SCHOOL");

                st.setAddress(rs.getString("S_ADDRESS"));
                st.setEmail(rs.getString("S_EMAIL"));
                st.setGender(rs.getString("S_GENDER"));
                st.setSchool(rs.getString("S_SCHOOL"));
                st.setYearOfReg(rs.getString("S_YOR"));
                st.setTelephn(rs.getString("S_TELEPHONE"));

                studentList.add(st);
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

    public int updateStudent(Student st) throws Exception {
        int count = 0;

        try {
            query = "UPDATE student SET S_NAME=?,S_DOB=?,"
                    + "S_ADDRESS=?,S_EMAIL=?,S_GENDER=?,"
                    + "S_YOR=?,S_TELEPHONE=?,S_SCHOOL=?"
                    + "WHERE S_NAME=? AND S_TELEPHONE=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDob());
            pst.setString(3, st.getAddress());
            pst.setString(4, st.getEmail());
            pst.setString(5, st.getGender());
            pst.setString(6, st.getYearOfReg());
            pst.setString(7, st.getTelephn());
            pst.setString(8, st.getSchool());
            pst.setString(9, st.getName());
            pst.setString(10, st.getTelephn());
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

    public int deleteStudent(Student st) throws Exception {
        int count = 0;

        try {
            query = "DELETE FROM `student` WHERE S_NAME=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());

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

    public Student selectStudent(String regId) throws Exception {
        Student student = null;
        try {
            query = "select S_PARENT_EMAIL,S_PARENT_NAME,S_NAME,S_DOB,S_ADDRESS,S_EMAIL,S_GENDER,S_YOR,S_TELEPHONE,S_SCHOOL,S_ID,S_PARENT_CONTACT_NO from student where S_ID=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, regId);

            rs = pst.executeQuery();

            while (rs.next()) {
                student = new Student();

                student.setDob(rs.getString("S_DOB"));
                student.setEmail(rs.getString("S_EMAIL"));
                student.setGender(rs.getString("S_GENDER"));
                student.setName(rs.getString("S_NAME"));
                student.setSchool(rs.getString("S_SCHOOL"));
                student.setStudentID(rs.getString("S_ID"));
                student.setTelephn(rs.getString("S_TELEPHONE"));
                student.setYearOfReg(rs.getString("S_YOR"));
                student.setpContactNo(rs.getString("S_PARENT_CONTACT_NO"));
                student.setAddress(rs.getString("S_ADDRESS"));
                student.setParentEmail(rs.getString("S_PARENT_EMAIL"));
                student.setParentName(rs.getString("S_PARENT_NAME"));
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
            } catch (Exception ee) {
                throw ee;
            }
        }
        return student;

    }

    public ArrayList<String> getStudentList() throws Exception {
        ArrayList<String> studentList = new ArrayList<String>();
        try {
            query = "SELECT S_ID FROM student";

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            studentList.add("--");

            while (rs.next()) {

                studentList.add(rs.getString("S_ID"));
            }

        } catch (Exception e) {
            throw e;
        }
        return studentList;
    }

    public String getStudentNameOnId(String studentId) throws Exception {
        String studentName = null;
        try {
            query = "SELECT S_NAME FROM student where S_ID=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, studentId);
            rs = pst.executeQuery();

            while (rs.next()) {
                studentName = rs.getString("S_NAME");
            }

        } catch (Exception e) {
            throw e;
        }
        return studentName;

    }

    public ArrayList<String> getCourseList() throws Exception {
        ArrayList<String> courseList = new ArrayList<String>();
        try {
            query = "SELECT course_id FROM course";

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            courseList.add("--");

            while (rs.next()) {

                courseList.add(rs.getString("course_id"));
            }

        } catch (Exception e) {
            throw e;
        }
        return courseList;
    }

    public String getCourseNameOnId(String corseID) throws Exception {
        String courseName = null;
        try {
            query = "SELECT course_description FROM course where course_id=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, corseID);
            rs = pst.executeQuery();

            while (rs.next()) {
                courseName = rs.getString("course_description");
            }

        } catch (Exception e) {
            throw e;
        }
        return courseName;
    }

    public HashMap<Integer, Object[]> getCourseDetailsOnStudent(String studentID) throws Exception {
        int count = 1;

        PreparedStatement stmt;
        ResultSet result;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            String query = "SELECT `course_id` FROM `student-course` WHERE `student_id`=?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, studentID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[2];

                rowData[0] = result.getString("course_id");
                rowData[1] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            throw ex;
        }

        return tableData;

    }
}
