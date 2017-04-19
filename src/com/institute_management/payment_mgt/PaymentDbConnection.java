/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.payment_mgt;

import com.institute_management.course_mgt.CourseDbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class PaymentDbConnection {

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

    public void createPaymentTableForNewCourse(String courseID) {

        Statement stmt;
        ResultSet result;
        try {
            Date date = new Date();
            String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            String tableName = "payments_" + year + "_" + courseID;

            String query = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ("
                    + "  `student_id` int(11) NOT NULL,"
                    + "  `January` varchar(11) NOT NULL,"
                    + "  `February` varchar(11) NOT NULL,"
                    + "  `March` varchar(11) NOT NULL,"
                    + "  `April` varchar(11) NOT NULL,"
                    + "  `May` varchar(11) NOT NULL,"
                    + "  `June` varchar(11) NOT NULL,"
                    + "  `July` varchar(11) NOT NULL,"
                    + "  `August` varchar(11) NOT NULL,"
                    + "  `September` varchar(11) NOT NULL,"
                    + "  `October` varchar(11) NOT NULL,"
                    + "  `November` varchar(11) NOT NULL,"
                    + "  `December` varchar(11) NOT NULL,"
                    + "  PRIMARY KEY (`student_id`) "
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            String query2 = "ALTER TABLE `" + tableName + "`"
                    + "  ADD CONSTRAINT `student_id_fk" + courseID + "` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE";

            stmt = con.createStatement();
            int x = stmt.executeUpdate(query);
            if (x == 1) {
                stmt = con.createStatement();
                stmt.executeUpdate(query2);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public HashMap getLastTwoPaymentStatus(String stuID, String courseID) throws SQLException {
        PreparedStatement stmt;
        ResultSet result;
        HashMap<String, String> paymentDetails = new HashMap<String, String>();
        try {
            Date date = new Date();
            String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            String tableName = "payments_" + year + "_" + courseID;

            String query = "select * from `" + tableName + "` where student_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, stuID);
            String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            result = stmt.executeQuery();
            while (result.next()) {
                String currentMonth;
                String payment;
                for (int i = 11; i >= 0; i--) {
                    currentMonth = months[i];
                    payment = result.getString(currentMonth);
                    if (payment.equals("YES")) {
                        paymentDetails.put(currentMonth, "YES");
                        if (i > 0) {
                            paymentDetails.put(months[i - 1], result.getString(months[i - 1]));
                        } else {
                            paymentDetails.put("--", "--");
                        }
                        break;
                    }
                }
            }

            return paymentDetails;

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int makeBatchPayment(String courseID, String studentID, ArrayList<String> a) throws Exception {
        Date date = new Date();
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String tableName = "payments_" + year + "_" + courseID;
        PreparedStatement stmt;
        ResultSet result;
        int success;

        try {
            String query =  "select * from `" + tableName + "` where student_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if(result.next()){
                String updateQuery = "";
                for(int i=0;i<a.size();i++){
                    updateQuery= updateQuery+a.get(i)+" = 'YES' ,";
                }
                //remove last ,
                updateQuery = updateQuery.substring(0,updateQuery.length()-1);
                query = "update `"+tableName+"` set "+updateQuery+" where student_id = ?";
                stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            success = stmt.executeUpdate();
            }
            else{
                String field="student_id ,";
                String value=studentID+" ,";
                for(int i=0;i<a.size();i++){
                    field = field+a.get(i)+" ,";
                    value = value +"'YES' ,";
                }
                field = field.substring(0,field.length()-1);
                value = value.substring(0,value.length()-1);
                
                query = "Insert into `"+tableName+"` ( "+field+" ) values ( "+value+" )";
                stmt = con.prepareStatement(query);
                success = stmt.executeUpdate();
            }
            return success;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
