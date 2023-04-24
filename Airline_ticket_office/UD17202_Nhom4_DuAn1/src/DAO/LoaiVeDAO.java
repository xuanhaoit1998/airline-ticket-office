/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.getConnection;
import DungChung.DungChung;
import Model.LoaiVe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TuanDuc
 */
public class LoaiVeDAO extends getConnection {

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã loại vé", "Tên loại vé"};
            String sql = "select ROW_NUMBER() Over (Order by MaLoaiVe), * from LOAIVE";
            new DungChung().statement(sql, tbl, header);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int them(LoaiVe lv) {
        try {
            String sql = "insert into LOAIVE values(?, ?)";
            Object[] obj = new Object[]{lv.getMaLoaiVe(), lv.getTenLoaiVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int sua(LoaiVe lv) {
        try {
            String sql = "update LOAIVE set TenLoaiVe = ? where MaLoaiVe = ?";
            Object[] obj = new Object[]{lv.getTenLoaiVe(), lv.getMaLoaiVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int xoa(LoaiVe lv) {
        try {
            String sql = "delete from LOAIVE where MaLoaiVe = ?";
            Object[] obj = new Object[]{lv.getMaLoaiVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, LoaiVe lv, int q) {
        lv.setMaLoaiVe(String.valueOf(tbl.getValueAt(q, 1)));
        lv.setTenLoaiVe(String.valueOf(tbl.getValueAt(q, 2)));
    }

    public LoaiVe timLVToDen(String cmnd) {
        try {
            String sql = "select * from LOAIVE where MaLoaiVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cmnd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LoaiVe lv = new LoaiVe(rs.getString(1));
                return lv;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaLoaiVe), * from LOAIVE where MaLoaiVe like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã loại vé", "Tên loại vé"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
