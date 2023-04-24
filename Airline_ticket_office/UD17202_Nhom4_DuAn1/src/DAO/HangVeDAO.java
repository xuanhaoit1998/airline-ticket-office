/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.getConnection;
import DungChung.DungChung;
import Model.HangVe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TuanDuc
 */
public class HangVeDAO extends getConnection {

    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã hạng vé", "Tên hạng vé"};
            String sql = "select ROW_NUMBER() Over (Order by MaHangVe), * from HANGVE";
            new DungChung().statement(sql, tbl, header);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public int them(HangVe hv) {
        try {
            String sql = "insert into HANGVE values(?, ?)";
            Object[] obj = new Object[]{hv.getMaHangVe(), hv.getTenHangVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int sua(HangVe hv) {
        try {
            String sql = "update HANGVE set TenHangVe = ? where MaHangVe = ?";
            Object[] obj = new Object[]{hv.getTenHangVe(), hv.getMaHangVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int xoa(HangVe hv) {
        try {
            String sql = "delete from HANGVE where MaHangVe = ?";
            Object[] obj = new Object[]{hv.getMaHangVe()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, HangVe hv, int q) {
        hv.setMaHangVe(String.valueOf(tbl.getValueAt(q, 1)));
        hv.setTenHangVe(String.valueOf(tbl.getValueAt(q, 2)));
    }
    
    public HangVe timHVToDen(String cmnd) {
        try {
            String sql = "select * from HANGVE where MaHangVe = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cmnd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HangVe lv = new HangVe(rs.getString(1));
                return lv;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaHangVe), * from HANGVE where MaHangVe like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã hạng vé", "Tên hạng vé"};
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
