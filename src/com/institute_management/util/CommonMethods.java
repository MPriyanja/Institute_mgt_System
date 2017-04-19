/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.util;

import com.institute_management.main.Main;
import static com.institute_management.util.Configurations.UserBean;
import java.util.ArrayList;

/**
 *
 * @author mihiran_p
 * @date Feb 22, 2017
 */
public class CommonMethods {

    public static boolean isNull(String text) throws Exception {
        boolean flag = true;

        if (text != null) {
            flag = false;
        }
        return flag;
    }

    public static boolean isEmpty(String text) throws Exception {
        boolean flag = true;

        if (text.trim() != "") {
            flag = false;
        }
        return flag;
    }
    
    /*
     * @author malinda_r
     * @date Feb 22, 2017
     */
    public static boolean check_user_access(int pageID) throws Exception {
        boolean accessStatus = false;
        ArrayList<Integer> pageAccessList = UserBean.getPrivilegeGrantedPageList();
        if (pageAccessList.contains(pageID)) {
            accessStatus = true;
        }
        return accessStatus;
    }

}
