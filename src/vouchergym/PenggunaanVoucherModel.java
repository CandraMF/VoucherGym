/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vouchergym;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author candra
 */
public class PenggunaanVoucherModel {
    private PenggunaanVoucher data = new PenggunaanVoucher();

    public PenggunaanVoucher getPenggunaanVoucher() {
        return data;
    }

    public void setPenggunaanVoucher(PenggunaanVoucher penggunaanVoucher) {
        this.data = penggunaanVoucher;
    }
    
    public boolean insertByIdVoucherMember(int id_voucher_member) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into penggunaan_voucher (id_voucher_member, tanggal_masuk) values (?,NOW())");
            con.preparedStatement.setInt(1, id_voucher_member);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
     public void cetakReport() {
        Koneksi con = new Koneksi();
        String is = "./src/vouchergym/report_penggunaan_voucher.jasper";
        Map map = new HashMap();
        map.put("judul", "Report Data Penggunaan Voucher");
        con.bukaKoneksi();
        try {
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(is, map, con.dbKoneksi);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        con.tutupKoneksi();
    }
}
