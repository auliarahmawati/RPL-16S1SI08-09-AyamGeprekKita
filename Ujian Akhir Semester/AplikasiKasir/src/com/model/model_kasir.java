package com.model;

import com.controller.controller_kasir;
import com.koneksi.koneksi;
import com.view.FrmKasir;
import com.view.FrmKembalian;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class model_kasir implements controller_kasir{

    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    
    Map<String, Object> parameter = new HashMap<String, Object>();
    
    @Override
    public void Simpan(FrmKasir ksr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "insert transaksi values (?,?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ksr.txtid_transaksi.getText());
            prepare.setString(2, ksr.txtkode_transaksi.getText());
            prepare.setString(3, ksr.txtnama_kasir.getText());
            prepare.setString(4, ksr.txtTanggal.getText());
            prepare.setString(5, ksr.txtharga.getText());
            prepare.setString(6, ksr.txtjumlah.getText());
            prepare.executeUpdate();
            
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "insert detail_transaksi values (?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ksr.txtid_transaksi.getText());
            prepare.setString(2, ksr.txtkode_barang.getText());
            prepare.executeUpdate();
           
            prepare.close();
        } catch (Exception e) {
        }
        finally {
            Tampil(ksr);
            AutoNomor(ksr);
            Total(ksr);
            Bersih(ksr);
            ksr.setLebarKolom();
        }
    }    

    @Override
    public void Tampil(FrmKasir ksr) throws SQLException {
        ksr.tbl.getDataVector().removeAllElements();
        ksr.tbl.fireTableDataChanged();
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select a.id_transaksi, c.kode_barang, c.nama, c.harga, a.jumlah_barang, a.harga_barang from transaksi a join detail_transaksi b on b.id_transaksi=a.id_transaksi join barang c on c.kode_barang=b.kode_barang where a.kode_transaksi='"+ksr.txtkode_transaksi.getText()+"' order by a.id_transaksi asc";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ksr.tbl.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Tanggal(FrmKasir ksr) throws SQLException {
        java.util.Date sekarang = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        ksr.txtTanggal.setText(kal.format(sekarang));
    }

    @Override
    public void ScanBarcode(FrmKasir ksr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            Statement st = con.createStatement();
            String sql = "select * from barang where kode_barang = '"+ksr.txtkode_barang.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ksr.txtnama.setText(rs.getString("nama"));
                ksr.txtharga.setText(rs.getString("harga"));
                ksr.txtjumlah.setText("1");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            ksr.txtharga.requestFocus();
        }
    }

    @Override
    public void AutoNomor(FrmKasir ksr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(id_transaksi) from transaksi";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                ksr.txtid_transaksi.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    @Override
    public void AutoNomorKode(FrmKasir ksr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            
            Statement st = con.createStatement();
            String sql = "select max(kode_transaksi) from transaksi";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int a = rs.getInt(1);
                ksr.txtkode_transaksi.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    @Override
    public void Bersih(FrmKasir ksr) throws SQLException {
        ksr.txtkode_barang.setText(null);
        ksr.txtnama.setText(null);
        ksr.txtharga.setText(null);
        ksr.txtjumlah.setText(null);
        ksr.txtkode_barang.requestFocus();
    }

    @Override
    public void Total(FrmKasir ksr) throws SQLException {
        int JumlahBaris = ksr.tblKasir.getRowCount();
        int Total = 0;
        int Harga_Barang;
        TableModel tableModel;
        tableModel = ksr.tblKasir.getModel();
        for (int i = 0; i < JumlahBaris; i++) {
            Harga_Barang = Integer.parseInt(tableModel.getValueAt(i, 5).toString());
            Total = (Total + Harga_Barang);
            ksr.txttotal.setText(String.valueOf(Total));
            ksr.txttotal2.setText("Total "+String.valueOf(Total));
        }
    }

    @Override
    public void Kembalian(FrmKasir ksr) throws SQLException {
        double total = Double.valueOf(ksr.txttotal.getText());
        double bayar = Double.valueOf(ksr.txtbayar.getText());
        double kembalian = (bayar-total);
        ksr.txtkembali.setText(String.valueOf(kembalian));
        ksr.txttotal2.setText("-" +String.valueOf(kembalian));
        new FrmKembalian().show();
        FrmKembalian.lblKembalian.setText(String.valueOf(kembalian)); 
    }

    @Override
    public void KlikTabel(FrmKasir ksr) throws SQLException {
        try {
            int pilih = ksr.tblKasir.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            ksr.txtid_transaksi2.setText(ksr.tbl.getValueAt(pilih, 0).toString());
            ksr.txtkode_barang.setText(ksr.tbl.getValueAt(pilih, 1).toString());
            ksr.txtnama.setText(ksr.tbl.getValueAt(pilih, 2).toString());
            ksr.txtharga.setText(ksr.tbl.getValueAt(pilih, 3).toString());
            ksr.txtjumlah.setText(ksr.tbl.getValueAt(pilih, 4).toString());
        } catch (Exception e) {
        } finally {
            ksr.txtjumlah.requestFocus();
            ksr.txtjumlah.setText(null);
        }
    }

    @Override
    public void UbahJumlah(FrmKasir ksr) throws SQLException {
        double harga = Double.valueOf(ksr.txtharga.getText());
        double jumlah = Double.valueOf(ksr.txtjumlah.getText());
        double jumlah_baru = (harga*jumlah);
        ksr.txtharga.setText(String.valueOf(jumlah_baru));
        Ubah(ksr);
        Total(ksr);
    }

    @Override
    public void Ubah(FrmKasir ksr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "update transaksi set harga_barang = '"+ksr.txtharga.getText()+"', "
                    + "jumlah_barang = '"+ksr.txtjumlah.getText()+"' "
                    + "where id_transaksi = '"+ksr.txtid_transaksi2.getText()+"'";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.executeUpdate();
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(ksr);
            Bersih(ksr);
            ksr.txtkode_barang.requestFocus();
        }
    }

    @Override
    public void Hapus(FrmKasir ksr) throws SQLException {
        try {
            Connection con = koneksi.getKoneksi();
            String sql = "delete from transaksi where id_transaksi = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            prepare.setString(1, ksr.txtid_transaksi2.getText());
            prepare.executeUpdate();
            
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(ksr);
            AutoNomor(ksr);
            Bersih(ksr);
            ksr.setLebarKolom();
            Total(ksr);
        }
    }

    @Override
    public void CetakStruk(FrmKasir ksr) throws SQLException {
        try {
            parameter.put("kode_transaksi", ksr.txtkode_transaksi.getText().toString());
            parameter.put("total", ksr.txttotal.getText());
            parameter.put("bayar", ksr.txtbayar.getText());
            parameter.put("kembali", ksr.txtkembali.getText());
            File file = new File("src/com/report/struk.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, koneksi.getKoneksi());
            JasperViewer.viewReport(jasperPrint, false);
            //JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception e) {
        }
    }
}
