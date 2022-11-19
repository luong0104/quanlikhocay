/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLy;

import Cay.Cay;
import Cay.PhieuNhap;
import Cay.PhieuXuat;
import Login.Login;
//import Cay.chitietnhap;
//import Cay.chitietxuat;
//import Cay.phanloaicay;
//import Cay.phieunhap;
//import Cay.phieuxuat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class User extends javax.swing.JFrame {

    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        view_cay();
        cbAddLoaiCay();
        cbAddLoaiCay1();
        cbAddLoaiCay2();
        view_xuat();
        view_nhap();
        
    }
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/qlk", "root", "");
        return con;
    }
     
    
    
     public void cbAddLoaiCay(){
        try{ 
            Connection con = getConnection();  
            String query = "Select maloaicay from phanloaicay";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                cbMaCay.addItem(rs.getString("maloaicay"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    public void cbAddLoaiCay1(){
        try{ 
            Connection con = getConnection();  
            String query = "Select macay from cay";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                cbMaCay1.addItem(rs.getString("macay"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cbAddLoaiCay2(){
        try{ 
            Connection con = getConnection();  
            String query = "Select macay from cay";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                cbMaCay2.addItem(rs.getString("macay"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    
    
    public ArrayList<Cay> cayList(String searchcay){
         ArrayList<Cay> cayList = new ArrayList<Cay>();
        try{ 
            
            Connection con = getConnection();  
            
            String search = "SELECT * FROM cay WHERE CONCAT(macay, tencay, maloaicay, giatien,soluong) LIKE '%"+searchcay+"%'";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(search);
            Cay cay;
            while(rs.next()){
                cay = new Cay(rs.getString("macay"),rs.getString("tencay"),rs.getString("maloaicay"),rs.getDouble("giatien"), rs.getInt("soluong"));
                cayList.add(cay);
            }       
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cayList;
    }    
    
    
    
    
    public void view_cay(){
        ArrayList<Cay> cays = cayList(txtSearch.getText());
        DefaultTableModel modelcay1 = (DefaultTableModel)tblUserViewCay.getModel();
        Object[] row = new Object[5];
        for(int i =0; i<cays.size(); i++){
            row[0]= cays.get(i).getMacay();
            row[1]= cays.get(i).getTencay();
            row[2]= cays.get(i).getLoaicay();
            row[3]= cays.get(i).getGiathanh();
            row[4]= cays.get(i).getSoluong();
            modelcay1.addRow(row);
        }
       tblUserViewCay.setModel(modelcay1);
    }
    
    public ArrayList<Cay> xuatCayList(){
        ArrayList<Cay> xuatList = new ArrayList<>();
        try{ 
            Connection con = getConnection();  
            String query = "Select * from cay";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            Cay cay;
            while(rs.next()){
                cay = new Cay(rs.getString("macay"),rs.getString("tencay"),rs.getString("maloaicay"),rs.getDouble("giatien"), rs.getInt("soluong"));
                xuatList.add(cay);
            }  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return xuatList;
    }
    
        
    public void view_xuatcay(){
        ArrayList<Cay> list1 = xuatCayList();
        DefaultTableModel modelnhap1 = (DefaultTableModel)tblUserViewCay.getModel();
        Object[] row = new Object[5];
        for(int i =0; i<list1.size(); i++){
            row[0]= list1.get(i).getMacay();
            row[1]= list1.get(i).getTencay();
            row[2]= list1.get(i).getMacay();
            row[3]= list1.get(i).getGiathanh();
            row[4]= list1.get(i).getSoluong();
            
            
            modelnhap1.addRow(row);
        }
    }
    
    
    
    public ArrayList<PhieuXuat> xuatList(){
         ArrayList<PhieuXuat> xuatList = new ArrayList<PhieuXuat>();
        try{ 
            
            Connection con = getConnection();  
            
            String search = "SELECT * FROM phieuxuat ";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(search);
            PhieuXuat px;
            while(rs.next()){
                px = new PhieuXuat(rs.getString("maphieuxuat"),rs.getString("ngayxuat"),rs.getString("macay"), rs.getInt("soluong"));
                xuatList.add(px);
            }       
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return xuatList;
    }    
    
    
    
    
    public void view_xuat(){
        ArrayList<PhieuXuat> xuat = xuatList();
        DefaultTableModel modelxuat = (DefaultTableModel)tblUserXuat.getModel();
        Object[] row = new Object[4];
        for(int i =0; i<xuat.size(); i++){
            row[0]= xuat.get(i).getMaxuat();
            row[1]= xuat.get(i).getNgayxuat();
            row[2]= xuat.get(i).getMacay();
            row[3]= xuat.get(i).getSoluong();
            modelxuat.addRow(row);
        }
       tblUserXuat.setModel(modelxuat);
    }
    
    public ArrayList<PhieuNhap> nhapList(){
         ArrayList<PhieuNhap> nhapList = new ArrayList<PhieuNhap>();
        try{ 
            
            Connection con = getConnection();              
            String search = "SELECT * FROM phieunhap";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(search);
            PhieuNhap pn;
            while(rs.next()){
                pn = new PhieuNhap(rs.getString("manhap"),rs.getString("ngaynhap"),rs.getString("macay"), rs.getInt("soluong"));
                nhapList.add(pn);
            }       
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return nhapList;
    }    
    
    
    
    
    public void view_nhap(){
        ArrayList<PhieuNhap> nhap = nhapList();
        DefaultTableModel modelnhap = (DefaultTableModel)tblUserNhap.getModel();
        Object[] row = new Object[4];
        for(int i =0; i<nhap.size(); i++){
            row[0]= nhap.get(i).getManhap();
            row[1]= nhap.get(i).getNgaynhap();
            row[2]= nhap.get(i).getMacay();
            row[3]= nhap.get(i).getSoluong();
            modelnhap.addRow(row);
        }
       tblUserNhap.setModel(modelnhap);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUserViewCay = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnAddCay = new javax.swing.JButton();
        txtAddMaCay = new javax.swing.JTextField();
        txtAddTenCay = new javax.swing.JTextField();
        txtAddGiaCay = new javax.swing.JTextField();
        cbMaCay = new javax.swing.JComboBox<>();
        btnExport = new javax.swing.JButton();
        btnUpdateCay = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUserXuat = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();
        btnXuatCay = new javax.swing.JButton();
        txtMaXuat = new javax.swing.JTextField();
        txtNgayXuat = new javax.swing.JTextField();
        txtESoLuong = new javax.swing.JTextField();
        cbMaCay1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUserNhap = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnRefresh2 = new javax.swing.JButton();
        btnNhap = new javax.swing.JButton();
        txtManhap = new javax.swing.JTextField();
        txtNgaynhap = new javax.swing.JTextField();
        txtISoLuong = new javax.swing.JTextField();
        cbMaCay2 = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblUserViewCay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Cây", "Tên Cây", "Loại Cây", "Giá Thành", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblUserViewCay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserViewCayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUserViewCay);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm cây", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel3.setText("Mã cây : ");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Tên cây : ");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Mã cây : ");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Giá tiền : ");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnAddCay.setText("Add");
        btnAddCay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCayActionPerformed(evt);
            }
        });

        cbMaCay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        btnExport.setText("Export file...");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnUpdateCay.setText("Update");
        btnUpdateCay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExport)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(btnRefresh)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAddCay)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdateCay))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddMaCay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddTenCay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbMaCay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddGiaCay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(btnExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAddMaCay)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddTenCay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaCay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddGiaCay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCay)
                    .addComponent(btnRefresh)
                    .addComponent(btnUpdateCay))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 743, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Quản Lý Cây", jPanel2);

        tblUserXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Xuất", "Ngày Xuất", "Mã Cây", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblUserXuat);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Mã Xuất : ");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Ngày Xuất:");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Mã cây : ");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Số Lượng");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRefresh1.setText("Refresh");
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        btnXuatCay.setText("Xuất Cây");
        btnXuatCay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatCayActionPerformed(evt);
            }
        });

        cbMaCay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(btnRefresh1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatCay))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbMaCay1, 0, 145, Short.MAX_VALUE)
                            .addComponent(txtESoLuong))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaXuat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaCay1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtESoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatCay)
                    .addComponent(btnRefresh1))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Xuất Cây", jPanel3);

        tblUserNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhập", "Ngày Nhập", "Mã Cây", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblUserNhap);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Mã Nhập : ");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Ngày Xuất:");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Mã cây : ");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Số Lượng");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRefresh2.setText("Refresh");
        btnRefresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh2ActionPerformed(evt);
            }
        });

        btnNhap.setText("Nhập Cây");
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        cbMaCay2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(btnRefresh2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNhap))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbMaCay2, 0, 145, Short.MAX_VALUE)
                            .addComponent(txtISoLuong)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtManhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbMaCay2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtISoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhap)
                    .addComponent(btnRefresh2))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Nhập Cây", jPanel4);

        jButton1.setText("Đăng Xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đăng Xuất", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateCayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCayActionPerformed
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            String maloaicay = cbMaCay.getSelectedItem().toString();
            st.execute("UPDATE cay SET tencay='" + txtAddTenCay.getText() + "',giatien=" + txtAddGiaCay.getText() +",maloaicay='" + maloaicay  + "' WHERE macay='" + txtAddMaCay.getText()+"'");
            DefaultTableModel modelcay = (DefaultTableModel)tblUserViewCay.getModel();
            modelcay.setRowCount(0);
            view_cay();
            JOptionPane.showMessageDialog(null, "Cập Nhập Thành Công!!!");
            st.close();
            con.close();
            //Referesh(); //Calling Referesh() method
        } catch (SQLException | ClassNotFoundException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }//GEN-LAST:event_btnUpdateCayActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        try{
            XSSFWorkbook workbook= new XSSFWorkbook();
            XSSFSheet sheet= workbook.createSheet("indanhsachcay");
            XSSFRow row= null;
            Cell cell= null;
            row= sheet.createRow(2);
            cell=row.createCell(0,org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Mã Cây ");
            cell=row.createCell(1,org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Tên Cây");
            cell=row.createCell(2,org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Loại Cây");
            cell=row.createCell(3,org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Giá Thành");
            cell=row.createCell(4,org.apache.poi.ss.usermodel.CellType.STRING);
            cell.setCellValue("Số Lượng");
            for(int i=0; i<xuatCayList().size();i++){
                row= sheet.createRow(3+i);
                cell= row.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(xuatCayList().get(i).getMacay());
                cell= row.createCell(1, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(xuatCayList().get(i).getTencay());
                cell= row.createCell(2, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(xuatCayList().get(i).getLoaicay());
                cell= row.createCell(3, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(xuatCayList().get(i).getGiathanh());
                cell= row.createCell(4, org.apache.poi.ss.usermodel.CellType.STRING);
                cell.setCellValue(xuatCayList().get(i).getSoluong());
            }
            File f= new File("D:/netbean/danhsachcay.xlsx");
            try{
                FileOutputStream fis= new FileOutputStream(f);
                workbook.write(fis);
                fis.close();
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();

            }
            JOptionPane.showMessageDialog(this, "in thanh cong");
        }catch (Exception ex){
            ex.printStackTrace();

        }

    }//GEN-LAST:event_btnExportActionPerformed

    private void btnAddCayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCayActionPerformed
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            String maloaicay = cbMaCay.getSelectedItem().toString();
            String sql = "INSERT INTO cay(macay,tencay,maloaicay,giatien) VALUES('"+txtAddMaCay.getText()+"','"+txtAddTenCay.getText()+"','"+maloaicay+"',"+txtAddGiaCay.getText()+")";
            statement.executeUpdate(sql);
            DefaultTableModel modelcay = (DefaultTableModel)tblUserViewCay.getModel();
            modelcay.setRowCount(0);
            view_cay();
            JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
            statement.close();
            con.close();
            //Referesh(); //Calling Referesh() method
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_btnAddCayActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtAddMaCay.setText("");
        txtAddTenCay.setText("");
        txtAddGiaCay.setText("");
        cbMaCay.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        DefaultTableModel modelnhap = (DefaultTableModel)tblUserViewCay.getModel();
        modelnhap.setRowCount(0);
        view_cay();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblUserViewCayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserViewCayMouseClicked
        try{
            Connection con = getConnection();
            int row = tblUserViewCay.getSelectedRow();
            String tbl_ClickCay = (tblUserViewCay.getModel().getValueAt(row, 0).toString());
            String query1 = "Select * from cay where macay ='"+tbl_ClickCay+"'";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            if(rs.next()){
                String macay = rs.getString("macay");
                String tencay = rs.getString("tencay");
                String maloaicay = rs.getString("maloaicay");
                String giatien = rs.getString("giatien");
                String soluong = rs.getString("soluong");
                String info = "Mã Cây: "+macay+"\n"
                +"Tên Cây: "+tencay+"\n"
                +"Loại Cây: "+maloaicay+"\n"
                +"Giá Thành: "+giatien+"\n"
                +"Số Lượng: "+soluong+"\n";
                //JOptionPane.showMessageDialog(this, info);

                int messagetype = JOptionPane.QUESTION_MESSAGE;
                String[] options ={"Update","Delete","Oke"};
                int code = JOptionPane.showOptionDialog(this, info, "info", 0, messagetype, null, options, "Oke");
                if(code==1){
                    int a = JOptionPane.QUESTION_MESSAGE;
                    String[] b ={"Yes","No"};
                    int c = JOptionPane.showOptionDialog(this, "Xóa?", "Delete", 0, a, null, b, "Oke");
                    //int d = JOptionPane.showConfirmDialog(this, messagetype)

                    if(c==0){
                        String sql = "delete from cay where macay='"+macay+"'";
                        Statement stdel = con.createStatement();
                        stdel.executeUpdate(sql);
                        DefaultTableModel modelxuat = (DefaultTableModel)tblUserViewCay.getModel();
                        modelxuat.setRowCount(0);
                        view_cay();
                        JOptionPane.showMessageDialog(this, "Xóa thành công!!1");
                    }else if(c == 1){
                        remove(this);
                    }
                }else if(code == 2){
                    remove(this);
                }
                else if(code ==0){
                    txtAddMaCay.setText(macay);
                    txtAddTenCay.setText(tencay);
                    txtAddGiaCay.setText(giatien);
                    cbMaCay.setSelectedItem(maloaicay.toString());
                }
            }

        }
        catch (SQLException | ClassNotFoundException se) {
            JOptionPane.showMessageDialog(null, se);
        }
    }//GEN-LAST:event_tblUserViewCayMouseClicked

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        txtESoLuong.setText("");
        txtMaXuat.setText("");
        txtNgayXuat.setText("");
        cbMaCay1.setSelectedIndex(0);
        
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void btnXuatCayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatCayActionPerformed
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            String macay = cbMaCay1.getSelectedItem().toString();
            String sql = "INSERT INTO phieuxuat VALUES('"+txtMaXuat.getText()+"','"+txtNgayXuat.getText()+"','"+macay+"',"+txtESoLuong.getText()+")";
            statement.executeUpdate(sql);
            DefaultTableModel modelxuat = (DefaultTableModel)tblUserXuat.getModel();
            modelxuat.setRowCount(0);
            view_xuat();
            JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
            statement.close();
            con.close();
            //Referesh(); //Calling Referesh() method
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_btnXuatCayActionPerformed

    private void btnRefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh2ActionPerformed
       txtISoLuong.setText("");
       txtManhap.setText("");
       txtNgaynhap.setText("");
       cbMaCay2.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefresh2ActionPerformed

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            String macay = cbMaCay2.getSelectedItem().toString();
            String sql = "INSERT INTO phieunhap VALUES('"+txtManhap.getText()+"','"+txtNgaynhap.getText()+"','"+macay+"',"+txtISoLuong.getText()+")";
            statement.executeUpdate(sql);
            DefaultTableModel modelnhap = (DefaultTableModel)tblUserNhap.getModel();
            modelnhap.setRowCount(0);
            view_nhap();
            JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
            statement.close();
            con.close();
            //Referesh(); //Calling Referesh() method
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_btnNhapActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int respone = JOptionPane.showConfirmDialog(this, "Do you want logout?","WARNING!!!",JOptionPane.YES_NO_OPTION);
        
        if(respone== JOptionPane.YES_OPTION){
            Login lg = new Login();
            lg.show();
            dispose();
        }else if(respone== JOptionPane.NO_OPTION){
            remove(respone);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCay;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnRefresh2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateCay;
    private javax.swing.JButton btnXuatCay;
    private javax.swing.JComboBox<String> cbMaCay;
    private javax.swing.JComboBox<String> cbMaCay1;
    private javax.swing.JComboBox<String> cbMaCay2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblUserNhap;
    private javax.swing.JTable tblUserViewCay;
    private javax.swing.JTable tblUserXuat;
    private javax.swing.JTextField txtAddGiaCay;
    private javax.swing.JTextField txtAddMaCay;
    private javax.swing.JTextField txtAddTenCay;
    private javax.swing.JTextField txtESoLuong;
    private javax.swing.JTextField txtISoLuong;
    private javax.swing.JTextField txtMaXuat;
    private javax.swing.JTextField txtManhap;
    private javax.swing.JTextField txtNgayXuat;
    private javax.swing.JTextField txtNgaynhap;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
