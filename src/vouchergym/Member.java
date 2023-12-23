/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vouchergym;

/**
 *
 * @author candra
 */
public class Member {
    private int id, jenis_member;
    private String nama, no_telp, tanggal_daftar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJenis_member() {
        return jenis_member;
    }

    public void setJenis_member(int jenis_member) {
        this.jenis_member = jenis_member;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getTanggal_daftar() {
        return tanggal_daftar;
    }

    public void setTanggal_daftar(String tanggal_daftar) {
        this.tanggal_daftar = tanggal_daftar;
    }
}
