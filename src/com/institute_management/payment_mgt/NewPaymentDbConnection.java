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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author tharindu_m
 */
public class NewPaymentDbConnection {

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

    public HashMap<String, Integer> makePayment(paymentBean pb) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        HashMap<String, Integer> success = new HashMap<String, Integer>();
        String s_id = pb.getS_id();
        String courseId = pb.getCourseID();
        String date = pb.getDate();
        ArrayList<String> months = pb.getMonth(); 
        String year = pb.getYear();
        double amount=pb.getAmount();
                
        int x;
        try {
            for (int i = 0; i < months.size(); i++) {
                String query = "INSERT INTO `payments`( `year`, `month`, `date`, `course_id`, `s_id`, `amount`) VALUES (?,?,?,?,?,?)";

                stmt = con.prepareStatement(query);
                stmt.setString(1, year);
                stmt.setString(2, months.get(i));
                stmt.setString(3, date);
                stmt.setString(4, courseId);
                stmt.setString(5, s_id);
                stmt.setDouble(6, amount);
                x = stmt.executeUpdate();
                success.put(months.get(i), x);
            }
            return success;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int getCourseFee(String courseId) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int x = 0;
        try {

            String query = "select monthly_fee from course where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);

            result = stmt.executeQuery();
            while (result.next()) {
                x = result.getInt("monthly_fee");
            }

        } catch (Exception ex) {
            throw ex;
        }
        return x;
    }

    public LinkedHashMap<String,String> getPaymentDetails(String courseId, String studntID, int noOfMonths) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        ArrayList<String> paymentDetails = new ArrayList<String>();
        LinkedHashMap<String,String> paymentMap = new LinkedHashMap<String,String>();
        int x = 0;
        try {

            String query = "select month from payments where course_id = ? and s_id=? order by id LIMIT 11";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, studntID);

            result = stmt.executeQuery();
            while (result.next()) {
                paymentDetails.add(result.getString("month"));
            }
            if(noOfMonths>11){
                noOfMonths=paymentDetails.size();
            }
            
            for(int i=0;i<noOfMonths;i++){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, -i);
                String Month = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
                if(paymentDetails.contains(Month)){
                    paymentMap.put(Month, "Payment Completed");
                }
                else{
                    paymentMap.put(Month, "Payment not Completed");
                }
            }

        } catch (Exception ex) {
            throw ex;
        }
        return paymentMap;
    }

}
