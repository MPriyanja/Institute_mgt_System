/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.student.BEAN;

import java.util.Date;

/**
 *
 * @author NIPUN
 */
public class Student {
    private String name;
    private String telephn;
    private String dob;
    private String address;
    private String email;
    private String school;
    private String gender;
    private String yearOfReg;
    private String pContactNo;
    private String studentID;
    private String parentName;
    private String parentEmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephn() {
        return telephn;
    }

    public void setTelephn(String telephn) {
        this.telephn = telephn;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYearOfReg() {
        return yearOfReg;
    }
  
    public void setYearOfReg(String yearOfReg) {
        this.yearOfReg = yearOfReg;
    }

    public String getpContactNo() {
        return pContactNo;
    }

    public void setpContactNo(String pContactNo) {
        this.pContactNo = pContactNo;
    }

    /**
     * @return the studentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }


    }
