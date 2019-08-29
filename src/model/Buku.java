/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Brama Hendra Mahendra
 */
public class Buku {
    private String kode_buku;
    private String judul;
    private String penerbit;
    private int harga;
    
    public Buku(String kode_buku, String judul, String penerbit, int harga) {
        this.kode_buku = kode_buku;
        this.judul = judul;
        this.penerbit = penerbit;
        this.harga = harga;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    
    
}
