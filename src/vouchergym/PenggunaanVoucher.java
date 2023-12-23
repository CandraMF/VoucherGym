/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vouchergym;

/**
 *
 * @author candra
 */
public class PenggunaanVoucher {
    private int id_voucher_member;
    private String tanggal_masuk;
    private VoucherMember voucherMember;

    public VoucherMember getVoucherMember() {
        return voucherMember;
    }

    public void setVoucherMember(VoucherMember voucherMember) {
        this.voucherMember = voucherMember;
    }

    public int getId_voucher_member() {
        return id_voucher_member;
    }

    public void setId_voucher_member(int id_voucher_member) {
        this.id_voucher_member = id_voucher_member;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }
}
