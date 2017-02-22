package com.institute_management.user_mgt.DB;


import com.institute_management.user_mgt.bean.userBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean validateUser(String userName, String password) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet result = null;
        boolean permissionStatus = false;
        try {
            String query = "SELECT USER_NAME,PASSWORD FROM SYS_USERS WHERE USER_NAME = ? AND PASSWORD = ? ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, password);

            result = stmt.executeQuery();
            while (result.next()) {
                permissionStatus = true;
            }
            System.out.println(query);
        } catch (Exception ex) {

        }
        result.close();
        stmt.close();
       
        return permissionStatus;

    }

    public userBean getUserDetails(String userName) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        int count = 0;
        ArrayList<Integer> privilegeGrantedPageList = new ArrayList<>();
        userBean userbean = new userBean();

        try {
            String query = "select us.user_id,us.role_id, pr.page_id from sys_users us, privileges pr where user_name = ? and us.role_id = pr.role_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            result = stmt.executeQuery();

            
            while (result.next()) {
                if (count == 0) {
                    userbean.setUserID(result.getInt("user_id"));
                    userbean.setRoleID(result.getInt("role_id")); 
                }
                privilegeGrantedPageList.add(result.getInt("page_id"));
                count++;
            }
            
            userbean.setPrivilegeGrantedPageList(privilegeGrantedPageList);
        } catch (Exception ex) {

        }
        result.close();
        stmt.close();
        
        return userbean;

    }

    public String[] selectUserRoles() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        int noOfRole = 0;
        String[] stringArray = null;
        ArrayList<String> roleList = new ArrayList<String>();
        try {
            String query = "SELECT `DESCRIPTION` FROM `user_role`";

            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                String role = result.getString("DESCRIPTION");
                roleList.add(role);
            }
            stringArray = roleList.toArray(new String[0]);

        } catch (Exception ex) {

        }
        result.close();
        stmt.close();
        
        return stringArray;
    }

}
