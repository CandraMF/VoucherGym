/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vouchergym;

/**
 *
 * @author candra
 */
public class VoucherMember {
    private int id, id_member, id_voucher, jumlah_penggunaan, due;
    private String tanggal_pembelian;
    private Member member;
    private Voucher voucher;

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public int getJumlah_penggunaan() {
        return jumlah_penggunaan;
    }

    public void setJumlah_penggunaan(int jumlah_penggunaan) {
        this.jumlah_penggunaan = jumlah_penggunaan;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_member() {
        return id_member;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }

    public int getId_voucher() {
        return id_voucher;
    }

    public void setId_voucher(int id_voucher) {
        this.id_voucher = id_voucher;
    }

    public String getTanggal_pembelian() {
        return tanggal_pembelian;
    }

    public void setTanggal_pembelian(String tanggal_pembelian) {
        this.tanggal_pembelian = tanggal_pembelian;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    
    
   
}
