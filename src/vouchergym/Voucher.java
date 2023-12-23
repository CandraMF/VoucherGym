/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vouchergym;

/**
 *
 * @author candra
 */
public class Voucher {
    private int id, harga, quota, jumlah_jam_per_quota, expired;
    private String nama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getJumlah_jam_per_quota() {
        return jumlah_jam_per_quota;
    }

    public void setJumlah_jam_per_quota(int jumlah_jam_per_quota) {
        this.jumlah_jam_per_quota = jumlah_jam_per_quota;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

   
}
