package com.view;

import com.model.model_caribarang;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class FrmCariBarang extends javax.swing.JFrame {
    
    public DefaultTableModel tbl;
    String header[] = {"Kode Barang", "Nama Barang", "Kategori", "Harga", "Tanggal Kadaluarsa"};
    model_caribarang model = new model_caribarang();
    
    public FrmCariBarang() throws SQLException {
        initComponents();
        tbl = new DefaultTableModel(null, header);
        tblCariBarang.setModel(tbl);
        tblCariBarang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        model.Tampil(this);
        setLebarKolom();
        this.getRootPane().setDefaultButton(btnCari);
        txtCari.requestFocus();
    }
    
    public void setColomnWidth(int kolom){
        DefaultTableColumnModel dt = (DefaultTableColumnModel) tblCariBarang.getColumnModel();
        TableColumn kolomtabel = dt.getColumn(kolom);
        int lebar = 0;
        int margin = 10;
        int a;
        
        TableCellRenderer render = kolomtabel.getHeaderRenderer();
        if (render == null) {
            render = tblCariBarang.getTableHeader().getDefaultRenderer();
        }
        Component kompo = render.getTableCellRendererComponent(tblCariBarang, kolomtabel.getHeaderValue(), false, false, 0, 0);
        lebar = kompo.getPreferredSize().width;
        for (a = 0; a < tblCariBarang.getRowCount(); a++) {
            render = tblCariBarang.getCellRenderer(a, kolom);
            kompo = render.getTableCellRendererComponent(tblCariBarang, tblCariBarang.getValueAt(a, kolom), false, false, a, kolom);
            int lebarKolom = kompo.getPreferredSize().width;
            lebar = Math.max(lebar, lebarKolom);
        }lebar = lebar + margin;
        kolomtabel.setPreferredWidth(lebar);
    }
    
    public void setLebarKolom(){
        int a;
        for (a = 0; a < tblCariBarang.getColumnCount(); a++) {
            setColomnWidth(a);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCariBarang = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnBatal = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCariBarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCariBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCariBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCariBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCariBarang);

        txtCari.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        btnBatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnCari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 418, Short.MAX_VALUE)
                .addComponent(btnBatal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCariBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCariBarangMouseClicked
        try {
            model.KlikTabel(this);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(FrmCariBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblCariBarangMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        try {
            model.Cari(this);
            txtCari.setText(null);
        } catch (Exception ex) {
            Logger.getLogger(FrmCariBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmCariBarang().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmCariBarang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblCariBarang;
    public javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
