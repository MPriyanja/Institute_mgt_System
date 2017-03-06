/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.payment_mgt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
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

    public void createPaymentTableForNewCourse(int courseID) {

        Statement stmt;
        ResultSet result;
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
            stmt.executeUpdate(query);

            stmt = con.createStatement();
            stmt.executeUpdate(query2);

        } catch (SQLException ex) {

        }

    }
}
