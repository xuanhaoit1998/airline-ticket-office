/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.getConnection;
import DungChung.DungChung;
import Model.ChuyenBay;
import Model.HangVe;
import Model.LoaiVe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import Model.VeMayBay;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TuanDuc
 */
public class VeMayBayDAO extends getConnection{
    public ArrayList<HangVe> layDS_HV(){
        ArrayList<HangVe> arr = new ArrayList<>();
        try {
            String sql = "select MaHangVe, TenHangVe from HANGVE";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                HangVe hv = new HangVe(rs.getString(1), rs.getString(2));
                arr.add(hv);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return arr;
    }
    
    public ArrayList<LoaiVe> layDS_LV(){
        ArrayList<LoaiVe> arr = new ArrayList<>();
        try {
            String sql = "select MaLoaiVe, TenLoaiVe from LOAIVE";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                LoaiVe lv = new LoaiVe(rs.getString(1), rs.getString(2));
                arr.add(lv);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return arr;
    }
    
    public ArrayList<ChuyenBay> layDS_CB(){
        ArrayList<ChuyenBay> arr = new ArrayList<>();
        try {
            String sql = "select MaChuyenBay from CHUYENBAY";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ChuyenBay cb = new ChuyenBay(rs.getString(1));
                arr.add(cb);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return arr;
    }
    
    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã vé", "Mã hạng vé", "Mã loại vé", "Mã chuyến bay", "Giá bán (triệu)"};
            String sql = "select ROW_NUMBER() Over (Order by MaVe), * from VEMAYBAY";
            new DungChung().statement(sql, tbl, header);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public int them(VeMayBay vmb) {
        try {
            String sql = "insert into VEMAYBAY values(?, ?, ?, ?, ?)";
            Object[] obj = new Object[]{vmb.getMaVe(), vmb.getMaHangVe(), vmb.getMaLoaiVe(), vmb.getMaChuyenBay(), vmb.getGiaBan()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int sua(VeMayBay vmb) {
        try {
            String sql = "update VEMAYBAY set MaHangVe = ?, MaLoaiVe = ?, MaChuyenBay = ?, GiaBan = ? where MaVe = ?";
            Object[] obj = new Object[]{vmb.getMaHangVe(), vmb.getMaLoaiVe(), vmb.getMaChuyenBay(), vmb.getGiaBan(), vmb.getMaVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int xoa(VeMayBay vmb) {
        try {
            String sql = "delete from VEMAYBAY where MaVe = ?";
            Object[] obj = new Object[]{vmb.getMaVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, VeMayBay vmb, int q) {
        vmb.setMaVe(String.valueOf(tbl.getValueAt(q, 1)));
        vmb.setMaHangVe(String.valueOf(tbl.getValueAt(q, 2)));
        vmb.setMaLoaiVe(String.valueOf(tbl.getValueAt(q, 3)));
        vmb.setMaChuyenBay(String.valueOf(tbl.getValueAt(q, 4)));
        vmb.setGiaBan(Float.parseFloat(String.valueOf(tbl.getValueAt(q, 5))));
    }
    
    public VeMayBay timDVToDen(String ma) {
        try {
            String sql = "select * from VEMAYBAY where MaVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                VeMayBay sb = new VeMayBay(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaVe), * from VEMAYBAY where MaVe like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã vé", "Mã hạng vé", "Mã loại vé", "Mã chuyến bay", "Giá bán (triệu)"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getFloat(6));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

}
