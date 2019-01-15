package com.model;

import com.controller.controller_suplier;
import com.koneksi.koneksi;
import com.view.FrmSuplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class model_suplier implements controller_suplier{

    @Override
    public void Simpan(FrmSuplier spl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "insert suplier values (?,?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txtid_suplier.getText());
            prepare.setString(2, spl.txtnama_suplier.getText());
            prepare.setString(3, spl.txtalamat.getText());
            prepare.setString(4, spl.txtno_telp.getText());
            prepare.setString(5, spl.txtbank.getText());
            prepare.setString(6, spl.txtnomor_rekening.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(spl);
            spl.setLebarKolom();
            Bersih(spl);
            AutoNomor(spl);
        }
    }

    @Override
    public void Ubah(FrmSuplier spl) throws SQLException {
         try {
            Connection con = koneksi.getKoneksi();
            String sql = "update suplier set nama = ?, "
                    + "alamat = ?, "
                    + "no_telp = ?, "
                    + "bank = ?, "
                    + "nomor_rekening = ? "
                    + "where id_suplier = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txtnama_suplier.getText());
            prepare.setString(2, spl.txtalamat.getText());
            prepare.setString(3, spl.txtno_telp.getText());
            prepare.setString(4, spl.txtbank.getText());
            prepare.setString(5, spl.txtnomor_rekening.getText());
            prepare.setString(6, spl.txtid_suplier.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(spl);
            spl.setLebarKolom();
            Bersih(spl);
            AutoNomor(spl);
        }
    }

    @Override
    public void Hapus(FrmSuplier spl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "delete from suplier where id_suplier = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, spl.txtid_suplier.getText());
            prepare.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(spl);
            spl.setLebarKolom();
            Bersih(spl);
            AutoNomor(spl);
        }
    }

    @Override
    public void Tampil(FrmSuplier spl) throws SQLException {
        spl.tbl.getDataVector().removeAllElements();
        spl.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from suplier order by id_suplier asc";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                spl.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Bersih(FrmSuplier spl) throws SQLException {
        spl.txtid_suplier.setText(null);
        spl.txtnama_suplier.setText(null);
        spl.txtalamat.setText(null);
        spl.txtno_telp.setText(null);
        spl.txtbank.setText(null);
        spl.txtnomor_rekening.setText(null);
        spl.txtnama_suplier.requestFocus();
    }

    @Override
    public void KlikTabel(FrmSuplier spl) throws SQLException {
        try {
            int pilih = spl.tblsuplier.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            spl.txtid_suplier.setText(spl.tbl.getValueAt(pilih, 0).toString());
            spl.txtnama_suplier.setText(spl.tbl.getValueAt(pilih, 1).toString());
            spl.txtalamat.setText(spl.tbl.getValueAt(pilih, 2).toString());
            spl.txtno_telp.setText(spl.tbl.getValueAt(pilih, 3).toString());
            spl.txtbank.setText(spl.tbl.getValueAt(pilih, 4).toString());
            spl.txtnomor_rekening.setText(spl.tbl.getValueAt(pilih, 5).toString());
        } catch (Exception e) {
            
        }
    }

    @Override
    public void AutoNomor(FrmSuplier spl) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(id_suplier) from suplier";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                spl.txtid_suplier.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }
    
}
