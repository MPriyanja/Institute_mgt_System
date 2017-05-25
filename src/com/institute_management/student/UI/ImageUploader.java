/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.student.UI;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mihiran
 */
public class ImageUploader extends javax.swing.JFrame {

    /**
     * Creates new form ImageUploader
     */
    private static final int IMG_WIDTH = 120;
    private static final int IMG_HEIGHT = 120;
    ImageIcon photo;
    WritableRaster raster;
    DataBufferByte data;
    File image;
    private String studentId="1";
   

    public ImageUploader(String sid) {
        initComponents();
        this.studentId=sid;
      
    }

    private ImageUploader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        lblImg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Image Uploader"));

        jButton1.setText("Select Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Upload Image");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton1)
                        .addGap(65, 65, 65)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public Image toImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser;
        FileNameExtensionFilter filter;
        String userhome = System.getProperty("user.home");
        chooser = new JFileChooser(userhome + "\\Music");

//JFileChooser fc = new JFileChooser(userhome +"\\Pictures");
        // chooser.set;
        File image = null;
        chooser.setCurrentDirectory(image);
        filter = new FileNameExtensionFilter("jpeg, gif and png files", "jpg", "gif", "png");
        chooser.addChoosableFileFilter(filter);

        int i = chooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            image = chooser.getSelectedFile();

            try {
                BufferedImage originalImage = ImageIO.read(image);
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                photo = new ImageIcon(toImage(resizeImageJpg));

                //converting buffered image to byte array
                raster = resizeImageJpg.getRaster();
                data = (DataBufferByte) raster.getDataBuffer();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            // JLabel label = new JLabel("", photo, JLabel.CENTER);
            lblImg.setIcon(photo);
            repaint();
            chooser.setCurrentDirectory(image);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
             // declare a connection by using Connection interface 
        Connection connection = null;
        // Create string of connection url within specified format with machine 
        //name, port number and database name. 
 
        String connectionURL = "jdbc:mysql://localhost:3306/institute_management";
 
        //declare a resultSet that works as a table resulted by execute a specified 
        //sql query.
        ResultSet rs = null;
 
        // Declare statement.
        PreparedStatement psmnt = null;
 
        try {
 
            //Load JDBC driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver").newInstance();
 
            // Create a connection by using getConnection() method that takes 
           // parameters of string type connection url, user name and password to 
            //connect to database. 
            connection = DriverManager.getConnection(connectionURL, "root", "");
            // prepareStatement() is used for create statement object that is 
            //used for sending sql statements to the specified database. 
            psmnt = connection.prepareStatement("update student set `S_IMAGE`=? where `S_ID`=?");
             byte[] extractBytes = data.getData();
            psmnt.setBytes(1, extractBytes);
            psmnt.setString(2, studentId);
         //   psmnt.setString(2, "Dhaka");
          //  psmnt.setString(4, "123456");
           
            // executeUpdate() method execute specified sql query. Here this query 
            //insert data and image from specified address. 
            int s = psmnt.executeUpdate();
            if (s > 0) {
                System.out.println("Uploaded successfully !"); 
                JOptionPane.showMessageDialog(rootPane, "Uploaded successfully !");
            } else {
                System.out.println("unsucessfull to upload image.");
            }
            connection.close();
            psmnt.close();
        } // catch if found any exception during rum time.
        catch (Exception ex) {
            System.out.println("Found some error : " + ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ImageUploader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageUploader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageUploader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageUploader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageUploader().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImg;
    // End of variables declaration//GEN-END:variables
}