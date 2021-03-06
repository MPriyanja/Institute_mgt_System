/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.subject_mgt.DB;

import com.institute_management.attendence.AttendBean;
import com.institute_management.student.BEAN.Student;
import com.institute_management.subject_mgt.BEAN.SubjectBean;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.imageio.ImageIO;

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

    
    
     public int deleteSubjectClasses(String code) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;
        try {
            String query = "delete from subject where SUBJECT_CODE = ? ";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, code);
            

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }
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
            query = "SELECT  S_NAME,S_ID,S_DOB,S_ADDRESS,S_EMAIL,S_GENDER,S_YOR,S_TELEPHONE,S_SCHOOL FROM student";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                //tableData[0] = false;
                st.setName(rs.getString("S_NAME"));
                st.setStudentID(rs.getString("S_ID"));
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
                    + "WHERE S_ID=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDob());
            pst.setString(3, st.getAddress());
            pst.setString(4, st.getEmail());
            pst.setString(5, st.getGender());
            pst.setString(6, st.getYearOfReg());
            pst.setString(7, st.getTelephn());
            pst.setString(8, st.getSchool());
            pst.setString(9, st.getStudentID());
           // pst.setString(10, st.getTelephn());
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

    public BufferedImage getImageById(String id) {
        String query = "select S_IMAGE from student where S_ID = ?";
        BufferedImage img = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();
            byte[] bytes = null;
            if (result.next()) {
                bytes = result.getBytes(1);
            }
            if (bytes != null) {
                img = ImageIO.read(new ByteArrayInputStream(bytes));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return img;
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
            String query = "select `course_id` FROM `student-course` WHERE `S_ID`=?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, studentID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[1];

                rowData[0] = result.getString("course_id");
                //   rowData[1] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            throw ex;
        }

        return tableData;

    }

    public int addStudentToACourse(Object selectedItem, String studentID, int cardType) throws Exception {
        int count = 0;

        try {
            query = "INSERT INTO student-course(course_id, student_id, card_type) VALUES (?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, selectedItem.toString());
            pst.setString(2, studentID);
            pst.setInt(3, cardType);

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

    public HashMap<Integer, Object[]> getCourseDetailsOnStudent(String sid, String date) throws Exception {
        int count = 1;

        PreparedStatement stmt;
        ResultSet result;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            String query = "select cd.course_id as course_id,cd." + date + " as date  FROM `student-course` sc left join `courses_dates` cd on cd.course_id=sc.course_id  WHERE sc.S_ID = ? AND cd." + date + "<> ''";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, sid);

            System.out.println(query);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[2];

                rowData[0] = result.getString("course_id");
                rowData[1] = result.getString("date");
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            throw ex;
        }

        return tableData;

    }

    public ArrayList<String[]> getCourseDetailsOnDay(String day) throws Exception {
        ArrayList<String[]> subList = new ArrayList<String[]>();
        PreparedStatement stmt;
        ResultSet result;
        try {
            String query = "select cd.course_id as course_id,cd." + day + " as time  FROM  `courses_dates` cd   WHERE  cd." + day + "<> ''";

            stmt = connection.prepareStatement(query);
            //stmt.setString(1, sid);

            System.out.println(query);

            result = stmt.executeQuery();
            while (result.next()) {
                String[] rowData = new String[2];

                rowData[0] = result.getString("course_id");
                rowData[1] = result.getString("time");
//                tableData.put(count, rowData);
//                count++;

                subList.add(rowData);
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            throw ex;
        }

        return subList;

    }

    public ArrayList<String> getCourseListOnStudent(String studentID) throws Exception {
        ArrayList<String> courseList = new ArrayList<String>();
        try {
            query = "SELECT `course_id` FROM `student-course` WHERE `S_ID`=? AND `status` IN ('ACT');";

            pst = connection.prepareStatement(query);
            pst.setString(1, studentID);
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

    public int markAttendence(String selectedCourseId, String sid, Date date, int i) throws Exception {
        int count = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY-HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE");
        String dateattendence = dateFormat.format(new Date());
        String dateattendence1 = dateFormat1.format(new Date()).toLowerCase();
        System.out.println(dateattendence1);

        String[] s = dateattendence.split("\\-");
        System.out.println("helo");

        try {
            query = "INSERT INTO `attendence`(`s_id`, `c_id`, `year`, `month`, `date`, `day`, `time`, `attendence`) VALUES (?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, sid);
            pst.setString(2, selectedCourseId);
            pst.setString(3, s[2]);
            pst.setString(4, s[1]);
            pst.setString(5, s[0]);
            pst.setString(6, dateattendence1);
            pst.setString(7, s[3]);
            pst.setInt(8, i);

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

    public ArrayList<AttendBean> getattendencHistory(String sid, String selectedCourseId) throws Exception{
  
     ArrayList<AttendBean> courseList = new ArrayList<AttendBean>();
        try {
            query = "SELECT `time`,`date`,`day`,`attendence`from attendence WHERE `s_id` in(?) AND `c_id` in(?) ORDER BY `id`";

            pst = connection.prepareStatement(query);
            pst.setString(1, sid);
             pst.setString(2, selectedCourseId);
            rs = pst.executeQuery();
          

            while (rs.next()) {

               AttendBean ab=new AttendBean();
               ab.setAttend(rs.getInt("attendence"));
               ab.setDay(rs.getString("day"));
               ab.setTime(rs.getString("time"));
               ab.setDate(rs.getInt("date"));
               
               courseList.add(ab);
            }

        } catch (Exception e) {
            throw e;
        }
        return courseList;
    
    
    
    
    }

    public void insertSchool(String school) throws Exception{
    int count = 0;

        try {
            query = "SELECT  `school` FROM `school` WHERE `school` in (?);";
            pst = connection.prepareStatement(query);
            pst.setString(1, school);
//            pst.setString(2, studentID);
//            pst.setInt(3, cardType);

            rs = pst.executeQuery();
            
            while (rs.next()) {                
                return;
            }
            
            query = "INSERT INTO `school`(`school`) VALUES (?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, school);
            count=pst.executeUpdate();

        } catch (SQLException e) {
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
       // return count;
 }
}
