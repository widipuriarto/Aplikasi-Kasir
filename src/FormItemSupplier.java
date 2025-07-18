
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class FormItemSupplier extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormCashier.class.getName());

    public Statement st;
    public ResultSet rs;
    Connection conn = koneksi.Koneksi.BukaKoneksi();
    
    private int idUserLogin;
    private String namaUser;
    private String role;
    
    /**
     * Creates new form FormDaftarBarang
     */
    public FormItemSupplier(String namaUser, int idUserLogin, String role) {
        setTitle("Form Item Supplier");
        initComponents();
        
        this.idUserLogin = idUserLogin;
        this.namaUser = namaUser;
        this.role = role;
        
        TampilData();
    }

    private FormItemSupplier() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void TampilData(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM supplier_barang");
            
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Supplier ID");
            model.addColumn("Item Code");
            model.addColumn("Item Name");
            model.addColumn("Price");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while(rs.next()) {
                Object[] data = {
                    rs.getString("idSupplier"),
                    rs.getString("kodeBarang"),
                    rs.getString("namaBarang"),
                    rs.getString("hargaSatuan")
                };
                model.addRow(data);
                tblItemSupplier.setModel(model);
            }
            
        } catch (Exception e) {
        }
    }
    
    private void CariData(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM supplier_barang WHERE namaBarang"
                    + " LIKE '%" + txtSearch.getText() + "%'");
            
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Supplier ID");
            model.addColumn("Item Code");
            model.addColumn("Item Name");
            model.addColumn("Price");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while (rs.next()) {
                Object[] data = {
                    rs.getString("idSupplier"),
                    rs.getString("kodeBarang"),
                    rs.getString("namaBarang"),
                    rs.getString("hargaSatuan")
                };
                model.addRow(data);
                tblItemSupplier.setModel(model);
            }
        } catch (Exception e) {
        }
    }
    
    private void Clear(){
        txtTransactionID.setText("");
        txtSupplierID.setText("");
        txtSupplierName.setText("");
        txtItemCode.setText("");
        txtDate.setText("");
        txtItemName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtSubtotal.setText("");
        
        btnCancel.setEnabled(false);
    }
    
    private void generateTransactionID() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT MAX(idPembelian) AS max_id FROM pembelian");

            int newID = 1; // default jika tabel masih kosong
            if (rs.next()) {
                newID = rs.getInt("max_id") + 1;
            }

            txtTransactionID.setText(String.valueOf(newID));
            txtTransactionID.setEditable(false); // opsional: tidak bisa diedit manual

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating Transaction ID: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemSupplier = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTransactionID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSupplierID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtSupplierName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        btnBuy = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buy Item");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(329, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(321, 321, 321))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblItemSupplier.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        tblItemSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Supplier ID", "Item Code", "Item Name", "Price"
            }
        ));
        tblItemSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemSupplier);

        btnBack.setBackground(new java.awt.Color(0, 153, 153));
        btnBack.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        txtSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 153, 153));
        btnSearch.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnSearchFocusLost(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Select the items you want to buy.");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Transaction ID");

        txtTransactionID.setEditable(false);
        txtTransactionID.setBackground(new java.awt.Color(235, 235, 235));
        txtTransactionID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Item Name");

        txtItemName.setBackground(new java.awt.Color(235, 235, 235));
        txtItemName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtItemName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtItemNameCaretUpdate(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Supplier ID");

        txtSupplierID.setEditable(false);
        txtSupplierID.setBackground(new java.awt.Color(235, 235, 235));
        txtSupplierID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Price");

        txtPrice.setEditable(false);
        txtPrice.setBackground(new java.awt.Color(235, 235, 235));
        txtPrice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        txtSupplierName.setEditable(false);
        txtSupplierName.setBackground(new java.awt.Color(235, 235, 235));
        txtSupplierName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Supplier Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Quantity");

        txtQuantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Item Code");

        txtItemCode.setEditable(false);
        txtItemCode.setBackground(new java.awt.Color(235, 235, 235));
        txtItemCode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Subtotal");

        txtSubtotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Date");

        txtDate.setEditable(false);
        txtDate.setBackground(new java.awt.Color(235, 235, 235));
        txtDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        btnBuy.setBackground(new java.awt.Color(0, 153, 153));
        btnBuy.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnBuy.setForeground(new java.awt.Color(255, 255, 255));
        btnBuy.setText("Buy");
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(0, 153, 153));
        btnCancel.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSupplierID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTransactionID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSupplierName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtItemCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTransactionID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 760, 740));

        setSize(new java.awt.Dimension(774, 805));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new FormAdminDashboard(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnSearchFocusLost
        // TODO add your handling code here:
        TampilData();
    }//GEN-LAST:event_btnSearchFocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        CariData();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtItemNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtItemNameCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameCaretUpdate

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed
        // TODO add your handling code here:
        
        if (txtTransactionID.getText().equals("") ||
                txtSupplierID.getText().equals("") ||
                txtSupplierName.getText().equals("") ||
                txtItemCode.getText().equals("") ||
                txtDate.getText().equals("") ||
                txtItemName.getText().equals("") ||
                txtPrice.getText().equals("") ||
                txtQuantity.getText().equals("") ||
                txtSubtotal.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Data must not be empty", "Data Validation", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        
        int kodeBarang = Integer.parseInt(txtItemCode.getText());
        int idSupplier = Integer.parseInt(txtSupplierID.getText());
        String namaBarang = txtItemName.getText();
        int hargaBeli = Integer.parseInt(txtPrice.getText());
        int jumlah = Integer.parseInt(txtQuantity.getText());
        int totalHarga = Integer.parseInt(txtSubtotal.getText());
        String tanggal = txtDate.getText();

        try {
            Statement st = conn.createStatement();

            // 1. Simpan ke tabel pembelian
            String sqlPembelian = "INSERT INTO pembelian (kodeBarang, idSupplier, jumlah, hargaBeli, totalHarga, tanggal) " +
                                  "VALUES (" + kodeBarang + ", " + idSupplier + ", " + jumlah + ", " + hargaBeli + ", " + totalHarga + ", '" + tanggal + "')";
            st.executeUpdate(sqlPembelian);

            // 2. Update ke tabel barang (jika belum ada, insert)
            ResultSet rsBarang = st.executeQuery("SELECT * FROM barang WHERE kodeBarang = " + kodeBarang);
            if (rsBarang.next()) {
                int stokLama = rsBarang.getInt("stok");
                int stokBaru = stokLama + jumlah;
                st.executeUpdate("UPDATE barang SET stok = " + stokBaru + " WHERE kodeBarang = " + kodeBarang);
            } else {
                st.executeUpdate("INSERT INTO barang (kodeBarang, namaBarang, hargaBeli, stok) " +
                                 "VALUES (" + kodeBarang + ", '" + namaBarang + "', " + hargaBeli + ", " + jumlah + ")");
            }

            // 3. Simpan ke tabel stok_mutasi
            ResultSet rsStok = st.executeQuery("SELECT stok FROM barang WHERE kodeBarang = " + kodeBarang);
            int stokTerakhir = 0;
            if (rsStok.next()) {
                stokTerakhir = rsStok.getInt("stok");
            }

            st.executeUpdate("INSERT INTO stok_mutasi (tanggal, kodeBarang, namaBarang, tipe, jumlah, stokTerakhir) " +
                             "VALUES ('" + tanggal + "', " + kodeBarang + ", '" + namaBarang + "', 'masuk', " + jumlah + ", " + stokTerakhir + ")");

            JOptionPane.showMessageDialog(this, "Pembelian berhasil disimpan!");

            Clear();
            TampilData(); // jika ada JTable pembelian

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan pembelian: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuyActionPerformed

    private void tblItemSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemSupplierMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblItemSupplier.getSelectedRow();
        if (selectedRow >= 0) {
            // Ambil data dari tabel (dalam bentuk model JTable)
            int idSupplier = Integer.parseInt(tblItemSupplier.getValueAt(selectedRow, 0).toString()); // kolom idSupplier
            int kodeBarang = Integer.parseInt(tblItemSupplier.getValueAt(selectedRow, 1).toString()); // kolom kodeBarang
            String namaBarang = tblItemSupplier.getValueAt(selectedRow, 2).toString();
            int hargaSatuan = Integer.parseInt(tblItemSupplier.getValueAt(selectedRow, 3).toString());

            // Set ID transaction
            generateTransactionID();
            txtItemCode.setText(String.valueOf(kodeBarang));
            txtItemName.setText(namaBarang);
            txtPrice.setText(String.valueOf(hargaSatuan));
            txtSupplierID.setText(String.valueOf(idSupplier));
            
            // Set tanggal saat ini
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            txtDate.setText(LocalDateTime.now().format(formatter));

            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT namaSupplier FROM supplier WHERE idSupplier = " + idSupplier);
                if (rs.next()) {
                    txtSupplierName.setText(rs.getString("namaSupplier"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal mengambil nama supplier: " + e.getMessage());
            }

            // Isi tanggal otomatis
            txtDate.setText(java.time.LocalDate.now().toString());
        }
        
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_tblItemSupplierMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        Clear();
        btnCancel.setEnabled(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
        try {
            int harga = Integer.parseInt(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            int totalHarga = harga * quantity;
            txtSubtotal.setText(String.valueOf(totalHarga));
        } catch (NumberFormatException e) {
            txtSubtotal.setText("");
        }
    }//GEN-LAST:event_txtQuantityKeyReleased

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormItemSupplier().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblItemSupplier;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtSupplierID;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JTextField txtTransactionID;
    // End of variables declaration//GEN-END:variables
}
