/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Koneksi.Koneksi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThinkPad
 */
public class FormJuri extends javax.swing.JFrame {

    public FormJuri() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataPendaftaran();
    }

    private void loadDataPendaftaran() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Daftar");
        model.addColumn("Nama Siswa");
        model.addColumn("Nama Lomba");
        model.addColumn("Status");
        model.addColumn("Nilai");
        model.addColumn("Catatan");

        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT p.id, u.username, l.nama_lomba, p.status, p.nilai, p.catatan " +
                         "FROM pendaftaran p " +
                         "JOIN users u ON p.user_id = u.id " +
                         "JOIN lomba l ON p.lomba_id = l.id";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                model.addRow(new Object[]{
                    r.getString("id"),
                    r.getString("username"),
                    r.getString("nama_lomba"),
                    r.getString("status"),
                    r.getInt("nilai"),
                    r.getString("catatan")
                });
            }
            tblPenilaian.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }


    
    /**
     * Method Simpan/Update Nilai, Status (Lolos/Tidak Lolos), dan Catatan
     */
    
    
  private void simpanPenilaian() {
        int selectedRow = tblPenilaian.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data siswa pada tabel terlebih dahulu!");
            return;
        }

        String idPendaftaran = tblPenilaian.getValueAt(selectedRow, 0).toString();
        String status = cmbStatus.getSelectedItem().toString(); // "Lolos" atau "Tidak Lolos"
        String nilai = txtNilai.getText();
        String catatan = txtCatatan.getText();

        if (nilai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nilai tidak boleh kosong!");
            return;
        }

        try {
            Connection c = Koneksi.configDB();
            String sql = "UPDATE pendaftaran SET status=?, nilai=?, catatan=? WHERE id=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, Integer.parseInt(nilai));
            pst.setString(3, catatan);
            pst.setString(4, idPendaftaran);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Penilaian berhasil disimpan!");
            loadDataPendaftaran(); // Refresh tabel
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan nilai: " + e.getMessage());
        }
    } 

private void clearForm() {
        txtNilai.setText("");
        txtCatatan.setText("");
        cmbStatus.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPenilaian = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCatatan = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNilai = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("PENILAIAN JURI - BI GOT TALENT");

        tblPenilaian.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblPenilaian);

        jLabel1.setText("Status Keputusan :");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lolos", "Tidak Lolos" }));

        jLabel2.setText("Catatan ( Alasan / Evaluasi ) :");

        txtCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCatatanActionPerformed(evt);
            }
        });
        jScrollPane1.setViewportView(txtCatatan);

        btnSimpan.setText("Simpan Penilaian");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel3.setText("Nilai ( 0 - 100 ) :");

        txtNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNilaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNilai)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(btnSimpan))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 43, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatatanActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpanPenilaian();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNilaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNilaiActionPerformed

    
                              
private void tblPenilaianMouseClicked(java.awt.event.MouseEvent evt) {                                         
        int selectedRow = tblPenilaian.getSelectedRow();
        if (selectedRow != -1) {
            cmbStatus.setSelectedItem(tblPenilaian.getValueAt(selectedRow, 3).toString());
            txtNilai.setText(tblPenilaian.getValueAt(selectedRow, 4).toString());
            Object catatanObj = tblPenilaian.getValueAt(selectedRow, 5);
            txtCatatan.setText(catatanObj != null ? catatanObj.toString() : "");
        }
    }
 
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormJuri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormJuri().setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblPenilaian;
    private javax.swing.JTextField txtCatatan;
    private javax.swing.JTextField txtNilai;
    // End of variables declaration//GEN-END:variables

}