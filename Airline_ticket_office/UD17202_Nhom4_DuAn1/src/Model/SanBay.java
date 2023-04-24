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
public class SanBay implements Serializable{

    private String maSanBay;
    private String tenSanbay;
    private String quocGia;
    private String diaDiem;

    public SanBay() {
    }

    public SanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    public SanBay(String maSanBay, String tenSanbay) {
        this.maSanBay = maSanBay;
        this.tenSanbay = tenSanbay;
    }

    public SanBay(String maSanBay, String tenSanbay, String quocGia, String diaDiem) {
        this.maSanBay = maSanBay;
        this.tenSanbay = tenSanbay;
        this.quocGia = quocGia;
        this.diaDiem = diaDiem;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    public String getTenSanbay() {
        return tenSanbay;
    }

    public void setTenSanbay(String tenSanbay) {
        this.tenSanbay = tenSanbay;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
