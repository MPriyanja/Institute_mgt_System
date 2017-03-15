/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



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
        int x=0;
        try {
            Date date = new Date();
            String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            String course_id = Integer.toString(courseID);
            String tableName = "payments_" + year + "_" + course_id;

            String query = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ("
                    + "  `student_id` int(11) NOT NULL,"
                    + "  `january` varchar(11) NOT NULL,"
                    + "  `february` varchar(11) NOT NULL,"
                    + "  `march` varchar(11) NOT NULL,"
                    + "  `april` varchar(11) NOT NULL,"
                    + "  `may` varchar(11) NOT NULL,"
                    + "  `june` varchar(11) NOT NULL,"
                    + "  `july` varchar(11) NOT NULL,"
                    + "  `august` varchar(11) NOT NULL,"
                    + "  `september` varchar(11) NOT NULL,"
                    + "  `october` varchar(11) NOT NULL,"
                    + "  `november` varchar(11) NOT NULL,"
                    + "  `december` varchar(11) NOT NULL,"
                    + "  KEY `student_id` (`student_id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            String query2 = "ALTER TABLE `" + tableName + "`"
                    + "  ADD CONSTRAINT `student_id_fk" + course_id + "` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE";

            stmt = con.createStatement();
            x = stmt.executeUpdate(query);

            stmt = con.createStatement();
            stmt.executeUpdate(query2);

        } catch (SQLException ex) {

        }
        return x;//if x is 0 , that mean successfully created that table
    }
    
    public void insertCourseData(courseBean bean){
        PreparedStatement stmt;
        ResultSet result;
        
        try{
           String query = "INSERT INTO `course`(`course_description`,subject_code,lecturer_id, "
                   + " `total_course_fee`, `monthly_fee`, `course_duration`, `grade`, `class_type`, `Medium`) VALUES "
                   + " (?,(Select SUBJECT_CODE from subject where SUBJECT_NAME=?),(SELECT ID FROM LECTURER WHERE NAME = ?),?,?,?)";
           
           stmt = con.prepareStatement(query);
           stmt.setString(1, bean.getCourseDescription());
           stmt.setString(2, bean.getSubject());
           stmt.setString(3, bean.getLecturerName());
           stmt.setDouble(4, bean.getTotalCourseFee());
           stmt.setDouble(5, bean.getMonthlyFee());
           stmt.setString(6, bean.getCourseDuration());
           stmt.setString(7, bean.getGrade());
           stmt.setString(8, bean.getCourseType());
           stmt.setString(9, bean.getCourseMedium());
           
           stmt.executeUpdate();
            
        }catch (Exception ex){
            
        }
    }
    
    public void insertClassDays(ArrayList<classDaysBean> beanList){
        PreparedStatement stmt;
        ResultSet result;
        
        String fields = "";// (`monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `sunday`) 
        String values = "";//       VALUES (?,?,?,?,?,?,?,?)
        try{
            for (int i = 0; i < beanList.size(); i++) {
                fields = fields+"`"+beanList.get(i).getDay();
            }
        }catch(Exception ex){
            
        }
    }
    
}
