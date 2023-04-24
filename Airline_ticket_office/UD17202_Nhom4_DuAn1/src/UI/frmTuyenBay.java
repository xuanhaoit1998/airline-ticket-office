/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.TuyenBayDAO;
import DungChung.DungChung;
import DungChung.ExportExcel;
import DungChung.ExportPDF;
import Model.SanBay;
import Model.TuyenBay;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author TuanDuc
 */
public class frmTuyenBay extends javax.swing.JFrame {

    String chucVu = "";
    int showHide_MaSBDi = 1;
    int showHide_MaSBDen = 1;
    int dong = -1;

    private void cbx_MaSBDi() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<SanBay> arr_SB = new TuyenBayDAO().layDS();
            for (int i = 0; i < arr_SB.size(); i++) {
                JLabel lbl = new JLabel(arr_SB.get(i).getMaSanBay() + " - " + arr_SB.get(i).getTenSanbay());
                lbl.setSize(pnlMaSanBayDi.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_SB.get(i).getMaSanBay());
                lbl.setForeground(Color.white);
                lbl.setFont(new Font("Segoe UI", 0, 14));
                lbl.setCursor(new Cursor(HAND_CURSOR));
                lbl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                txtMaSanBayDi.setText(listLBL.get(i).getText());
                                pnlMaSanBayDi.setVisible(false);
                                showHide_MaSBDi++;
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(new Color(55, 38, 91));
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(Color.white);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });
                pnlMaSanBayDi.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlMaSanBayDi.setPreferredSize(new Dimension(sptMaSanBayDi.getWidth(), listLBL.size() * 20 + 7));
            pnlMaSanBayDi.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cbx_MaSBDen() {
        ArrayList<JLabel> listLBL = new ArrayList<>();
        try {
            int cao = 3;
            ArrayList<SanBay> arr_HMB = new TuyenBayDAO().layDS();
            for (int i = 0; i < arr_HMB.size(); i++) {
                JLabel lbl = new JLabel(arr_HMB.get(i).getMaSanBay() + " - " + arr_HMB.get(i).getTenSanbay());
                lbl.setSize(pnlMaSanBayDen.getWidth(), 20);
                lbl.setLocation(10, cao);
                lbl.setName("lbl" + arr_HMB.get(i).getMaSanBay());
                lbl.setForeground(Color.white);
                lbl.setFont(new Font("Segoe UI", 0, 14));
                lbl.setCursor(new Cursor(HAND_CURSOR));
                lbl.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                txtMaSanBayDen.setText(listLBL.get(i).getText());
                                pnlMaSanBayDen.setVisible(false);
                                showHide_MaSBDen++;
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(new Color(55, 38, 91));
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        String[] s = e.toString().split(" ");
                        String str = s[s.length - 1];
                        for (int i = 0; i < listLBL.size(); i++) {
                            if (listLBL.get(i).getName().equals(str)) {
                                listLBL.get(i).setForeground(Color.white);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });
                pnlMaSanBayDen.add(lbl);
                listLBL.add(lbl);
                cao += 20;
            }
            pnlMaSanBayDen.setPreferredSize(new Dimension(sptMaSanBayDen.getWidth(), listLBL.size() * 20 + 7));
            pnlMaSanBayDen.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHideCBX_MaSBDi() {
        showHide_MaSBDi++;
        if (showHide_MaSBDi % 2 == 0) {
            pnlMaSanBayDi.setVisible(true);
        } else {
            pnlMaSanBayDi.setVisible(false);
        }
    }

    private void showHideCBX_MaSBDen() {
        showHide_MaSBDen++;
        if (showHide_MaSBDen % 2 == 0) {
            pnlMaSanBayDen.setVisible(true);
        } else {
            pnlMaSanBayDen.setVisible(false);
        }
    }

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMa, lblLoiSBDi, lblLoiSBDen});
    }

    private boolean check() {
        if (txtMaTuyenBay.getText().trim().isEmpty()) {
            lblLoiMa.setVisible(true);
            txtMaTuyenBay.requestFocus();
            return false;
        }
        if (txtMaSanBayDi.getText().trim().isEmpty()) {
            lblLoiSBDi.setVisible(true);
            txtMaSanBayDi.requestFocus();
            return false;
        }
        if (txtMaSanBayDen.getText().trim().isEmpty()) {
            lblLoiSBDen.setVisible(true);
            txtMaSanBayDen.requestFocus();
            return false;
        }
        if(txtMaSanBayDi.getText().trim().equals(txtMaSanBayDen.getText().trim())){
            JOptionPane.showMessageDialog(this, "Sân bay đi và sân bay đến không được trùng nhau!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaTuyenBay, txtMaSanBayDi, txtMaSanBayDen});
        an();
        lblSTT.setText("0");
        dong = -1;
    }

    private void bang() {
        new TuyenBayDAO().loadTable(tblTuyenBay);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 300, 300, 300};
        new DungChung().editColumnWidth(col, tblTuyenBay);
    }

    private void them() {
        if (check()) {
            String ma = txtMaTuyenBay.getText().trim();
            String[] str_sbdi = txtMaSanBayDi.getText().trim().split("-");
            String[] str_sbden = txtMaSanBayDen.getText().trim().split("-");
            String sbdi = str_sbdi[0].trim();
            String sbden = str_sbden[0].trim();
            int kt = new TuyenBayDAO().them(new TuyenBay(ma, sbdi, sbden));
            if (kt == 1) {
                bang();
                TuyenBay hmb = new TuyenBayDAO().timTBToDen(ma);
                for (int i = 0; i < tblTuyenBay.getRowCount(); i++) {
                    String id = String.valueOf(tblTuyenBay.getValueAt(i, 1));
                    if (id.equals(hmb.getMaTuyenBay())) {
                        tblTuyenBay.setRowSelectionInterval(i, i);
                    }
                }
                moi();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại! Trùng khóa chính.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        editColumnWidth();
    }

    private void capNhat() {
        if (!txtMaTuyenBay.getText().isEmpty()) {
            String ma = txtMaTuyenBay.getText().trim();
            String[] str_sbdi = txtMaSanBayDi.getText().trim().split("-");
            String[] str_sbden = txtMaSanBayDen.getText().trim().split("-");
            String sbdi = str_sbdi[0].trim();
            String sbden = str_sbden[0].trim();
            int kt = new TuyenBayDAO().sua(new TuyenBay(ma, sbdi, sbden));
            if (kt == 1) {
                bang();
                tblTuyenBay.setRowSelectionInterval(dong, dong);
                an();
                editColumnWidth();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa tuyến bay " + tblTuyenBay.getValueAt(dong, 1) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaTuyenBay.getText().trim();
                int kt = new TuyenBayDAO().xoa(new TuyenBay(ma));
                if (kt == 1) {
                    bang();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        editColumnWidth();
    }

    private void hienThi(int row) {
        TuyenBay tb = new TuyenBay();
        new TuyenBayDAO().hienThi(tblTuyenBay, tb, row);
        txtMaTuyenBay.setText(tb.getMaTuyenBay());
        txtMaSanBayDi.setText(tb.getMaSanBayDi());
        txtMaSanBayDen.setText(tb.getMaSanBayDen());
        txtMaTuyenBay.setEditable(false);
        lblSTT.setText(String.valueOf(tblTuyenBay.getValueAt(dong, 0)));
    }

    private void exportExcel() {
        new ExportExcel().exportExcel("DANH SÁCH BẢNG TUYẾN BAY", "Tuyến bay", tblTuyenBay, new int[]{1500, 5000, 5000, 5000});
        JOptionPane.showMessageDialog(this, "Xuất file excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportPDF() {
        new ExportPDF().exportPDF("DANH SÁCH BẢNG TUYẾN BAY", tblTuyenBay);
        JOptionPane.showMessageDialog(this, "Xuất file pdf thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void chucNang(String str) {
        try {
            if (str.equals("dau")) {
                dong = 0;
            } else if (str.equals("cuoi")) {
                dong = tblTuyenBay.getRowCount() - 1;
            } else if (str.equals("pre")) {
                dong--;
                if (dong < 0) {
                    JOptionPane.showMessageDialog(this, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (str.equals("next")) {
                dong++;
                if (dong >= tblTuyenBay.getRowCount()) {
                    JOptionPane.showMessageDialog(this, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }

            tblTuyenBay.setRowSelectionInterval(dong, dong);
            lblSTT.setText(String.valueOf(tblTuyenBay.getValueAt(dong, 0)));
            hienThi(dong);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public frmTuyenBay() {
        initComponents();
        setLocationRelativeTo(this);
        cbx_MaSBDi();
        cbx_MaSBDen();
        new DungChung().transTXT(new JTextField[]{txtMaTuyenBay, txtMaSanBayDi, txtMaSanBayDen, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    public frmTuyenBay(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        cbx_MaSBDi();
        cbx_MaSBDen();
        new DungChung().transTXT(new JTextField[]{txtMaTuyenBay, txtMaSanBayDi, txtMaSanBayDen, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        chucVu = vt;
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        pnlMaSanBayDi = new javax.swing.JPanel();
        pnlMaSanBayDen = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMaTuyenBay = new javax.swing.JTextField();
        lblLoiMa = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaSanBayDi = new javax.swing.JTextField();
        sptMaSanBayDi = new javax.swing.JSeparator();
        lblLoiSBDi = new javax.swing.JLabel();
        splTable = new javax.swing.JScrollPane();
        tblTuyenBay = new javax.swing.JTable();
        lblSTT = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaSanBayDen = new javax.swing.JTextField();
        sptMaSanBayDen = new javax.swing.JSeparator();
        lblLoiSBDen = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnCBXMaSBDen = new javax.swing.JButton();
        btnCBXMaSBDi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMaSanBayDi.setBackground(new java.awt.Color(194, 194, 194));

        javax.swing.GroupLayout pnlMaSanBayDiLayout = new javax.swing.GroupLayout(pnlMaSanBayDi);
        pnlMaSanBayDi.setLayout(pnlMaSanBayDiLayout);
        pnlMaSanBayDiLayout.setHorizontalGroup(
            pnlMaSanBayDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlMaSanBayDiLayout.setVerticalGroup(
            pnlMaSanBayDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel5.add(pnlMaSanBayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        pnlMaSanBayDen.setBackground(new java.awt.Color(194, 194, 194));

        javax.swing.GroupLayout pnlMaSanBayDenLayout = new javax.swing.GroupLayout(pnlMaSanBayDen);
        pnlMaSanBayDen.setLayout(pnlMaSanBayDenLayout);
        pnlMaSanBayDenLayout.setHorizontalGroup(
            pnlMaSanBayDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        pnlMaSanBayDenLayout.setVerticalGroup(
            pnlMaSanBayDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel5.add(pnlMaSanBayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(55, 38, 91));
        jLabel9.setText("QUẢN LÝ TUYẾN BAY");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(55, 38, 91));
        jLabel10.setText("Tìm kiếm");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.setOpaque(false);
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel5.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        txtMaTuyenBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaTuyenBay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaTuyenBay.setOpaque(false);
        txtMaTuyenBay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaTuyenBayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaTuyenBayKeyTyped(evt);
            }
        });
        jPanel5.add(txtMaTuyenBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiMa.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMa.setText("Mã tuyến bay không chinh xác");
        jPanel5.add(lblLoiMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(55, 38, 91));
        jLabel11.setText("Mã tuyến bay");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(55, 38, 91));
        jLabel12.setText("Mã sân bay đi");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        txtMaSanBayDi.setEditable(false);
        txtMaSanBayDi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSanBayDi.setBorder(null);
        txtMaSanBayDi.setOpaque(false);
        jPanel5.add(txtMaSanBayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 360, -1));
        jPanel5.add(sptMaSanBayDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 390, 10));

        lblLoiSBDi.setBackground(new java.awt.Color(194, 194, 194));
        lblLoiSBDi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiSBDi.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiSBDi.setText("Mã tuyến bay đi không chính xác");
        jPanel5.add(lblLoiSBDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, -1));

        tblTuyenBay.setAutoCreateRowSorter(true);
        tblTuyenBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTuyenBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã tuyến bay", "Mã sân bay đi", "Mã sân bay đến"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTuyenBay.setAutoscrolls(false);
        tblTuyenBay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblTuyenBay.setFocusable(false);
        tblTuyenBay.setGridColor(new java.awt.Color(255, 255, 255));
        tblTuyenBay.setOpaque(false);
        tblTuyenBay.setRequestFocusEnabled(false);
        tblTuyenBay.setRowHeight(30);
        tblTuyenBay.setRowMargin(0);
        tblTuyenBay.setSelectionBackground(new java.awt.Color(55, 38, 91));
        tblTuyenBay.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTuyenBay.setShowHorizontalLines(false);
        tblTuyenBay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTuyenBayMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblTuyenBay);

        jPanel5.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 930, 200));

        lblSTT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSTT.setForeground(new java.awt.Color(55, 38, 91));
        lblSTT.setText("0");
        jPanel5.add(lblSTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(55, 38, 91));
        jLabel13.setText("Mã sân bay đến");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtMaSanBayDen.setEditable(false);
        txtMaSanBayDen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSanBayDen.setBorder(null);
        txtMaSanBayDen.setOpaque(false);
        txtMaSanBayDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSanBayDenKeyReleased(evt);
            }
        });
        jPanel5.add(txtMaSanBayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 330, -1));
        jPanel5.add(sptMaSanBayDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 360, 10));

        lblLoiSBDen.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiSBDen.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiSBDen.setText("Mã tuyến bay đến không chính xác");
        jPanel5.add(lblLoiSBDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 190, -1));

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgButtonThem.png"))); // NOI18N
        btnThem.setBorder(null);
        btnThem.setBorderPainted(false);
        btnThem.setContentAreaFilled(false);
        btnThem.setDefaultCapable(false);
        btnThem.setFocusPainted(false);
        btnThem.setFocusable(false);
        btnThem.setIconTextGap(0);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemMouseExited(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgButtonCapNhat.png"))); // NOI18N
        btnCapNhat.setBorder(null);
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setContentAreaFilled(false);
        btnCapNhat.setDefaultCapable(false);
        btnCapNhat.setFocusPainted(false);
        btnCapNhat.setFocusable(false);
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseExited(evt);
            }
        });
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgButtonXoa.png"))); // NOI18N
        btnXoa.setBorder(null);
        btnXoa.setBorderPainted(false);
        btnXoa.setContentAreaFilled(false);
        btnXoa.setDefaultCapable(false);
        btnXoa.setFocusPainted(false);
        btnXoa.setFocusable(false);
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaMouseExited(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, -1));

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgButtonMoi.png"))); // NOI18N
        btnMoi.setBorder(null);
        btnMoi.setBorderPainted(false);
        btnMoi.setContentAreaFilled(false);
        btnMoi.setDefaultCapable(false);
        btnMoi.setFocusPainted(false);
        btnMoi.setFocusable(false);
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMoiMouseExited(evt);
            }
        });
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel5.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, -1, -1));

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgButtonExcel.png"))); // NOI18N
        btnExcel.setBorder(null);
        btnExcel.setBorderPainted(false);
        btnExcel.setContentAreaFilled(false);
        btnExcel.setFocusPainted(false);
        btnExcel.setFocusable(false);
        btnExcel.setIconTextGap(0);
        btnExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExcelMouseExited(evt);
            }
        });
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        jPanel5.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, -1, -1));

        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgButtonFPT.png"))); // NOI18N
        btnPDF.setBorder(null);
        btnPDF.setBorderPainted(false);
        btnPDF.setContentAreaFilled(false);
        btnPDF.setFocusPainted(false);
        btnPDF.setFocusable(false);
        btnPDF.setIconTextGap(0);
        btnPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPDFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPDFMouseExited(evt);
            }
        });
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        jPanel5.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 540, -1, -1));

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgDau.png"))); // NOI18N
        btnPre.setBorder(null);
        btnPre.setBorderPainted(false);
        btnPre.setContentAreaFilled(false);
        btnPre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPre.setDefaultCapable(false);
        btnPre.setFocusPainted(false);
        btnPre.setFocusable(false);
        btnPre.setIconTextGap(0);
        btnPre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPreMouseExited(evt);
            }
        });
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        jPanel5.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, -1, -1));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgPre.png"))); // NOI18N
        btnFirst.setBorder(null);
        btnFirst.setBorderPainted(false);
        btnFirst.setContentAreaFilled(false);
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.setDefaultCapable(false);
        btnFirst.setFocusPainted(false);
        btnFirst.setFocusable(false);
        btnFirst.setIconTextGap(0);
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFirstMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFirstMouseExited(evt);
            }
        });
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel5.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, -1, -1));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgNext.png"))); // NOI18N
        btnLast.setBorder(null);
        btnLast.setBorderPainted(false);
        btnLast.setContentAreaFilled(false);
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.setDefaultCapable(false);
        btnLast.setFocusPainted(false);
        btnLast.setFocusable(false);
        btnLast.setIconTextGap(0);
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLastMouseExited(evt);
            }
        });
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel5.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, -1, -1));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/bgCuoi.png"))); // NOI18N
        btnNext.setBorder(null);
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setDefaultCapable(false);
        btnNext.setFocusPainted(false);
        btnNext.setFocusable(false);
        btnNext.setIconTextGap(0);
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNextMouseExited(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel5.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, -1, -1));

        btnCBXMaSBDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnCBXMaSBDen.setBorder(null);
        btnCBXMaSBDen.setBorderPainted(false);
        btnCBXMaSBDen.setContentAreaFilled(false);
        btnCBXMaSBDen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCBXMaSBDen.setFocusPainted(false);
        btnCBXMaSBDen.setFocusable(false);
        btnCBXMaSBDen.setIconTextGap(0);
        btnCBXMaSBDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCBXMaSBDenActionPerformed(evt);
            }
        });
        jPanel5.add(btnCBXMaSBDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        btnCBXMaSBDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/down.png"))); // NOI18N
        btnCBXMaSBDi.setBorder(null);
        btnCBXMaSBDi.setBorderPainted(false);
        btnCBXMaSBDi.setContentAreaFilled(false);
        btnCBXMaSBDi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCBXMaSBDi.setDefaultCapable(false);
        btnCBXMaSBDi.setFocusPainted(false);
        btnCBXMaSBDi.setFocusable(false);
        btnCBXMaSBDi.setIconTextGap(0);
        btnCBXMaSBDi.setInheritsPopupMenu(true);
        btnCBXMaSBDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCBXMaSBDiActionPerformed(evt);
            }
        });
        jPanel5.add(btnCBXMaSBDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaTuyenBayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaTuyenBayKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTuyenBayKeyReleased

    private void txtMaSanBayDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSanBayDenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanBayDenKeyReleased

    private void tblTuyenBayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTuyenBayMouseClicked
        txtTimKiem.setText(null);
        dong = tblTuyenBay.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblTuyenBayMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new TuyenBayDAO().tim(tblTuyenBay, txtTimKiem.getText().trim());
        editColumnWidth();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        new DungChung().hoverButton5(1, btnThem, "bgButtonThemHover.png");
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
        new DungChung().hoverButton5(2, btnThem, "bgButtonThem.png");
    }//GEN-LAST:event_btnThemMouseExited

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseEntered
        new DungChung().hoverButton5(1, btnCapNhat, "bgButtonCapNhatHover.png");
    }//GEN-LAST:event_btnCapNhatMouseEntered

    private void btnCapNhatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseExited
        new DungChung().hoverButton5(2, btnCapNhat, "bgButtonCapNhat.png");
    }//GEN-LAST:event_btnCapNhatMouseExited

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (tblTuyenBay.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn tuyến bay cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            capNhat();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        new DungChung().hoverButton5(1, btnXoa, "bgButtonXoaHover.png");
    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        new DungChung().hoverButton5(2, btnXoa, "bgButtonXoa.png");
    }//GEN-LAST:event_btnXoaMouseExited

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (chucVu.equals("Trưởng phòng")) {
            xoa();
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ admin được phép xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseEntered
        new DungChung().hoverButton5(1, btnMoi, "bgButtonMoiHover.png");
    }//GEN-LAST:event_btnMoiMouseEntered

    private void btnMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseExited
        new DungChung().hoverButton5(2, btnMoi, "bgButtonMoi.png");
    }//GEN-LAST:event_btnMoiMouseExited

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        moi();
        tblTuyenBay.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseEntered
        new DungChung().hoverButton5(1, btnExcel, "bgButtonExcelHover.png");
    }//GEN-LAST:event_btnExcelMouseEntered

    private void btnExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseExited
        new DungChung().hoverButton5(2, btnExcel, "bgButtonExcel.png");
    }//GEN-LAST:event_btnExcelMouseExited

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportExcel();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnPDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseEntered
        new DungChung().hoverButton5(1, btnPDF, "bgButtonFPTHover.png");
    }//GEN-LAST:event_btnPDFMouseEntered

    private void btnPDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseExited
        new DungChung().hoverButton5(2, btnPDF, "bgButtonFPT.png");
    }//GEN-LAST:event_btnPDFMouseExited

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        exportPDF();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnPreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreMouseEntered
        new DungChung().hoverButton5(1, btnPre, "bgDauHover.png");
    }//GEN-LAST:event_btnPreMouseEntered

    private void btnPreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreMouseExited
        new DungChung().hoverButton5(2, btnPre, "bgDau.png");
    }//GEN-LAST:event_btnPreMouseExited

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        chucNang("dau");
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFirstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseEntered
        new DungChung().hoverButton5(1, btnFirst, "bgPreHover.png");
    }//GEN-LAST:event_btnFirstMouseEntered

    private void btnFirstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseExited
        new DungChung().hoverButton5(2, btnFirst, "bgPre.png");
    }//GEN-LAST:event_btnFirstMouseExited

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        chucNang("pre");
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseEntered
        new DungChung().hoverButton5(1, btnLast, "bgNextHover.png");
    }//GEN-LAST:event_btnLastMouseEntered

    private void btnLastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseExited
        new DungChung().hoverButton5(2, btnLast, "bgNext.png");
    }//GEN-LAST:event_btnLastMouseExited

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        chucNang("next");
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered
        new DungChung().hoverButton5(1, btnNext, "bgCuoiHover.png");
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited
        new DungChung().hoverButton5(2, btnNext, "bgCuoi.png");
    }//GEN-LAST:event_btnNextMouseExited

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        chucNang("cuoi");
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtMaTuyenBayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaTuyenBayKeyTyped
        if (txtMaTuyenBay.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaTuyenBayKeyTyped

    private void btnCBXMaSBDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCBXMaSBDenActionPerformed
        showHideCBX_MaSBDen();
        pnlMaSanBayDi.setVisible(false);
    }//GEN-LAST:event_btnCBXMaSBDenActionPerformed

    private void btnCBXMaSBDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCBXMaSBDiActionPerformed
        showHideCBX_MaSBDi();
        pnlMaSanBayDen.setVisible(false);
    }//GEN-LAST:event_btnCBXMaSBDiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTuyenBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTuyenBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTuyenBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTuyenBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTuyenBay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCBXMaSBDen;
    private javax.swing.JButton btnCBXMaSBDi;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblLoiMa;
    private javax.swing.JLabel lblLoiSBDen;
    private javax.swing.JLabel lblLoiSBDi;
    private javax.swing.JLabel lblSTT;
    private javax.swing.JPanel pnlMaSanBayDen;
    private javax.swing.JPanel pnlMaSanBayDi;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JSeparator sptMaSanBayDen;
    private javax.swing.JSeparator sptMaSanBayDi;
    private javax.swing.JTable tblTuyenBay;
    private javax.swing.JTextField txtMaSanBayDen;
    private javax.swing.JTextField txtMaSanBayDi;
    private javax.swing.JTextField txtMaTuyenBay;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
