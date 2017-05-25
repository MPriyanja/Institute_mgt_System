/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class autoSuggest extends javax.swing.JFrame {

    /**
     * Creates new form test2
     */
    public autoSuggest() {
        initComponents();

    }

    public void comboFilter(String enteredText, JComboBox jComboBox, int x) {
        List<String> filterArray = new ArrayList<String>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/institute_management", "root", "");
            String str1 = "";
            String str = "";
            if (x == 1) {
                str = "SELECT S_ID FROM STUDENT WHERE S_ID  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_ID");
                    filterArray.add(str1);
                }
            } else if (x == 2) {
                str = "SELECT S_NAME FROM STUDENT WHERE S_NAME  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_NAME");
                    filterArray.add(str1);
                }
            } else if (x == 3) {
                str = "SELECT S_NIC FROM STUDENT WHERE S_NIC  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_NIC");
                    filterArray.add(str1);
                }
            } else if (x == 4) {
                str = "SELECT S_TELEPHONE FROM STUDENT WHERE S_TELEPHONE  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_TELEPHONE");
                    filterArray.add(str1);
                }
            }

        } catch (Exception ex) {
            System.out.println("error" + ex);
        }

        if (filterArray.size() > 0) {
            jComboBox.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            jComboBox.setSelectedItem(enteredText);
            jComboBox.showPopup();
        } else {
            jComboBox.hidePopup();
        }
    }

    public void comboFilterCourse(String enteredText, JComboBox jComboBox, int x) {
        List<String> filterArray = new ArrayList<String>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/institute_management", "root", "");
            String str1 = "";
            String str = "";
            if (x == 1) {
                str = "SELECT S_ID FROM STUDENT WHERE S_ID  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_ID");
                    filterArray.add(str1);
                }
            } else if (x == 2) {
                str = "SELECT S_NAME FROM STUDENT WHERE S_NAME  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_NAME");
                    filterArray.add(str1);
                }
            } else if (x == 3) {
                str = "SELECT S_NIC FROM STUDENT WHERE S_NIC  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_NIC");
                    filterArray.add(str1);
                }
            } else if (x == 4) {
                str = "SELECT S_TELEPHONE FROM STUDENT WHERE S_TELEPHONE  LIKE '" + enteredText + "%'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(str);
                while (rs.next()) {
                    str1 = rs.getString("S_TELEPHONE");
                    filterArray.add(str1);
                }
            }

        } catch (Exception ex) {
            System.out.println("error" + ex);
        }

        if (filterArray.size() > 0) {
            jComboBox.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            jComboBox.setSelectedItem(enteredText);
            jComboBox.showPopup();
        } else {
            jComboBox.hidePopup();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setEditable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(172, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(autoSuggest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(autoSuggest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(autoSuggest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(autoSuggest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new autoSuggest().setVisible(true);
            }
        });
    }
// Variables declaration - do not modify
    private javax.swing.JComboBox jComboBox1;
// End of variables declaration

    public void comboFilterOnCouresStudent(String text, JComboBox jSID, String selectedCourseId) {
        int x = 0;
        List<String> filterArray = new ArrayList<String>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/institute_management", "root", "");
            String str1 = "";
            String str = "";

            str = "SELECT `S_ID` FROM `student-course` WHERE `S_ID` LIKE '"+ text +"%' and `course_id`  in('" + selectedCourseId + "')";
            System.out.println(str);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                str1 = rs.getString("S_ID");
                filterArray.add(str1);
            }

        } catch (Exception ex) {
            System.out.println("error" + ex);
        }
        if (filterArray.size() > 0) {
            jSID.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            jSID.setSelectedItem(text);
            jSID.showPopup();
        } else {
            jSID.hidePopup();
        }
    }
}
