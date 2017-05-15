package com.institute_management.user_mgt.DB;

import com.institute_management.user_mgt.bean.userBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDbConnection {

    
      
    Connection con = getConnection();
 
    public Connection getConnection()  {
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
            throw ex;
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
            String query = "select us.*, pr.page_id from sys_users us, privileges pr where user_name = ? and us.role_id = pr.role_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            result = stmt.executeQuery();

            while (result.next()) {
                if (count == 0) {
//                    userbean.setAddress(result.getString("address"));
//                    userbean.setContact(result.getString("contact"));
//                    userbean.setGender(result.getString("gender"));
//                    userbean.setNic(result.getString("nic"));
//                    userbean.setName(result.getString("name"));
                    userbean.setUserID(result.getInt("user_id"));
                    userbean.setRoleID(result.getInt("role_id"));
                    userbean.setUserName(result.getString("user_name"));
                    userbean.setPassword(result.getString("password"));
                }
                privilegeGrantedPageList.add(result.getInt("page_id"));
                count++;
            }

            userbean.setPrivilegeGrantedPageList(privilegeGrantedPageList);
        } catch (Exception ex) {
            throw ex;
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
            throw ex;
        }
        result.close();
        stmt.close();

        return stringArray;
    }

    //select distinct pd.page_description from page_details pd, privileges pr, sys_users su, user_role ur where user_name = 'malinda' and su.role_id = pr.role_id and pr.page_id = pd.page_id
    public HashMap<Integer, String> getAllPages() throws Exception {
        HashMap<Integer, String> pageMap = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            String query = "select * from page_details order by page_id";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                pageMap.put(result.getInt("page_id"), result.getString("page_description"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return pageMap;
    }

    public ArrayList<Integer> getPageListForRole(String role_name) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Integer> accessGrantedPageList = new ArrayList<Integer>();
        try {
            String query = "SELECT page_id FROM privileges pr, user_role ur WHERE ur.description = ? and ur.user_role_id = pr.role_id";
            stmt = con.prepareStatement(query);
            stmt.setString(1, role_name);
            result = stmt.executeQuery();

            while (result.next()) {
                accessGrantedPageList.add(result.getInt("page_id"));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return accessGrantedPageList;
    }

    public void deletePagePrivilages(int role_id) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Integer> accessGrantedPageList = new ArrayList<Integer>();
        try {
            String query = "DELETE FROM privileges WHERE role_id = ? ";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, role_id);
            stmt.executeUpdate();

            stmt.close();

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addPagePrivilages(int role_id, int page_id) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Integer> accessGrantedPageList = new ArrayList<Integer>();
        try {
            String query = "INSERT INTO `privileges` (`role_id`, `page_id`) VALUES (?,?)";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, role_id);
            stmt.setInt(2, page_id);
            stmt.executeUpdate();
            System.out.println("INSERT INTO privileges (`role_id`, `page_id`) VALUES (" + role_id + "," + page_id + ")");
        } catch (Exception ex) {
            throw ex;
        }

    }

    public int getUserRoleIDbyName(String role) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        int roleID = 0;

        try {
            String query = "select `user_role_id` from `user_role` where description=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, role);

            result = stmt.executeQuery();
            while (result.next()) {
                roleID = result.getInt("user_role_id");
            }

        } catch (Exception ex) {
            throw ex;
        }
        return roleID;
    }

    public int AddSystemUser(userBean a) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        int success = 0;

        try {
            String query = "INSERT INTO `sys_users`(`name`, `nic`, `contact`, `address`, `gender`, `user_name`, `password`, `role_id`) VALUES (?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getNic());
            stmt.setString(3, a.getContact());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, a.getGender());
            stmt.setString(6, a.getUserName());
            stmt.setString(7, a.getPassword());
            stmt.setInt(8, a.getRoleID());

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        }
        return success;
    }
    
    public int UpdateSystemUser(userBean a, String oldUserName) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        int success = 0;

        try {
            String query = "UPDATE `sys_users` SET `name`= ?,`nic`= ?,`contact`= ?,`address`= ?,`gender`= ?,`user_name`=?,`password`=?,`role_id`=? WHERE user_name = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getNic());
            stmt.setString(3, a.getContact());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, a.getGender());
            stmt.setString(6, a.getUserName());
            stmt.setString(7, a.getPassword());
            stmt.setInt(8, a.getRoleID());
            stmt.setString(9, oldUserName);

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        }
        return success;
    }
    
    public boolean checkUserNameExistancy(String userName) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        boolean userNameExist = false;

        try {
            String query = "select * from `sys_users` where user_name=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);

            result = stmt.executeQuery();
            if(result.next()){
                userNameExist = true;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return userNameExist;
    }
}
