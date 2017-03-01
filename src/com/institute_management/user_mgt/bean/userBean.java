/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.user_mgt.bean;

import java.util.ArrayList;


public class userBean {
    private String userName;
    private int roleID;
    private int userID;
    private ArrayList privilegeGrantedPageList;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the roleID
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * @param roleID the roleID to set
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the privilegeGrantedPageList
     */
    public ArrayList getPrivilegeGrantedPageList() {
        return privilegeGrantedPageList;
    }

    /**
     * @param privilegeGrantedPageList the privilegeGrantedPageList to set
     */
    public void setPrivilegeGrantedPageList(ArrayList privilegeGrantedPageList) {
        this.privilegeGrantedPageList = privilegeGrantedPageList;
    }
           
}
