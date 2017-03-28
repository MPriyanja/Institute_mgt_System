/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.student.BL;

import com.institute_management.student.BEAN.Student;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NIPUN
 */
public class StudentDetails extends AbstractTableModel {

    private static final String[] column = {"Name",
        "Date of Birth",
        "Address",
        "Email",
        "Gender",
        "Year of Registration",
        "Telephone",
        "School"};
    private static ArrayList<Student> list;

    public StudentDetails(ArrayList<Student> stList) {
        list = stList;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getName();
            case 1:
                return list.get(rowIndex).getDob();
            case 2:
                return list.get(rowIndex).getAddress();
            case 3:
                return list.get(rowIndex).getEmail();
            case 4:
                return list.get(rowIndex).getGender();
            case 5:
                return list.get(rowIndex).getYearOfReg();
            case 6:
                return list.get(rowIndex).getTelephn();
            case 7:
                return list.get(rowIndex).getSchool();
            default:
                return "Error";

        }

    }

    @Override
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }

}
