/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.getConnection;
import DungChung.DungChung;
import Model.SanBay;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import Model.TuyenBay;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TuanDuc
 */
public class TuyenBayDAO extends getConnection {
    public ArrayList<SanBay> layDS() {
        ArrayList<SanBay> arr = new ArrayList<>();
        try {
            String sql = "select MaSanBay, TenSanBay from SANBAY";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanBay sb = new SanBay(rs.getString(1), rs.getString(2));
                arr.add(sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
    public void loadTable(JTable tbl) {
        try {
            String[] header = new String[]{"STT", "Mã tuyến bay", "Mã sân bay đi", "Mã sân bay đến"};
            String sql = "select ROW_NUMBER() Over (Order by MaTuyenBay), * from TUYENBAY";
            new DungChung().statement(sql, tbl, header);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int them(TuyenBay tb) {
        try {
            String sql = "insert into TUYENBAY values(?, ?, ?)";
            Object[] obj = new Object[]{tb.getMaTuyenBay(), tb.getMaSanBayDi(), tb.getMaSanBayDen()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int sua(TuyenBay tb) {
        try {
            String sql = "update TUYENBAY set MaSanBayDi = ?, MaSanBayDen = ? where MaTuyenBay = ?";
            Object[] obj = new Object[]{tb.getMaSanBayDi(), tb.getMaSanBayDen(), tb.getMaTuyenBay()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoa(TuyenBay tb) {
        try {
            String sql = "delete from TUYENBAY where MaTuyenBay = ?";
            Object[] obj = new Object[]{tb.getMaTuyenBay()};
            PreparedStatement ps = new DungChung().prepareStatement(sql, obj);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void hienThi(JTable tbl, TuyenBay tb, int q) {
        tb.setMaTuyenBay(String.valueOf(tbl.getValueAt(q, 1)));
        tb.setMaSanBayDi(String.valueOf(tbl.getValueAt(q, 2)));
        tb.setMaSanBayDen(String.valueOf(tbl.getValueAt(q, 3)));
    }
    
    public TuyenBay timTBToDen(String ma) {
        try {
            String sql = "select * from TUYENBAY where MaTuyenBay = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TuyenBay tb = new TuyenBay(rs.getString(1));
                return tb;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public void tim(JTable tbl, String ma) {
        try {
            String sql = "select ROW_NUMBER() Over (Order by MaTuyenBay), * from TUYENBAY where MaTuyenBay like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            String[] header = new String[]{"STT", "Mã tuyến bay", "Mã sân bay đi", "Mã sân bay đến"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                model.addRow(data);
            }
            tbl.removeAll();
            tbl.setModel(model);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
