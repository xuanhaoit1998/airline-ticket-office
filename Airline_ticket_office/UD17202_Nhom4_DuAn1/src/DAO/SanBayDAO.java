/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.getConnection;
import DungChung.DungChung;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import Model.SanBay;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TuanDuc
 */
public class SanBayDAO extends getConnection{
    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã sân bay", "Tên sân bay", "Quốc gia", "Địa điểm"};
            String sql = "select ROW_NUMBER() Over (Order by MaSanBay), * from SANBAY";
            new DungChung().statement(sql, tbl, header);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public int them(SanBay sb) {
        try {
            String sql = "insert into SANBAY values(?, ?, ?, ?)";
            Object[] obj = new Object[]{sb.getMaSanBay(), sb.getTenSanbay(), sb.getQuocGia(), sb.getDiaDiem()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int sua(SanBay sb) {
        try {
            String sql = "update SANBAY set TenSanBay = ?, QuocGia = ?, DiaDiem = ? where MaSanBay = ?";
            Object[] obj = new Object[]{sb.getTenSanbay(), sb.getQuocGia(), sb.getDiaDiem(), sb.getMaSanBay()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public int xoa(SanBay sb) {
        try {
            String sql = "delete from SANBAY where MaSanBay = ?";
            Object[] obj = new Object[]{sb.getMaSanBay()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, SanBay sb, int q) {
        sb.setMaSanBay(String.valueOf(tbl.getValueAt(q, 1)));
        sb.setTenSanbay(String.valueOf(tbl.getValueAt(q, 2)));
        sb.setQuocGia(String.valueOf(tbl.getValueAt(q, 3)));
        sb.setDiaDiem(String.valueOf(tbl.getValueAt(q, 4)));
    }
    
    public SanBay timSBToDen(String ma) {
        try {
            String sql = "select * from SANBAY where MaSanBay = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanBay sb = new SanBay(rs.getString(1));
                return sb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaSanBay), * from SANBAY where MaSanBay like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã sân bay", "Tên sân bay", "Quốc gia", "Địa điểm"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
