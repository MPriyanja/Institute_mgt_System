/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.util;

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

}
