package com.model;

import com.controller.controller_kembalian;
import com.view.FrmKembalian;
import com.view.FrmKasir;
import java.sql.SQLException;


public class model_kembalian implements controller_kembalian {

    @Override
    public void Bersih(FrmKembalian kmbl) throws SQLException {        
        kmbl.dispose();
        FrmKasir.txttotal.setText(null);
        FrmKasir.txttotal2.setText(null);
        FrmKasir.txtbayar.setText(null);
        FrmKasir.txtkembali.setText(null);
        new FrmKasir().show();
    }   
}
