/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLy;

import Cay.Account;
import Cay.*;
import Login.Login;
import com.mysql.cj.result.Row;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        view_cay();
        cbLoaiCay();
        cbAddLoaiCay();
        view_account();
        view_xuat();
        view_nhap();
    }
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/qlk", "root", "");
        return con;
    }
    
    
    public void cbLoaiCay(){
        try{ 
            Connection con = getConnection();  
            String query = "Select tenloaicay from phanloaicay";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                cbSearchLoaiCay.addItem(rs.getString("tenloaicay"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
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
        DefaultTableModel modelxuat = (DefaultTableModel)tblViewXuat.getModel();
        Object[] row = new Object[4];
        for(int i =0; i<xuat.size(); i++){
            row[0]= xuat.get(i).getMaxuat();
            row[1]= xuat.get(i).getNgayxuat();
            row[2]= xuat.get(i).getMacay();
            row[3]= xuat.get(i).getSoluong();
            modelxuat.addRow(row);
        }
       tblViewXuat.setModel(modelxuat);
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
        DefaultTableModel modelnhap = (DefaultTableModel)tblViewNhap.getModel();
        Object[] row = new Object[4];
        for(int i =0; i<nhap.size(); i++){
            row[0]= nhap.get(i).getManhap();
            row[1]= nhap.get(i).getNgaynhap();
            row[2]= nhap.get(i).getMacay();
            row[3]= nhap.get(i).getSoluong();
            modelnhap.addRow(row);
        }
       tblViewNhap.setModel(modelnhap);
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
        DefaultTableModel modelcay1 = (DefaultTableModel)tblViewCay.getModel();
        Object[] row = new Object[5];
        for(int i =0; i<cays.size(); i++){
            row[0]= cays.get(i).getMacay();
            row[1]= cays.get(i).getTencay();
            row[2]= cays.get(i).getLoaicay();
            row[3]= cays.get(i).getGiathanh();
            row[4]= cays.get(i).getSoluong();
            modelcay1.addRow(row);
        }
       tblViewCay.setModel(modelcay1);
    }
    
    
    
    
    
    public ArrayList<Account> accountList(){
        ArrayList<Account> accountList = new ArrayList<>();
        try{ 
            Connection con = getConnection();  
            String query = "Select * from account";
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            Account acc;
            while(rs.next()){
                acc = new Account(rs.getString("username"),rs.getString("password"),rs.getString("cmnd"),rs.getBoolean("role"));
                accountList.add(acc);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return accountList;
    }
    
        
    public void view_account(){
        ArrayList<Account> listacc = accountList();
        DefaultTableModel modelacc = (DefaultTableModel)tblAccount.getModel();
        Object[] row = new Object[3];
        for(int i =0; i<listacc.size(); i++){
            row[0]= listacc.get(i).getUsername();
            row[1]= listacc.get(i).getCmnd();
            row[2]= listacc.get(i).getRole();
            modelacc.addRow(row);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblViewCay = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbSearchLoaiCay = new javax.swing.JComboBox<>();
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
        btnImport = new javax.swing.JButton();
        btnUpdateCay = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtPassWord = new javax.swing.JTextField();
        txtRetypePass = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblViewNhap = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblViewXuat = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblViewCay.setModel(new javax.swing.table.DefaultTableModel(
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
        tblViewCay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblViewCayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblViewCay);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Loại cây : ");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbSearchLoaiCay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbSearchLoaiCay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(cbSearchLoaiCay, 0, 156, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearch)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbSearchLoaiCay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(15, Short.MAX_VALUE)))
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

        btnImport.setText("Import file...");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
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
                    .addComponent(btnImport)
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
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(btnImport)
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
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Quản lý cây", jPanel1);

        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "CMND", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAccount);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Username : ");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("CMND : ");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Password : ");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("RetypePass : ");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassWord, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRetypePass, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCMND, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRetypePass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Quản lý tài khoản", jPanel2);

        tblViewNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblViewNhap);

        tblViewXuat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblViewXuat);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jScrollPane4)
        );

        jTabbedPane2.addTab("Quản lý xuất nhập cây", jPanel3);

        btnLogout.setText("LogOut");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Đăng xuất", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
         try {
            //connection
            Connection con = getConnection();
            Statement st = con.createStatement();
            //Excel
            //FileInputStream input = new FileInputStream("D:\\netbean\\Book1.xlsx");
             JFileChooser chooser = new JFileChooser();
             chooser.showOpenDialog(null);
             File input = chooser.getSelectedFile().getAbsoluteFile();
            
            XSSFWorkbook  wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            for (int i = 1; i <= rows; i++) {

                XSSFRow row = sheet.getRow(i);
                String macay = row.getCell(0).getStringCellValue();
                String tencay = row.getCell(1).getStringCellValue();
                String maloaicay = row.getCell(2).getStringCellValue();
                double giatien = row.getCell(3).getNumericCellValue();
                double soluongs = row.getCell(4).getNumericCellValue();
                int soluong = (int)soluongs;

                String sql = "INSERT INTO cay VALUES('" + macay + "','" + tencay + "','" + maloaicay + "'," + giatien + "," + soluong +")";
                st.execute(sql);
            }           
            wb.close();
            //input.close();
            con.close();
            DefaultTableModel modelnhap = (DefaultTableModel)tblViewCay.getModel();
            modelnhap.setRowCount(0);
            view_cay();
            JOptionPane.showMessageDialog(this, "Import Successfull!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnImportActionPerformed

    private void tblViewCayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblViewCayMouseClicked
       try{
            Connection con = getConnection();    
            int row = tblViewCay.getSelectedRow();
            String tbl_ClickCay = (tblViewCay.getModel().getValueAt(row, 0).toString());
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
                        DefaultTableModel modelxuat = (DefaultTableModel)tblViewCay.getModel();
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
    }//GEN-LAST:event_tblViewCayMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtAddMaCay.setText("");
        txtAddTenCay.setText("");
        txtAddGiaCay.setText("");
        cbMaCay.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnAddCayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCayActionPerformed
        try {  
            Connection con = getConnection();    
            Statement statement = con.createStatement(); 
            String maloaicay = cbMaCay.getSelectedItem().toString();
            String sql = "INSERT INTO cay(macay,tencay,maloaicay,giatien) VALUES('"+txtAddMaCay.getText()+"','"+txtAddTenCay.getText()+"','"+maloaicay+"',"+txtAddGiaCay.getText()+")";
            statement.executeUpdate(sql);  
            DefaultTableModel modelcay = (DefaultTableModel)tblViewCay.getModel();
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

    private void btnUpdateCayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCayActionPerformed
        try {  
            Connection con = getConnection();    
            Statement st = con.createStatement();              
            String maloaicay = cbMaCay.getSelectedItem().toString();
            st.execute("UPDATE cay SET tencay='" + txtAddTenCay.getText() + "',giatien=" + txtAddGiaCay.getText() +",maloaicay='" + maloaicay  + "' WHERE macay='" + txtAddMaCay.getText()+"'");  
            DefaultTableModel modelcay = (DefaultTableModel)tblViewCay.getModel();
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

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int respone = JOptionPane.showConfirmDialog(this, "Do you want logout?","WARNING!!!",JOptionPane.YES_NO_OPTION);
        
        if(respone== JOptionPane.YES_OPTION){
            Login lg = new Login();
            lg.show();
            dispose();
        }else if(respone== JOptionPane.NO_OPTION){
            remove(respone);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        DefaultTableModel modelnhap = (DefaultTableModel)tblViewCay.getModel();
        modelnhap.setRowCount(0);
        view_cay();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {  
            Connection con = getConnection();     
            
            String username = txtUserName.getText();
            String cmnd = txtPassWord.getText();
            String password= txtPassWord.getText();
            String retype = txtRetypePass.getText();
            
            if(username.equals("") || cmnd.equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập cmnd hoặc username");                
            }
            else{
                if(password.equals("") || retype.equals("")){                    
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập lại Password");     
                }else {
                    if(password.equals(retype)){
                        if((txtCMND.getText()).length() == 12 ){
                            Statement statement = con.createStatement();
                            String sql = "INSERT INTO account VALUES('"+txtUserName.getText()+"','"+txtPassWord.getText()+"','"+txtCMND.getText()+"',"+"0)";
                            statement.executeUpdate(sql);  
                            DefaultTableModel modelacc = (DefaultTableModel)tblAccount.getModel();
                            modelacc.setRowCount(0);
                            view_account();
                            JOptionPane.showMessageDialog(null, "Thêm thành công!!!");  
                            statement.close();  
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại CMND");
                        }
                    }
                }              
            }
                    
            
            con.close();  
        //Referesh(); //Calling Referesh() method  
        } catch (SQLException | ClassNotFoundException e) {  
            JOptionPane.showMessageDialog(null, e);
            
        }
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCay;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateCay;
    private javax.swing.JComboBox<String> cbMaCay;
    private javax.swing.JComboBox<String> cbSearchLoaiCay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTable tblViewCay;
    private javax.swing.JTable tblViewNhap;
    private javax.swing.JTable tblViewXuat;
    private javax.swing.JTextField txtAddGiaCay;
    private javax.swing.JTextField txtAddMaCay;
    private javax.swing.JTextField txtAddTenCay;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtPassWord;
    private javax.swing.JTextField txtRetypePass;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
