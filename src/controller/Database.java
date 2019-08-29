/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;


/**
 *
 * @author Brama Hendra Mahendra
 */
public class Database {
    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;
    private ArrayList<Buku> buku = new ArrayList<>();
    
    public Database(){
        loadBuku();
    }
    
    public void connect() {
        try {
            String url = "jdbc:mysql://localhost/db_project_java_00015";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            stat = conn.createStatement();
        } catch (Exception ex) {    
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void disconnect(){
        try {
            conn.close();
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int rows = stat.executeUpdate(query);
            if (rows > 0) cek = true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cek;
    }
    
    public void loadBuku() {
        connect();
        try {
            String query = "SELECT * FROM buku";
            rs = stat.executeQuery(query);
            while (rs.next()){
                buku.add(new Buku(rs.getString("nim"), rs.getString("nama"), rs.getString("jurusan"), rs.getInt("jk")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    
    public ArrayList<Buku> getBuku() {
        return buku;
    }
    
    public void addBuku(Buku b) {
        connect();
        String query = "INSERT INTO buku VALUES (";
        query += "'" + b.getKode_buku() + "',";
        query += "'" + b.getJudul() + "',";
        query += "'" + b.getPenerbit() + "',";
        query += "'" + b.getHarga() + "'";
        query += ")";
        if (manipulate(query)) buku.add(b);
        disconnect();
    }
     
    public boolean cekDuplikatKodeBuku(String kode_buku){
        boolean cek = false;
        for (Buku b : buku) {
            if (b.getKode_buku().equals(kode_buku)){
                cek = true;
                break;
            }
        }
        return cek;
    }
    
    public void delBuku(String kode_buku) {
        connect();
        String query = "DELETE FROM buku WHERE kode_buku='" + kode_buku + "'";
        if (manipulate(query)){
            for (Buku b : buku) {
                if (b.getKode_buku().equals(kode_buku)){
                    buku.remove(b);
                    break;
                }
            }
        }
        disconnect();
    }
    
    public void updateMahasiswa(Buku b) {
        connect();
        String query = "UPDATE buku SET";
        query += " judul='" + b.getJudul() + "',";
        query += " penerbit='" + b.getPenerbit() + "',";
        query += " harga='" + b.getHarga() + "'";
        query += " WHERE kode_buku='" + b.getKode_buku() + "'";
        if (manipulate(query)){
            for (Buku book : buku) {
                if (book.getKode_buku().equals(b.getKode_buku())){
                    book.setJudul(b.getJudul());
                    book.setPenerbit(b.getPenerbit());
                    book.setHarga(b.getHarga());
                    break;
                }
            }
        }
        disconnect();
    }
}
