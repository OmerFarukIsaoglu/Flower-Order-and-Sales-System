/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bp2proje2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
/**
 *
 * @author awada
 */
public class UserTeslimatBilgisi extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserTeslimatBilgisi.class.getName());
    private String Ad;
    private int kullaniciID;

    /**
     * Creates new form UserTeslimatBilgisi
     */
    public UserTeslimatBilgisi(String ad, int kuulaniciId) {
        initComponents();
        this.Ad = ad;
        this.kullaniciID = kuulaniciId;
        TabloYukle();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        UserAnaSayfa useranasayfa = new UserAnaSayfa(Ad, kullaniciID);
        useranasayfa.setVisible(true);
    }//GEN-LAST:event_formWindowClosing
    //DATABASE ILE FRAME ARASINDAKI BAGLANTIYI SAGLAYABILMEK ICIN BIR GETCONNECITON METODU
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/your_database",
    "your_username",
    "your_password"
            );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Bağlantı hatası: " + ex.getMessage());
        }
        return conn;
    }

    private void TabloYukle() {
        // JTable için boş bir model oluşturuluyor ve sütunlar ekleniyor
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Cicek Adi");
    model.addColumn("Adet");
    model.addColumn("Toplam");
    model.addColumn("Durum");
    model.addColumn("Tarih");
    
    // Veritabanı bağlantısı açılıyor
    Connection conn = getConnection();
    if (conn == null) {
        return;
    }
    
    try {
        Statement st = conn.createStatement();
        
        // Sadece giriş yapan kullanıcıya ait siparişler kullaniciID ile filtrelenerek çekiliyor
        String sql = String.format("SELECT c.ad as cicek_ad, s.adet, s.toplam_fiyat, s.durum, s.siparis_tarihi FROM siparisler s JOIN cicekler c ON s.cicek_id = c.id WHERE s.kullanici_id = %d", kullaniciID);
        ResultSet rs = st.executeQuery(sql);
        
        // Her satır tabloya ekleniyor
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("cicek_ad"),
                rs.getInt("adet"),
                rs.getInt("toplam_fiyat"),
                rs.getString("durum"),
                rs.getTimestamp("siparis_tarihi")
            });
        }
        
        // Model JTable'a bağlanıyor
        Table.setModel(model);
        st.close();
        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Hata: " + ex.getMessage(), "Hata!", JOptionPane.ERROR_MESSAGE);
    }}

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserTeslimatBilgisi("", 0).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
