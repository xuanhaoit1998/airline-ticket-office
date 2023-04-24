/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author TuanDuc
 */
public class HangMayBay implements Serializable{
    private String maHang;
    private String tenHang;
    private String quocGia;

    public HangMayBay() {
    }

    public HangMayBay(String maHang) {
        this.maHang = maHang;
    }

    public HangMayBay(String maHang, String tenHang) {
        this.maHang = maHang;
        this.tenHang = tenHang;
    }

    public HangMayBay(String maHang, String tenHang, String quocGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.quocGia = quocGia;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }
}
