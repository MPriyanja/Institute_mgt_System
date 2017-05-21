/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.student.UI;

import com.institute_management.student.BEAN.Student;
import com.institute_management.student.BL.StudentDetails;
import com.institute_management.subject_mgt.DB.SubjectDbConnection;
import com.institute_management.user_mgt.UI.mainFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author NIPUN
 */
public class addStudent extends javax.swing.JFrame {

    /**
     * Creates new form addStudent
     */
    ArrayList<Student> stList;

    //  ArrayList<Object[]> tableData;//=new Object[3];
    SubjectDbConnection dbCon = new SubjectDbConnection();

    public addStudent() {
        initComponents();
        loadStudent();
        jDOB.setDate(new Date());
        jYOR.setDate(new Date());
        

    }

    public void loadStudent() {
        try {
            stList = dbCon.selectAllStudent();
            StudentDetails sd = new StudentDetails(stList);
            tblViewStudent.setModel(sd);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error Occured in data loading");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        txtAddrs = new javax.swing.JTextField();
        comboGender = new javax.swing.JComboBox();
        txtTele = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSchool = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpcontact = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jDOB = new com.toedter.calendar.JDateChooser();
        jYOR = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViewStudent = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Student");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Name         ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 60, 102, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Date of Birth ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 92, 102, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Address       ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 131, 102, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Telephone              ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 130, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Email    ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 156, 102, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Gender     ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 190, 102, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("School                      ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 130, -1));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 60, 186, -1));

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });
        jPanel1.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 153, 186, -1));

        txtAddrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddrsActionPerformed(evt);
            }
        });
        jPanel1.add(txtAddrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 123, 186, -1));

        comboGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        comboGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGenderActionPerformed(evt);
            }
        });
        jPanel1.add(comboGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 187, 109, -1));
        jPanel1.add(txtTele, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 98, 188, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Year of Registration  ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, -1, 20));

        txtSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSchoolActionPerformed(evt);
            }
        });
        jPanel1.add(txtSchool, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 130, 188, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Parent Contact NO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 140, -1));

        txtpcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpcontactActionPerformed(evt);
            }
        });
        jPanel1.add(txtpcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 168, 188, -1));

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Clear");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 236, 80, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 236, 83, -1));

        jDOB.setDateFormatString("yyyy-MM-dd");
        jDOB.setPreferredSize(new java.awt.Dimension(6, 20));
        jPanel1.add(jDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 90, 186, 25));

        jYOR.setDateFormatString("yyyy-MM-dd");
        jYOR.setPreferredSize(new java.awt.Dimension(6, 20));
        jPanel1.add(jYOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 60, 188, 25));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Student"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Add Student");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(jLabel1)
                .addContainerGap(526, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 215, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1040, 260));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("View Student"));

        tblViewStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Date of Birth", "Address", "Email", "Gender", "Year of Registration", "Telephone", "School"
            }
        ));
        jScrollPane1.setViewportView(tblViewStudent);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    private void txtAddrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddrsActionPerformed

    private void comboGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGenderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            String s=sdf.format(jDOB.getDate());
            Student st = new Student();
            st.setAddress(txtAddrs.getText());
//            if(jDOB.getDate()==null){
//             JOptionPane.showMessageDialog(new JFrame(), "Please enter Date Of Birth ");
//
//            }
            st.setDob(sdf.format(jDOB.getDate()));
            st.setEmail(txtMail.getText());
            st.setGender(comboGender.getSelectedItem().toString());
            st.setName(txtName.getText());
            st.setSchool(txtSchool.getText());
//            st.setTelephn(txtTele.getText());
//             if(jYOR.getDate()==null){
//             JOptionPane.showMessageDialog(new JFrame(), "Please enter Registration year ");
//
//            }
            st.setYearOfReg(sdf.format(jYOR.getDate()));
            
            st.setpContactNo(txtpcontact.getText());

            //SubjectDbConnection dbCon = new SubjectDbConnection();
            int count = dbCon.insertStudent(st);
            if (count > 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Successfully inserted ");

                loadStudent();

                jDOB.setDate(null);
                txtMail.setText(null);
                txtName.setText(null);
                txtSchool.setText(null);
                txtTele.setText(null);
                jYOR.setDate(null);
                txtpcontact.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error Occured " + e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSchoolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSchoolActionPerformed

    private void txtpcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpcontactActionPerformed

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
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboGender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDOB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jYOR;
    private javax.swing.JTable tblViewStudent;
    private javax.swing.JTextField txtAddrs;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSchool;
    private javax.swing.JTextField txtTele;
    private javax.swing.JTextField txtpcontact;
    // End of variables declaration//GEN-END:variables
}
