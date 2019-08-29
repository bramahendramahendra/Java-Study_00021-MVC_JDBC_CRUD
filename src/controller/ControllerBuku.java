/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.ViewBuku;
import model.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Brama Hendra Mahendra
 */
public class ControllerBuku extends MouseAdapter implements ActionListener {
    private ViewBuku view;
    private Database db;

    public ControllerBuku() {
        view = new ViewBuku();
        db = new Database();
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        loadTable();
    }
    
    public void loadTable(){
        DefaultTableModel model = new DefaultTableModel(new String[]{"kode_buku", "judul", "penerbit", "harga"}, 0);
        ArrayList<Buku> buku = db.getBuku();
        for (Buku b : buku) {
            model.addRow(new Object[]{b.getKode_buku(), b.getJudul(), b.getPenerbit(), b.getHarga()});
        }
        view.setJt_buku(model);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getJb_tambah())){
            jb_tambahActionPerformed();
            loadTable   ();
        }
//else if (source.equals(view.getBtnHapus())){
//            btnHapusActionPerformed();
//            loadTable();
//        }else if (source.equals(view.getBtnUbah())){
//            btnUbahActionPerformed();
//            loadTable();
//        }else if (source.equals(view.getBtnReset())){
//            view.reset();
//            loadTable();
//        }else if (source.equals(view.getBtnCari())){
//            btnCariActionPerformed();
//        }    
    }
    
    public void jb_tambahActionPerformed(){
        String kode_buku = view.getKode_buku();
        String judul = view.getJudul();
        String penerbit = view.getPenerbit();
        int harga = Integer.parseInt(view.getHarga());
        
        if (kode_buku.isEmpty() || judul.isEmpty() || penerbit.isEmpty() || harga == 0){
            view.showMessage("Data Kosong", "Error", 0);
        }else{
            if (db.cekDuplikatKodeBuku(kode_buku)){
                view.showMessage("Buku Sudah Ada", "Error", 0);
            }else{
                db.addBuku(new Buku(kode_buku,judul,penerbit,harga));
                view.reset();
                view.showMessage("Data Berhasil Ditambah", "Success", 1);
            }
        }
    }
}
