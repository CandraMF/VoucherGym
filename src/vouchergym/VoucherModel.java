/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vouchergym;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author candra
 */
public class VoucherModel {

    private Voucher data = new Voucher();

    public Voucher getData() {
        return data;
    }

    public void setData(Voucher data) {
        this.data = data;
    }

    public ObservableList<Voucher> load(String keyword) {
        try {
            ObservableList<Voucher> tableData = FXCollections.observableArrayList();
            Koneksi koneksi = new Koneksi();

            koneksi.bukaKoneksi();
            koneksi.statement = koneksi.dbKoneksi.createStatement();
            ResultSet resultSet = koneksi.statement.executeQuery("select id, nama, harga, quota, jumlah_jam_per_quota, expired from voucher "
                    + "WHERE "
                    + "nama LIKE '%" + keyword + "%' OR "
                    + "harga = '" + keyword + "' OR "
                    + "quota = '" + keyword + "' OR "
                    + "jumlah_jam_per_quota = '" + keyword + "' OR "
                    + "expired = '" + keyword + "'");

            int i = 1;

            while (resultSet.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(resultSet.getInt("id"));
                voucher.setNama(resultSet.getString("nama"));
                voucher.setHarga(resultSet.getInt("harga"));
                voucher.setQuota(resultSet.getInt("quota"));
                voucher.setJumlah_jam_per_quota(resultSet.getInt("jumlah_jam_per_quota"));
                voucher.setExpired(resultSet.getInt("expired"));
                tableData.add(voucher);
                i++;
            }

            koneksi.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            Logger.getLogger(VoucherModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update voucher set nama = ?, harga = ?, quota = ?, jumlah_jam_per_quota = ?, expired = ? where id = ? ");
            con.preparedStatement.setString(1, getData().getNama());
            con.preparedStatement.setInt(2, getData().getHarga());
            con.preparedStatement.setInt(3, getData().getQuota());
            con.preparedStatement.setInt(4, getData().getJumlah_jam_per_quota());
            con.preparedStatement.setInt(5, getData().getExpired());
            con.preparedStatement.setInt(6, getData().getId());
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

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into voucher (nama, harga, quota, jumlah_jam_per_quota, expired) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getData().getNama());
            con.preparedStatement.setInt(2, getData().getHarga());
            con.preparedStatement.setInt(3, getData().getQuota());
            con.preparedStatement.setInt(4, getData().getJumlah_jam_per_quota());
            con.preparedStatement.setInt(5, getData().getExpired());
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

    public boolean delete(int id) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from voucher where id  = ? ");
            con.preparedStatement.setInt(1, id);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public void cetakReport() {
        Koneksi con = new Koneksi();
        String is = "./src/vouchergym/report_voucher.jasper";
        Map map = new HashMap();
        map.put("judul", "Report Data Voucher");
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
