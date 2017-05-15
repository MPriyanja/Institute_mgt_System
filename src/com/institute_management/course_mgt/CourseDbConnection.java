/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import com.institute_management.student.BEAN.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                    + "  `s_id` int(11) NOT NULL,"
                    + "  `january` varchar(11) NOT NULL,"
                    + "  `february` varchar(11) NOT NULL,"
                    + "  `march` varchar(11) NOT NULL,"
                    + "  `may` varchar(11) NOT NULL,"
                    + "  `june` varchar(11) NOT NULL,"
                    + "  `july` varchar(11) NOT NULL,"
                    + "  `august` varchar(11) NOT NULL,"
                    + "  `september` varchar(11) NOT NULL,"
                    + "  `october` varchar(11) NOT NULL,"
                    + "  `november` varchar(11) NOT NULL,"
                    + "  `december` varchar(11) NOT NULL,"
                    + "  KEY `s_id` (`s_id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            String query2 = "ALTER TABLE `" + tableName + "`"
                    + "  ADD CONSTRAINT `s_id_fk" + course_id + "` FOREIGN KEY (`s_id`) REFERENCES `s` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE";

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

    public void insertClassDays(HashMap<String, classDaysBean> beanMap, String CourseId) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        classDaysBean bean = new classDaysBean();
        SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");
        String values = "";//       VALUES (?,?,?,?,?,?,?,?)
        int count = 0;
        try {
            Map<String, classDaysBean> map = beanMap;
            for (Map.Entry<String, classDaysBean> entry : map.entrySet()) {
                //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                count++;
                if (count == 1) {
                    bean = entry.getValue();
                    String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                    String query = "Insert into courses_dates (`course_id`," + bean.getDay() + ") values (?,?)";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, CourseId);
                    stmt.setString(2, StartEndTime);
                    stmt.executeUpdate();
                } else if (count > 1) {
                    if (beanMap.size() > 1) {

                        bean = entry.getValue();
                        String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                        String query = "update courses_dates set " + bean.getDay() + " = ? where `course_id` = ? ";
                        stmt = con.prepareStatement(query);
                        stmt.setString(1, StartEndTime);
                        stmt.setString(2, CourseId);
                        stmt.executeUpdate();

                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String GenerateCourseID(String Grade, String subject, String ClassType, String Lecturer, int batchNo) {

        String lecName = Lecturer.contains(" ") ? Lecturer.substring(0, Lecturer.indexOf(" ")) : Lecturer;
        String CourseID = Grade.replace(" ", "") + "/" + subject + "/" + ClassType.charAt(0) + "/" + lecName + "/B" + (Integer.toString(batchNo));
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

    public boolean checkstudentExistancy(String studentID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        boolean isExist = false;

        try {
            String query = "SELECT * FROM student WHERE s_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                isExist = true;
            }

            return isExist;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public Student getStudentDetails(String studentID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        Student stdn = new Student();

        try {
            String query = "SELECT * FROM student WHERE s_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                stdn.setAddress(result.getString("S_ADDRESS"));
                //stdn.setDob(result.getString(""));
                // stdn.setEmail(result.getString(""));
                stdn.setGender(result.getString("S_GENDER"));
                stdn.setName(result.getString("S_NAME"));
                stdn.setSchool(result.getString("S_SCHOOL"));
                stdn.setTelephn(result.getString("S_TELEPHONE"));
                stdn.setYearOfReg(result.getString("S_YOR"));
                stdn.setpContactNo(result.getString("S_PARENT_CONTACT_NO"));
                stdn.setStudentID(studentID);

            }
            stmt.close();
            result.close();
            return stdn;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public boolean checkstudentinCourse(String studentID, String courseID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        boolean isExist = false;

        try {
            String query = "SELECT * FROM `student-course` WHERE s_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            while (result.next()) {
                String cID = result.getString("course_id");
                String status = result.getString("status");

                if (cID.equals(courseID) && status.equals("ACT")) {
                    isExist = true;
                    return isExist;
                }
            }

            return isExist;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int studentRegistrationForCourse(String studentID, String courseID, int cardType) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "INSERT INTO `student-course`(`s_id`, `course_id`, `registation_date`,`cardType`, `status`) VALUES (?,?,CURDATE(),?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);
            stmt.setInt(3, cardType);
            stmt.setString(4, "ACT");

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int studentDeleteFromCourse(String studentID, String courseID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "DELETE FROM `student-course`  WHERE `s_id`=? and `course_id`=? and `status`='ACT'";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int updateStudentCardType(String studentID, String courseID, int cardType) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = "UPDATE `student-course` SET `cardType`=? WHERE `s_id`=? and  `status`=? and `course_id`=? ";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, cardType);
            stmt.setString(2, studentID);
            stmt.setString(3, "ACT");
            stmt.setString(4, courseID);

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public courseBean classDetailsUpdate(String courseID) throws Exception {

        courseBean cb = new courseBean();
        HashMap<String, classDaysBean> map = new HashMap<String, classDaysBean>();
        PreparedStatement stmt;
        ResultSet result;
        int success;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        try {
            String query = "select * from courses_dates where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            result = stmt.executeQuery();
            while (result.next()) {
                if (result.getString("monday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("monday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("monday");
                    map.put("monday", cdays);
                }
                if (result.getString("tuesday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("tuesday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("tuesday");
                    map.put("tuesday", cdays);
                }
                if (result.getString("wednesday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("wednesday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("wednesday");
                    map.put("wednesday", cdays);
                }
                if (result.getString("thursday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("thursday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("thursday");
                    map.put("thursday", cdays);
                }
                if (result.getString("friday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("friday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("friday");
                    map.put("friday", cdays);
                }
                if (result.getString("saturday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("saturday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("saturday");
                    map.put("saturday", cdays);
                }
                if (result.getString("sunday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("sunday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("sunday");
                    map.put("sunday", cdays);
                }
                cb.setClassDaysMap(map);

            }
            PreparedStatement stmt1;
            ResultSet result1;
            String query1 = "Select total_course_fee,monthly_fee from course where course_id=?";
            stmt1 = con.prepareStatement(query1);
            stmt1.setString(1, courseID);
            result1 = stmt1.executeQuery();
            while (result1.next()) {
                cb.setCourseID(courseID);
                cb.setTotalCourseFee(result1.getDouble("total_course_fee"));
                cb.setMonthlyFee(result1.getDouble("monthly_fee"));
            }
            return cb;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int addExtraClass(String courseID, Date clzDate, String StartTime, String EndTime) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(clzDate);

        try {
            String query = "INSERT INTO `extraclasses`(`courseID`, `date`, `startTime`, `endTime`, `status`) VALUES (?,?,?,?,?) ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            stmt.setString(2, reportDate);
            stmt.setString(3, StartTime);
            stmt.setString(4, EndTime);
            stmt.setString(5, "ACT");

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public HashMap<Integer, Object[]> getExtraClassDetails(String courseID) throws Exception {
        int count = 1;

        PreparedStatement stmt;
        ResultSet result;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            String query = "select * from extraclasses where `courseID`=? ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[5];

                rowData[0] = result.getString("date");
                rowData[1] = result.getString("startTime");
                rowData[2] = result.getString("endTime");
                rowData[3] = result.getString("status");
                rowData[4] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            throw ex;
        }

        return tableData;
    }

    public HashMap<Integer, Object[]> getCourseDetailsForTableView() throws Exception {
        int count = 1;

        PreparedStatement stmt;
        ResultSet result;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            String query = "select cs.course_id,cs.course_description,cs.class_type,cs.medium,(select name from lecturer where id = cs.lecturer_id)as lecturer,(select subject_name from subject where subject_id = cs.subject_id)as subject from course cs ";

            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[6];

                rowData[0] = result.getString("course_id");
                rowData[1] = result.getString("course_description");
                rowData[2] = result.getString("subject");
                rowData[3] = result.getString("lecturer");
                rowData[4] = result.getString("class_type");
                rowData[5] = result.getString("medium");
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            throw ex;
        }

        return tableData;
    }

    public void updateClassDays(HashMap<String, classDaysBean> beanMap, String CourseId) throws Exception {
        PreparedStatement stmt;
        ResultSet result;

        try {
            String query = "Delete from courses_dates where Course_id=? ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, CourseId);
            stmt.executeUpdate();

            insertClassDays(beanMap, CourseId);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public int updateCourseFee(String course_id, double monthlyFee, double totalFee) throws Exception {

        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query = " UPDATE `course` SET `total_course_fee`= ? ,`monthly_fee`= ? WHERE `course_id` = ?";

            stmt = con.prepareStatement(query);
            stmt.setDouble(1, totalFee);
            stmt.setDouble(2, monthlyFee);
            stmt.setString(3, course_id);

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int deleteExtraClasses(String courseId, String date, String startTime) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;
        try {
            String query = "delete from extraclasses where courseid = ? and date = ? and startTime = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            success = stmt.executeUpdate();

            return success;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int[] totalFreeCards(String courseID) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success;
        int array[] = new int[4];
        try {
            String query = "SELECT (select count(cardType) from `student-course` where cardType=0 ) as free,(select count(cardType) from `student-course` where cardType=1 ) as half,(select count(cardType) from `student-course` where cardType=2 ) as normal, (select count(cardType) from `student-course`)as total FROM `student-course` where course_id = ? ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);

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

    public courseBean getCourseDetails(String CourseID) throws Exception {

        PreparedStatement stmt = null;
        ResultSet result = null;
        courseBean cb = new courseBean();

        try {

            String query = "select co.*,su.subject_name,le.name from course co inner join subject su on co.subject_id = su.subject_id inner join lecturer le on co.lecturer_id = le.id where co.course_id ='" + CourseID + "'";

            stmt = new CourseDbConnection().getConnection().prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                cb.setLecturerName(result.getString("name"));
                cb.setSubject(result.getString("subject_name"));
                cb.setGrade(result.getString("grade"));
                cb.setCourseType(result.getString("class_type"));
                cb.setCourseDescription(result.getString("course_description"));
                cb.setCourseMedium(result.getString("medium"));
                cb.setBatchNumber(result.getInt("batch_number"));
                cb.setMonthlyFee(result.getInt("monthly_fee"));
                cb.setTotalCourseFee(result.getInt("total_course_fee"));
                cb.setCourseID(CourseID);
            }
            return cb;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String[] getStudentData(String StudentID, String CourseID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        String array[] = new String[2];

        try {

            String query = "SELECT cardType,(select s_name from student where s_id = ?)as name FROM `student-course` WHERE s_ID = ? and course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, StudentID);
            stmt.setString(2, StudentID);
            stmt.setString(3, CourseID);
            result = stmt.executeQuery();

            while (result.next()) {
                if (result.getString("cardType").equals("0")) {
                    array[1] = "Free Card";
                } else if (result.getString("cardType").equals("1")) {
                    array[1] = "Half Free Card";
                } else if (result.getString("cardType").equals("2")) {
                    array[1] = "Normal Card";
                }
                array[0] = result.getString("name");
            }
            return array;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public HashMap SearchStudent(String id, String name, String nic, String mob) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        HashMap map = new HashMap();
        id = id.equals("--") ? "" : id;
        name = name.equals("--") ? "" : name;
        nic = nic.equals("--") ? "" : nic;
        mob = mob.equals("--") ? "" : mob;
        try {
            String query = "SELECT * from STUDENT where S_ID like '%" + id + "%' AND S_NAME like '%" + name + "%' AND S_NIC like '%" + nic + "%' AND S_TELEPHONE like '%" + mob + "%'";

            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                map.put("name", (result.getString("S_NAME").equals("")) ? "--" : result.getString("S_NAME"));
                map.put("address", (result.getString("S_ADDRESS").equals("")) ? "--" : result.getString("S_ADDRESS"));
                map.put("email", (result.getString("S_EMAIL").equals("")) ? "--" : result.getString("S_EMAIL"));
                map.put("gender", (result.getString("S_GENDER").equals("")) ? "--" : result.getString("S_GENDER"));
                map.put("mobile", (result.getString("S_TELEPHONE").equals("")) ? "--" : result.getString("S_TELEPHONE"));
                map.put("school", (result.getString("S_SCHOOL").equals("")) ? "--" : result.getString("S_SCHOOL"));
                map.put("nic", (result.getString("S_NIC").equals("")) ? "--" : result.getString("S_NIC"));
                map.put("regID", (result.getString("S_ID").equals("")) ? "--" : result.getString("S_ID"));
                map.put("pContact", (result.getString("S_PARENT_CONTACT_NO").equals("")) ? "--" : result.getString("S_PARENT_CONTACT_NO"));
                map.put("regDate", (result.getString("S_YOR").equals("")) ? "--" : result.getString("S_YOR"));

            }

        } catch (Exception ex) {
            throw ex;
        }
        return map;
    }
    
    public ArrayList<String> getAllCourse(String StudentID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<String> list= new ArrayList<String>();

        try {

            String query = "SELECT course_id from `student-course` where s_id = ? ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, StudentID);
            
            result = stmt.executeQuery();

            while (result.next()) {
                list.add(result.getString("course_id"));
            }
            return list;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
