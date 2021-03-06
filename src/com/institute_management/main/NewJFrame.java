/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.main;

import com.institute_management.attendence.attendenceCourse;
import com.institute_management.course_mgt.coursePOPUPNEW;
import com.institute_management.course_mgt.selectCourse;
import com.institute_management.lecture_mgt.lecturerPOPUPNEW;
import com.institute_management.payment_mgt.PaymentDashBoard;
import com.institute_management.report.AttendenaceReport;
import com.institute_management.report.reportGen;
import com.institute_management.student.UI.studentPOPUPNEW;
import com.institute_management.subject_mgt.UI.subjectPOPUPNEW;
import com.institute_management.user_mgt.UI.sysuserPOPUPNEW;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author tharindu_m
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() throws Exception {
        initComponents();
//        Toolkit tk = Toolkit.getDefaultToolkit();
//        int xsize = (int) tk.getScreenSize().getWidth();
//        int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(1366, 780);
        DashBoardMethods dbM = new DashBoardMethods();
        try {
            lblTotalCourses.setText(dbM.getTotalCourses() + "");
            lblTotalLectures.setText(dbM.getTotalLecturers() + "");
            lblTotalStudents.setText(dbM.getTotalStudents() + "");
            //lblTotalIncome.setText("Rs " + dbM.getTotalIncome() + "");
            lblTotalIncome.setText("Rs 4000.00");
        } catch (Exception ex) {
            throw ex;
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotalCourses = new javax.swing.JLabel();
        lblTotalStudents = new javax.swing.JLabel();
        lblTotalLectures = new javax.swing.JLabel();
        lblTotalIncome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LBLCOURSE = new javax.swing.JLabel();
        lblStudent = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        lblLecture = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        lblAttendance = new javax.swing.JLabel();
        lblPayment = new javax.swing.JLabel();
        lblPrivilege = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblReport = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblReports = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("INSTITUTE OF PROFESSIONAL STUDIES");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-27, 70, 1060, 40));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("STUDENT MANAGEMENT SYSTEM");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 92, 1050, 60));

        lblTotalCourses.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        lblTotalCourses.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalCourses.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblTotalCourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 90, 80));

        lblTotalStudents.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        lblTotalStudents.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalStudents.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblTotalStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 90, 80));

        lblTotalLectures.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        lblTotalLectures.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalLectures.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblTotalLectures, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 80, 80));

        lblTotalIncome.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lblTotalIncome.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalIncome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(lblTotalIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 180, 110, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/dashbord new.jpg"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 1020, 310));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 1020, 330));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jPanel1.setMinimumSize(new java.awt.Dimension(238, 505));
        jPanel1.setPreferredSize(new java.awt.Dimension(238, 505));
        jPanel1.setLayout(null);

        LBLCOURSE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE COURSEMANAGEMNT.jpg"))); // NOI18N
        LBLCOURSE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLCOURSEMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLCOURSEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLCOURSEMouseExited(evt);
            }
        });
        jPanel1.add(LBLCOURSE);
        LBLCOURSE.setBounds(10, 230, 226, 44);

        lblStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE STUDENT.jpg"))); // NOI18N
        lblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStudentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblStudentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblStudentMouseExited(evt);
            }
        });
        jPanel1.add(lblStudent);
        lblStudent.setBounds(10, 280, 226, 44);

        lblSubject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE SUBJECTMANAGEMNT.jpg"))); // NOI18N
        lblSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubjectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSubjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSubjectMouseExited(evt);
            }
        });
        jPanel1.add(lblSubject);
        lblSubject.setBounds(10, 330, 226, 44);

        lblLecture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE LECTURER.jpg"))); // NOI18N
        lblLecture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLectureMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLectureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLectureMouseExited(evt);
            }
        });
        jPanel1.add(lblLecture);
        lblLecture.setBounds(10, 380, 226, 44);

        lblDashboard.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(204, 204, 204));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE DASHBOARD.jpg"))); // NOI18N
        lblDashboard.setIconTextGap(10);
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDashboardMouseExited(evt);
            }
        });
        jPanel1.add(lblDashboard);
        lblDashboard.setBounds(10, 180, 226, 44);

        lblAttendance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE ATTANDANCE.jpg"))); // NOI18N
        lblAttendance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttendanceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAttendanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAttendanceMouseExited(evt);
            }
        });
        jPanel1.add(lblAttendance);
        lblAttendance.setBounds(10, 530, 226, 44);

        lblPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE PAYMENTMANAGEMNT.jpg"))); // NOI18N
        lblPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPaymentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPaymentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPaymentMouseExited(evt);
            }
        });
        jPanel1.add(lblPayment);
        lblPayment.setBounds(10, 430, 226, 44);

        lblPrivilege.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE PRIVILAGE.jpg"))); // NOI18N
        lblPrivilege.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrivilegeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPrivilegeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPrivilegeMouseExited(evt);
            }
        });
        jPanel1.add(lblPrivilege);
        lblPrivilege.setBounds(10, 480, 226, 44);

        lblEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE EMAIL.jpg"))); // NOI18N
        lblEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEmailMouseExited(evt);
            }
        });
        jPanel1.add(lblEmail);
        lblEmail.setBounds(10, 630, 226, 44);

        lblReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/BLUE REPORTS.jpg"))); // NOI18N
        lblReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblReportMouseExited(evt);
            }
        });
        jPanel1.add(lblReport);
        lblReport.setBounds(10, 580, 226, 44);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new ImageIcon(new ImageIcon("/com/institute_management/resources/images/MainFrame/ipslogo.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/ipslogo.jpg"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(-10, 10, 280, 140);

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 300, 700));

        jLabel6.setIcon(new ImageIcon(new ImageIcon("F:\\new version\\Institute_mgt_System\\src\\com\\institute_management\\resources\\images\\MainFrame\\multiple_circle_chart.gif").getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT)));

        //ImageIcon imageIcon = new ImageIcon(new ImageIcon("/com/institute_management/resources/images/MainFrame/data_viz.gif").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        //jLabel6.setIcon(imageIcon);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/multiple_circle_chart.gif"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 380, 250));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Course Reports");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 370, 400, 40));

        lblReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/pdf.JPG"))); // NOI18N
        lblReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReportsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblReportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblReportsMouseExited(evt);
            }
        });
        jPanel2.add(lblReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 410, 310, 270));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Current Attendence Status");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 400, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setForeground(new java.awt.Color(0, 0, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/MainFrame/bluewolf inc.JPG"))); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 160, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 1370, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportMouseExited
        lblReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblReportMouseExited

    private void lblReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportMouseEntered
        lblReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblReportMouseEntered

    private void lblEmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmailMouseExited
        lblEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblEmailMouseExited

    private void lblEmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmailMouseEntered
        lblEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblEmailMouseEntered

    private void lblPrivilegeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrivilegeMouseExited
        lblPrivilege.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblPrivilegeMouseExited

    private void lblPrivilegeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrivilegeMouseEntered
        lblPrivilege.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblPrivilegeMouseEntered

    private void lblPrivilegeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrivilegeMouseClicked
        sysuserPOPUPNEW spp = new sysuserPOPUPNEW();
        this.dispose();
        spp.setVisible(true);
    }//GEN-LAST:event_lblPrivilegeMouseClicked

    private void lblPaymentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaymentMouseExited
        lblPayment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblPaymentMouseExited

    private void lblPaymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaymentMouseEntered
        lblPayment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblPaymentMouseEntered

    private void lblPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaymentMouseClicked
        PaymentDashBoard pd = new PaymentDashBoard();

        pd.setVisible(true);

    }//GEN-LAST:event_lblPaymentMouseClicked

    private void lblAttendanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttendanceMouseExited
        lblAttendance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblAttendanceMouseExited

    private void lblAttendanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttendanceMouseEntered
        lblAttendance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblAttendanceMouseEntered

    private void lblDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseExited
        lblDashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblDashboardMouseExited

    private void lblDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseEntered
        lblDashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblDashboardMouseEntered

    private void lblLectureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLectureMouseExited
        lblLecture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblLectureMouseExited

    private void lblLectureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLectureMouseEntered
        lblLecture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblLectureMouseEntered

    private void lblLectureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLectureMouseClicked
        lecturerPOPUPNEW cpp = new lecturerPOPUPNEW();
        this.dispose();
        cpp.setVisible(true);
    }//GEN-LAST:event_lblLectureMouseClicked

    private void lblSubjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubjectMouseExited
        lblSubject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblSubjectMouseExited

    private void lblSubjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubjectMouseEntered
        lblSubject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblSubjectMouseEntered

    private void lblStudentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentMouseExited
        lblStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lblStudentMouseExited

    private void lblStudentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentMouseEntered
        lblStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblStudentMouseEntered

    private void lblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentMouseClicked
        studentPOPUPNEW cpp = new studentPOPUPNEW();
        this.dispose();
        cpp.setVisible(true);
    }//GEN-LAST:event_lblStudentMouseClicked

    private void LBLCOURSEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCOURSEMouseExited
        LBLCOURSE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_LBLCOURSEMouseExited

    private void LBLCOURSEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCOURSEMouseEntered
        LBLCOURSE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_LBLCOURSEMouseEntered

    private void LBLCOURSEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCOURSEMouseClicked
        coursePOPUPNEW cpp = new coursePOPUPNEW();
        this.dispose();
        cpp.setVisible(true);

    }//GEN-LAST:event_LBLCOURSEMouseClicked

    private void lblSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubjectMouseClicked
        this.dispose();
        subjectPOPUPNEW spp = new subjectPOPUPNEW();
        spp.setVisible(true);
    }//GEN-LAST:event_lblSubjectMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        final MultiplePieChartDemo2 demo = new MultiplePieChartDemo2("Multiple Pie Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void lblReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportsMouseClicked
       AttendenaceReport ar = new AttendenaceReport();
       ar.setVisible(true);
       
        //G:\\new version\\Institute_mgt_System\\src\\com\\institute_management\\report\\AttendanceReport.jasper
    }//GEN-LAST:event_lblReportsMouseClicked

    private void lblAttendanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttendanceMouseClicked
        // TODO add your handling code here:
        attendenceCourse ac=new attendenceCourse();
        ac.setVisible(true);
        this.dispose();
                
    }//GEN-LAST:event_lblAttendanceMouseClicked

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
       jLabel6.setBorder(null);
    }//GEN-LAST:event_jLabel6MouseExited

    private void lblReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportsMouseEntered
        lblReports.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255))); 
    }//GEN-LAST:event_lblReportsMouseEntered

    private void lblReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportsMouseExited
         lblReports.setBorder(null);
    }//GEN-LAST:event_lblReportsMouseExited
   
        

   
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
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame().setVisible(true);

                } catch (Exception ex) {
                    Logger.getLogger(NewJFrame.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBLCOURSE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAttendance;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLecture;
    private javax.swing.JLabel lblPayment;
    private javax.swing.JLabel lblPrivilege;
    private javax.swing.JLabel lblReport;
    private javax.swing.JLabel lblReports;
    private javax.swing.JLabel lblStudent;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTotalCourses;
    private javax.swing.JLabel lblTotalIncome;
    private javax.swing.JLabel lblTotalLectures;
    private javax.swing.JLabel lblTotalStudents;
    // End of variables declaration//GEN-END:variables
}
