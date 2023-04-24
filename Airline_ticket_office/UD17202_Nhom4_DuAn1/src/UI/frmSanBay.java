/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.SanBayDAO;
import DungChung.DungChung;
import DungChung.ExportExcel;
import DungChung.ExportPDF;
import Model.SanBay;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author TuanDuc
 */
public class frmSanBay extends javax.swing.JFrame {
    String chucVu = "";
    int dong = -1;

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaSB, lblLoiTen, lblLoiQuocGia, lblLoiDiaDiem});
    }

    private boolean check() {
        JLabel[] lbl = new JLabel[]{lblLoiMaSB, lblLoiTen, lblLoiQuocGia, lblLoiDiaDiem};
        JTextField[] txt = new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem};
        return new DungChung().check(lbl, txt);
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem});
        an();
        lblSTT.setText("0");
        dong = -1;
    }

    private void bang() {
        new SanBayDAO().loadTable(tblSanBay);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 225, 225, 225, 225};
        new DungChung().editColumnWidth(col, tblSanBay);
    }

    private void them() {
        if (check()) {
            String ma = txtMaSanBay.getText().trim();
            String ten = txtTenSanBay.getText().trim();
            String quocgia = txtQuocGia.getText().trim();
            String diadiem = txtDiaDiem.getText().trim();
            int kt = new SanBayDAO().them(new SanBay(ma, ten, quocgia, diadiem));
            if (kt == 1) {
                bang();
                SanBay sb = new SanBayDAO().timSBToDen(ma);
                for (int i = 0; i < tblSanBay.getRowCount(); i++) {
                    String id = String.valueOf(tblSanBay.getValueAt(i, 1));
                    if (id.equals(sb.getMaSanBay())) {
                        tblSanBay.setRowSelectionInterval(i, i);
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
        if (!txtMaSanBay.getText().isEmpty()) {
            String ma = txtMaSanBay.getText().trim();
            String ten = txtTenSanBay.getText().trim();
            String quocgia = txtQuocGia.getText().trim();
            String diadiem = txtDiaDiem.getText().trim();
            int kt = new SanBayDAO().sua(new SanBay(ma, ten, quocgia, diadiem));
            if (kt == 1) {
                bang();
                tblSanBay.setRowSelectionInterval(dong, dong);
                an();
                editColumnWidth();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa sân bay " + tblSanBay.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaSanBay.getText().trim();
                int kt = new SanBayDAO().xoa(new SanBay(ma));
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
        SanBay sb = new SanBay();
        new SanBayDAO().hienThi(tblSanBay, sb, row);
        txtMaSanBay.setText(sb.getMaSanBay());
        txtTenSanBay.setText(sb.getTenSanbay());
        txtQuocGia.setText(sb.getQuocGia());
        txtDiaDiem.setText(sb.getDiaDiem());
        txtMaSanBay.setEditable(false);
        lblSTT.setText(String.valueOf(tblSanBay.getValueAt(dong, 0)));
    }

    private void exportExcel() {
        new ExportExcel().exportExcel("DANH SÁCH BẢNG SÂN BAY", "Sân bay", tblSanBay, new int[]{1500, 5000, 7000, 6000, 6000});
        JOptionPane.showMessageDialog(this, "Xuất file excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportPDF() {
        new ExportPDF().exportPDF("DANH SÁCH BẢNG SÂN BAY", tblSanBay);
        JOptionPane.showMessageDialog(this, "Xuất file pdf thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void chucNang(String str) {
        try {
            if (str.equals("dau")) {
                dong = 0;
            } else if (str.equals("cuoi")) {
                dong = tblSanBay.getRowCount() - 1;
            } else if (str.equals("pre")) {
                dong--;
                if (dong < 0) {
                    JOptionPane.showMessageDialog(this, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (str.equals("next")) {
                dong++;
                if (dong >= tblSanBay.getRowCount()) {
                    JOptionPane.showMessageDialog(this, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }

            tblSanBay.setRowSelectionInterval(dong, dong);
            lblSTT.setText(String.valueOf(tblSanBay.getValueAt(dong, 0)));
            hienThi(dong);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public frmSanBay() {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public frmSanBay(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtMaSanBay, txtTenSanBay, txtQuocGia, txtDiaDiem, txtTimKiem});
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

        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMaSanBay = new javax.swing.JTextField();
        lblLoiMaSB = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTenSanBay = new javax.swing.JTextField();
        lblLoiTen = new javax.swing.JLabel();
        splTable = new javax.swing.JScrollPane();
        tblSanBay = new javax.swing.JTable();
        lblSTT = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtQuocGia = new javax.swing.JTextField();
        lblLoiQuocGia = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDiaDiem = new javax.swing.JTextField();
        lblLoiDiaDiem = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(55, 38, 91));
        jLabel15.setText("QUẢN LÝ SÂN BAY");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(55, 38, 91));
        jLabel16.setText("Tìm kiếm");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.setOpaque(false);
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel8.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        txtMaSanBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSanBay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaSanBay.setOpaque(false);
        txtMaSanBay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSanBayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaSanBayKeyTyped(evt);
            }
        });
        jPanel8.add(txtMaSanBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMaSB.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiMaSB.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaSB.setText("Mã hãng máy bay không chinh xác");
        jPanel8.add(lblLoiMaSB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(55, 38, 91));
        jLabel17.setText("Mã sân bay");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(55, 38, 91));
        jLabel18.setText("Tên sân bay");
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        txtTenSanBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSanBay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenSanBay.setOpaque(false);
        jPanel8.add(txtTenSanBay, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 390, -1));

        lblLoiTen.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiTen.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiTen.setText("Tên hãng máy bay không chính xác");
        jPanel8.add(lblLoiTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 240, -1));

        tblSanBay.setAutoCreateRowSorter(true);
        tblSanBay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sân bay", "Tên sân bay", "Quốc gia", "Địa điểm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSanBay.setAutoscrolls(false);
        tblSanBay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblSanBay.setFocusable(false);
        tblSanBay.setGridColor(new java.awt.Color(255, 255, 255));
        tblSanBay.setOpaque(false);
        tblSanBay.setRequestFocusEnabled(false);
        tblSanBay.setRowHeight(30);
        tblSanBay.setRowMargin(0);
        tblSanBay.setSelectionBackground(new java.awt.Color(55, 38, 91));
        tblSanBay.setShowHorizontalLines(false);
        tblSanBay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanBayMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblSanBay);

        jPanel8.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 930, 300));

        lblSTT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSTT.setForeground(new java.awt.Color(55, 38, 91));
        lblSTT.setText("0");
        jPanel8.add(lblSTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 630, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(55, 38, 91));
        jLabel19.setText("Quốc gia");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtQuocGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtQuocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtQuocGia.setOpaque(false);
        txtQuocGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuocGiaKeyReleased(evt);
            }
        });
        jPanel8.add(txtQuocGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 360, -1));

        lblLoiQuocGia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiQuocGia.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiQuocGia.setText("Quốc gia không chính xác");
        jPanel8.add(lblLoiQuocGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 190, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(55, 38, 91));
        jLabel20.setText("Địa điểm");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        txtDiaDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiaDiem.setOpaque(false);
        txtDiaDiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiaDiemKeyReleased(evt);
            }
        });
        jPanel8.add(txtDiaDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 390, -1));

        lblLoiDiaDiem.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiDiaDiem.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiDiaDiem.setText("Địa điểm không chính xác");
        jPanel8.add(lblLoiDiaDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 200, -1));

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
        jPanel8.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));

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
        jPanel8.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

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
        jPanel8.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, -1));

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
        jPanel8.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, -1, -1));

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
        jPanel8.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 630, -1, -1));

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
        jPanel8.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, -1, -1));

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
        jPanel8.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 630, -1, -1));

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
        jPanel8.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 630, -1, -1));

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
        jPanel8.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 630, -1, -1));

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
        jPanel8.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 630, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSanBayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSanBayKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanBayKeyReleased

    private void txtQuocGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuocGiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuocGiaKeyReleased

    private void txtDiaDiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaDiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaDiemKeyReleased

    private void tblSanBayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanBayMouseClicked
        txtTimKiem.setText(null);
        dong = tblSanBay.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblSanBayMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new SanBayDAO().tim(tblSanBay, txtTimKiem.getText().trim());
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
        if(tblSanBay.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn sân bay cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else{
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
        tblSanBay.clearSelection();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnPDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseEntered
        new DungChung().hoverButton5(1, btnPDF, "bgButtonFPTHover.png");
    }//GEN-LAST:event_btnPDFMouseEntered

    private void btnPDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPDFMouseExited
        new DungChung().hoverButton5(2, btnPDF, "bgButtonFPT.png");
    }//GEN-LAST:event_btnPDFMouseExited

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        exportPDF();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportExcel();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseExited
        new DungChung().hoverButton5(2, btnExcel, "bgButtonExcel.png");
    }//GEN-LAST:event_btnExcelMouseExited

    private void btnExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelMouseEntered
        new DungChung().hoverButton5(1, btnExcel, "bgButtonExcelHover.png");
    }//GEN-LAST:event_btnExcelMouseEntered

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

    private void txtMaSanBayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSanBayKeyTyped
        if (txtMaSanBay.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaSanBayKeyTyped

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
            java.util.logging.Logger.getLogger(frmSanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSanBay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSanBay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblLoiDiaDiem;
    private javax.swing.JLabel lblLoiMaSB;
    private javax.swing.JLabel lblLoiQuocGia;
    private javax.swing.JLabel lblLoiTen;
    private javax.swing.JLabel lblSTT;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JTable tblSanBay;
    private javax.swing.JTextField txtDiaDiem;
    private javax.swing.JTextField txtMaSanBay;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtTenSanBay;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
