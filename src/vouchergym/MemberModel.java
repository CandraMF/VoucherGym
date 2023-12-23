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
import vouchergym.Member;
import vouchergym.Koneksi;

/**
 *
 * @author candra
 */
public class MemberModel {

    private Member data = new Member();

    public Member getData() {
        return data;
    }

    public void setData(Member data) {
        this.data = data;
    }

    public ObservableList<Member> load(String keyword) {
        try {
            ObservableList<Member> tableData = FXCollections.observableArrayList();
            Koneksi koneksi = new Koneksi();
            koneksi.bukaKoneksi();
            koneksi.statement = koneksi.dbKoneksi.createStatement();
            ResultSet resultSet = koneksi.statement.executeQuery("select id, nama, no_telp, tanggal_daftar, jenis_member from member "
                    + "WHERE "
                    + "nama LIKE '%" + keyword + "%' OR "
                    + "no_telp LIKE '%" + keyword + "%' OR "
                    + "jenis_member = '" + keyword + "'");

            int i = 1;

            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setNama(resultSet.getString("nama"));
                member.setNo_telp(resultSet.getString("no_telp"));
                member.setTanggal_daftar(resultSet.getString("tanggal_daftar"));
                member.setJenis_member(resultSet.getInt("jenis_member"));
                tableData.add(member);
                i++;
            }

            koneksi.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            Logger.getLogger(MemberModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update member set nama = ?, no_telp = ?, jenis_member = ? where id = ? ");
            con.preparedStatement.setString(1, getData().getNama());
            con.preparedStatement.setString(2, getData().getNo_telp());
            con.preparedStatement.setInt(3, getData().getJenis_member());
            con.preparedStatement.setInt(4, getData().getId());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into member (nama, no_telp, tanggal_daftar, jenis_member) values (?,?,NOW(),?)");
            con.preparedStatement.setString(1, getData().getNama());
            con.preparedStatement.setString(2, getData().getNo_telp());
            con.preparedStatement.setInt(3, getData().getJenis_member());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from member where id  = ? ");
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
        String is = "./src/vouchergym/report_member.jasper";
        Map map = new HashMap();
        map.put("judul", "Report Data Member");
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
