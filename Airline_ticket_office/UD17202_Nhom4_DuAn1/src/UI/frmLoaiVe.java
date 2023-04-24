/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.LoaiVeDAO;
import DungChung.DungChung;
import DungChung.ExportExcel;
import DungChung.ExportPDF;
import Model.LoaiVe;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author TuanDuc
 */
public class frmLoaiVe extends javax.swing.JFrame {
    String chucVu = "";
    int dong = -1;

    private void an() {
        new DungChung().hideLBLError(new JLabel[]{lblLoiMaLoai, lblLoiTenLoai});
    }

    private boolean check() {
        return new DungChung().check(new JLabel[]{lblLoiMaLoai, lblLoiTenLoai}, new JTextField[]{txtMaLoaiVe, txtTenLoaiVe});
    }

    private void moi() {
        new DungChung().reset(new JTextField[]{txtMaLoaiVe, txtTenLoaiVe});
        an();
        lblSTT.setText("0");
        dong = -1;
    }

    private void bang() {
        new LoaiVeDAO().loadTable(tblLoaiVe);
    }

    private void editColumnWidth() {
        int[] col = new int[]{40, 450, 450};
        new DungChung().editColumnWidth(col, tblLoaiVe);
    }

    private void them() {
        if (check()) {
            String ma = txtMaLoaiVe.getText().trim();
            String ten = txtTenLoaiVe.getText().trim();
            int kt = new LoaiVeDAO().them(new LoaiVe(ma, ten));
            if (kt == 1) {
                bang();
                LoaiVe lv = new LoaiVeDAO().timLVToDen(ma);
                for (int i = 0; i < tblLoaiVe.getRowCount(); i++) {
                    String id = String.valueOf(tblLoaiVe.getValueAt(i, 1));
                    if (id.equals(lv.getMaLoaiVe())) {
                        tblLoaiVe.setRowSelectionInterval(i, i);
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
        if (!txtMaLoaiVe.getText().isEmpty()) {
            String ma = txtMaLoaiVe.getText().trim();
            String ten = txtTenLoaiVe.getText().trim();
            int kt = new LoaiVeDAO().sua(new LoaiVe(ma, ten));
            if (kt == 1) {
                bang();
                tblLoaiVe.setRowSelectionInterval(dong, dong);
                editColumnWidth();
                an();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoa() {
        if (dong >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "Bạn cần xóa loại vé " + tblLoaiVe.getValueAt(dong, 2) + "?", "Thông báo", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String ma = txtMaLoaiVe.getText().trim();
                int kt = new LoaiVeDAO().xoa(new LoaiVe(ma));
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
        LoaiVe lv = new LoaiVe();
        new LoaiVeDAO().hienThi(tblLoaiVe, lv, row);
        txtMaLoaiVe.setText(lv.getMaLoaiVe());
        txtTenLoaiVe.setText(lv.getTenLoaiVe());
        txtMaLoaiVe.setEditable(false);
        lblSTT.setText(String.valueOf(tblLoaiVe.getValueAt(dong, 0)));
    }

    private void exportExcel() {
        new ExportExcel().exportExcel("DANH SÁCH BẢNG LOẠI VÉ", "Loại vé", tblLoaiVe, new int[]{1500, 4500, 5000});
        JOptionPane.showMessageDialog(this, "Xuất file excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportPDF() {
        new ExportPDF().exportPDF("DANH SÁCH BẢNG LOẠI VÉ", tblLoaiVe);
        JOptionPane.showMessageDialog(this, "Xuất file pdf thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void chucNang(String str) {
        try {
            if (str.equals("dau")) {
                dong = 0;
            } else if (str.equals("cuoi")) {
                dong = tblLoaiVe.getRowCount() - 1;
            } else if (str.equals("pre")) {
                dong--;
                if (dong < 0) {
                    JOptionPane.showMessageDialog(this, "Đang ở đầu danh sách!");
                    dong += 1;
                    return;
                }
            } else if (str.equals("next")) {
                dong++;
                if (dong >= tblLoaiVe.getRowCount()) {
                    JOptionPane.showMessageDialog(this, "Đang ở cuối danh sách!");
                    dong -= 1;
                    return;
                }
            }

            tblLoaiVe.setRowSelectionInterval(dong, dong);
            lblSTT.setText(String.valueOf(tblLoaiVe.getValueAt(dong, 0)));
            hienThi(dong);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public frmLoaiVe() {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtMaLoaiVe, txtTenLoaiVe, txtTimKiem});
        an();
        bang();
        editColumnWidth();
        splTable.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
    
    public frmLoaiVe(String vt) {
        initComponents();
        setLocationRelativeTo(this);
        new DungChung().transTXT(new JTextField[]{txtMaLoaiVe, txtTenLoaiVe, txtTimKiem});
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtMaLoaiVe = new javax.swing.JTextField();
        lblLoiMaLoai = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenLoaiVe = new javax.swing.JTextField();
        lblLoiTenLoai = new javax.swing.JLabel();
        splTable = new javax.swing.JScrollPane();
        tblLoaiVe = new javax.swing.JTable();
        lblSTT = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 38, 91));
        jLabel1.setText("QUẢN LÝ LOẠI VÉ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 11, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 38, 91));
        jLabel2.setText("Tìm kiếm");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, -1, -1));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.setOpaque(false);
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 196, -1));

        txtMaLoaiVe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaLoaiVe.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMaLoaiVe.setOpaque(false);
        txtMaLoaiVe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaLoaiVeKeyReleased(evt);
            }
        });
        jPanel1.add(txtMaLoaiVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, -1));

        lblLoiMaLoai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiMaLoai.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiMaLoai.setText("Mã loại vé không chinh xác");
        jPanel1.add(lblLoiMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 220, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(55, 38, 91));
        jLabel3.setText("Mã loại vé");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(55, 38, 91));
        jLabel5.setText("Tên loại vé");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        txtTenLoaiVe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenLoaiVe.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTenLoaiVe.setOpaque(false);
        jPanel1.add(txtTenLoaiVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 390, -1));

        lblLoiTenLoai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblLoiTenLoai.setForeground(new java.awt.Color(255, 0, 0));
        lblLoiTenLoai.setText("Tên loại vé không chính xác");
        jPanel1.add(lblLoiTenLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 150, -1));

        tblLoaiVe.setAutoCreateRowSorter(true);
        tblLoaiVe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblLoaiVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã loại vé", "Tên loại vé"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblLoaiVe.setAutoscrolls(false);
        tblLoaiVe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblLoaiVe.setFocusable(false);
        tblLoaiVe.setGridColor(new java.awt.Color(255, 255, 255));
        tblLoaiVe.setMinimumSize(new java.awt.Dimension(30, 0));
        tblLoaiVe.setOpaque(false);
        tblLoaiVe.setPreferredSize(new java.awt.Dimension(150, 150));
        tblLoaiVe.setRequestFocusEnabled(false);
        tblLoaiVe.setRowHeight(30);
        tblLoaiVe.setRowMargin(0);
        tblLoaiVe.setSelectionBackground(new java.awt.Color(55, 38, 91));
        tblLoaiVe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLoaiVe.setShowHorizontalLines(false);
        tblLoaiVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiVeMouseClicked(evt);
            }
        });
        splTable.setViewportView(tblLoaiVe);

        jPanel1.add(splTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 930, 200));

        lblSTT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSTT.setForeground(new java.awt.Color(55, 38, 91));
        lblSTT.setText("0");
        jPanel1.add(lblSTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, -1, -1));

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
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

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
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

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
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, -1, -1));

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
        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, -1, -1));

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
        jPanel1.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, -1, -1));

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
        jPanel1.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 450, -1, -1));

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
        jPanel1.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

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
        jPanel1.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, -1, -1));

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
        jPanel1.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, -1, -1));

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
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaLoaiVeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaLoaiVeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiVeKeyReleased

    private void tblLoaiVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiVeMouseClicked
        txtTimKiem.setText(null);
        dong = tblLoaiVe.getSelectedRow();
        hienThi(dong);
    }//GEN-LAST:event_tblLoaiVeMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        new LoaiVeDAO().tim(tblLoaiVe, txtTimKiem.getText().trim());
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
        if(tblLoaiVe.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Chọn loại vé cần cập nhật.", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
        tblLoaiVe.clearSelection();
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
            java.util.logging.Logger.getLogger(frmLoaiVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLoaiVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLoaiVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLoaiVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLoaiVe().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLoiMaLoai;
    private javax.swing.JLabel lblLoiTenLoai;
    private javax.swing.JLabel lblSTT;
    private javax.swing.JScrollPane splTable;
    private javax.swing.JTable tblLoaiVe;
    private javax.swing.JTextField txtMaLoaiVe;
    private javax.swing.JTextField txtTenLoaiVe;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
