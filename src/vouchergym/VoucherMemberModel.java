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
public class VoucherMemberModel {

    private VoucherMember data = new VoucherMember();

    public VoucherMember getData() {
        return data;
    }

    public void setData(VoucherMember data) {
        this.data = data;
    }

    public ObservableList<VoucherMember> loadByIdMember(int id_member) {
        try {
            ObservableList<VoucherMember> tableData = FXCollections.observableArrayList();
            Koneksi koneksi = new Koneksi();
            koneksi.bukaKoneksi();
            koneksi.statement = koneksi.dbKoneksi.createStatement();

            String query
                    = "SELECT vm.id, vm.tanggal_pembelian, vm.id_member, vm.id_voucher, m.nama as nama_member, m.no_telp, m.tanggal_daftar, m.jenis_member, v.nama as nama_voucher, v.harga, v.quota, v.jumlah_jam_per_quota, v.expired, "
                    + "(SELECT COUNT(*) FROM penggunaan_voucher pv WHERE pv.id_voucher_member = vm.id) AS jumlah_penggunaan, "
                    + "DATEDIFF(DATE_ADD(vm.tanggal_pembelian, INTERVAL v.expired DAY), now()) AS due "
                    + "FROM `voucher_member` vm "
                    + "JOIN voucher v on v.id = vm.id_voucher "
                    + "JOIN member m on m.id = vm.id_member WHERE vm.id_member = " + id_member;

            ResultSet resultSet = koneksi.statement.executeQuery(query);

            int i = 1;

            while (resultSet.next()) {
                VoucherMember voucherMember = new VoucherMember();

                voucherMember.setId(resultSet.getInt("id"));
                voucherMember.setId_member(resultSet.getInt("id_member"));
                voucherMember.setId_voucher(resultSet.getInt("id_voucher"));
                voucherMember.setJumlah_penggunaan(resultSet.getInt("jumlah_penggunaan"));
                voucherMember.setDue(resultSet.getInt("due"));
                voucherMember.setTanggal_pembelian(resultSet.getString("tanggal_pembelian"));

                voucherMember.setMember(new Member());
                voucherMember.getMember().setId(resultSet.getInt("id_member"));
                voucherMember.getMember().setNama(resultSet.getString("nama_member"));
                voucherMember.getMember().setJenis_member(resultSet.getInt("jenis_member"));
                voucherMember.getMember().setNo_telp(resultSet.getString("no_telp"));
                voucherMember.getMember().setTanggal_daftar(resultSet.getString("tanggal_daftar"));

                voucherMember.setVoucher(new Voucher());
                voucherMember.getVoucher().setId(resultSet.getInt("id_voucher"));
                voucherMember.getVoucher().setHarga(resultSet.getInt("harga"));
                voucherMember.getVoucher().setQuota(resultSet.getInt("quota"));
                voucherMember.getVoucher().setNama(resultSet.getString("nama_voucher"));
                voucherMember.getVoucher().setJumlah_jam_per_quota(resultSet.getInt("jumlah_jam_per_quota"));
                voucherMember.getVoucher().setExpired(resultSet.getInt("expired"));

                tableData.add(voucherMember);
                i++;
            }

            koneksi.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            Logger.getLogger(VoucherMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertByIdMember(int id_member) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into voucher_member (id_member, id_voucher, tanggal_pembelian) values (?,?,NOW())");
            con.preparedStatement.setInt(1, id_member);
            con.preparedStatement.setInt(2, getData().getVoucher().getId());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from voucher_member where id  = ? ");
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
        String is = "./src/vouchergym/report_pembelian_voucher.jasper";
        Map map = new HashMap();
        map.put("judul", "Report Data Pembelian Voucher");
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
