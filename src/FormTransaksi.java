
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class FormTransaksi extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormTransaksi.class.getName());

    public Statement st;
    public ResultSet rs;
    Connection conn = koneksi.Koneksi.BukaKoneksi();
    
    private int idUserLogin;
    private String namaUser;
    private String role;
    
    /**
     * Creates new form FormTransaksi
     */
    public FormTransaksi(String namaUser, int idUser, String role) {
        setTitle("Form Transaction");
        initComponents();
        
        TampilData();
        this.idUserLogin = idUser;
        this.namaUser = namaUser;
        this.role = role;
    }

    private FormTransaksi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void Clear(){
        txtTransactionID.setText("");
        txtUserID.setText("");
        txtDate.setText("");
        txtItemCode.setText("");
        txtItemName.setText("");
        txtStock.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtTotal.setText("");
        
        txtItemCode.setEditable(true);
    }
    
    private void TampilData(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM keranjang");
            
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Transaction ID");
            model.addColumn("Item Name");
            model.addColumn("Price");
            model.addColumn("Quantity");
            model.addColumn("Subtotal");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while(rs.next()) {
                Object[] data = {
                    rs.getInt("idTransaksi"),
                    rs.getString("namaBarang"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah"),
                    rs.getInt("subtotal"),
                };
                model.addRow(data);
            }
            tblTransaction.setModel(model);
            tampilkanTotalSubtotal();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }

    private void generateTransactionID() {
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT MAX(idTransaksi) AS max_id FROM transaksi");

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

    private void tampilkanTotalSubtotal() {
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(subtotal) AS total FROM keranjang");
            if (rs.next()) {
                int total = rs.getInt("total");

                txtSubtotal.setText(String.valueOf(total));
                if (total == 0) {
                    txtSubtotal.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil total: " + e.getMessage());
        }
    }
    
    private void hitungKembalian() {
        try {
            // Ambil nilai dari text field
            String subtotalText = txtSubtotal.getText().replaceAll("[^\\d]", ""); // hapus karakter selain angka
            String cashText = txtCash.getText().replaceAll("[^\\d]", "");

            if (!subtotalText.isEmpty() && !cashText.isEmpty()) {
                int subtotal = Integer.parseInt(subtotalText);
                int cash = Integer.parseInt(cashText);

                if (cash >= subtotal) {
                    int change = cash - subtotal;
                    txtChange.setText(String.valueOf(change));

                    // Aktifkan tombol print
                    btnPrint.setEnabled(true);
                }
            } else {
                txtChange.setText("");
                btnPrint.setEnabled(false);
            }
        } catch (NumberFormatException e) {
            txtChange.setText("Input tidak valid");
            btnPrint.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTransactionID = new javax.swing.JTextField();
        txtUserID = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaction = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnItemList = new javax.swing.JButton();
        btnAddTransaction = new javax.swing.JButton();
        txtDate = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCash = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtChange = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SALES TRANSACTION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel1)
                .addContainerGap(261, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 60));

        jPanel2.setBackground(new java.awt.Color(253, 253, 253));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Transaction ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Date");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("User ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Item Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Stock");

        txtTransactionID.setEditable(false);
        txtTransactionID.setBackground(new java.awt.Color(235, 235, 235));
        txtTransactionID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtTransactionID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransactionIDActionPerformed(evt);
            }
        });

        txtUserID.setEditable(false);
        txtUserID.setBackground(new java.awt.Color(235, 235, 235));
        txtUserID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        txtItemName.setBackground(new java.awt.Color(235, 235, 235));
        txtItemName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        txtStock.setEditable(false);
        txtStock.setBackground(new java.awt.Color(235, 235, 235));
        txtStock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        btnPrint.setBackground(new java.awt.Color(0, 153, 153));
        btnPrint.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(253, 253, 253));
        btnPrint.setText("Print");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setEnabled(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 153, 153));
        btnReset.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(253, 253, 253));
        btnReset.setText("Reset");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(0, 153, 153));
        btnSave.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(253, 253, 253));
        btnSave.setText("Save");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tblTransaction.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        tblTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Item Name", "Price", "Quantity", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tblTransaction);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Item Code");

        txtItemCode.setEditable(false);
        txtItemCode.setBackground(new java.awt.Color(235, 235, 235));
        txtItemCode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtItemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtItemCodeKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Price");

        txtPrice.setEditable(false);
        txtPrice.setBackground(new java.awt.Color(235, 235, 235));
        txtPrice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Quantity");

        txtQuantity.setEditable(false);
        txtQuantity.setBackground(new java.awt.Color(235, 235, 235));
        txtQuantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(253, 253, 253));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(235, 235, 235));
        txtTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        btnItemList.setBackground(new java.awt.Color(0, 153, 153));
        btnItemList.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnItemList.setForeground(new java.awt.Color(253, 253, 253));
        btnItemList.setText("Item List");
        btnItemList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnItemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemListActionPerformed(evt);
            }
        });

        btnAddTransaction.setBackground(new java.awt.Color(0, 153, 153));
        btnAddTransaction.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btnAddTransaction.setForeground(new java.awt.Color(253, 253, 253));
        btnAddTransaction.setText("Add");
        btnAddTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTransactionActionPerformed(evt);
            }
        });

        txtDate.setEditable(false);
        txtDate.setBackground(new java.awt.Color(235, 235, 235));
        txtDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(0, 153, 153));
        btnBack.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(253, 253, 253));
        btnBack.setText("Back");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(253, 253, 253));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Cash");

        txtCash.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashKeyReleased(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(253, 253, 253));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Change");

        txtChange.setEditable(false);
        txtChange.setBackground(new java.awt.Color(235, 235, 235));
        txtChange.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        jLabel13.setBackground(new java.awt.Color(253, 253, 253));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 153));
        jLabel13.setText("Subtotal");

        txtSubtotal.setEditable(false);
        txtSubtotal.setBackground(new java.awt.Color(235, 235, 235));
        txtSubtotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(txtCash)
                            .addComponent(txtChange)
                            .addComponent(jLabel13)
                            .addComponent(txtSubtotal)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUserID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                .addComponent(txtTransactionID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtItemCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(txtStock)
                            .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(txtTotal))))
                .addGap(62, 62, 62))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
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
                        .addComponent(txtStock))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 800, 740));

        setSize(new java.awt.Dimension(814, 808));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Clear();
        btnAddTransaction.setEnabled(true);
        txtItemCode.setEditable(false);
        txtItemCode.setBackground(new java.awt.Color(235,235,235));
        txtQuantity.setEditable(false);
        txtQuantity.setBackground(new java.awt.Color(235,235,235));
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnAddTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTransactionActionPerformed
        // TODO add your handling code here:
        // Set ID transaction
        generateTransactionID();
        // Set ID user
        txtUserID.setText(String.valueOf(idUserLogin));

        // Set tanggal saat ini
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        txtDate.setText(LocalDateTime.now().format(formatter));

        btnAddTransaction.setEnabled(false);
        txtItemCode.setEditable(true);
        txtItemCode.setBackground(new java.awt.Color(255, 255, 255));

    }//GEN-LAST:event_btnAddTransactionActionPerformed

    private void txtItemCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtItemCodeKeyReleased
        // TODO add your handling code here:
        try {
            String kode = txtItemCode.getText();

            if (kode.length() > 0) {
                st = conn.createStatement();
                String sql = "SELECT * FROM barang WHERE kodeBarang = '" + kode + "'";
                rs = st.executeQuery(sql);

                if (rs.next()) {
                    txtItemName.setText(rs.getString("namaBarang"));
                    txtPrice.setText(rs.getString("hargaJual"));
                    txtStock.setText(rs.getString("stok"));
                } else {
                    // Bersihkan jika tidak ditemukan
                    txtItemName.setText("");
                    txtPrice.setText("");
                    txtStock.setText("");
                }
            } else {
                // Bersihkan jika kosong
                txtItemName.setText("");
                txtPrice.setText("");
                txtStock.setText("");
            }
            
            if(!txtItemName.getText().equals("")){
                txtQuantity.setEditable(true);
                txtQuantity.setBackground(new java.awt.Color(255, 255, 255));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_txtItemCodeKeyReleased

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
         try {
            String priceText = txtPrice.getText().trim();
            String quantityText = txtQuantity.getText().trim();

            if (!priceText.isEmpty() && !quantityText.isEmpty()) {
                int price = Integer.parseInt(priceText);
                int quantity = Integer.parseInt(quantityText);
                int subtotal = price * quantity;

                txtTotal.setText(String.valueOf(subtotal)); // Menampilkan int biasa
            } else {
                txtTotal.setText(""); // Kosongkan jika belum lengkap
            }
        } catch (NumberFormatException e) {
            txtTotal.setText(""); // Kosongkan jika input tidak valid
        }
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try {
            st = conn.createStatement();
            
            
            if (txtTransactionID.getText().equals("") ||
                txtUserID.getText().equals("") ||
                txtDate.getText().equals("") ||
                txtItemCode.getText().equals("") ||
                txtItemName.getText().equals("") ||
                txtPrice.getText().equals("") ||
                txtQuantity.getText().equals("") ||
                txtTotal.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Data must not be empty", "Data Validation", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            String idTransaksi = txtTransactionID.getText();
            String idUser = txtUserID.getText();
            String date = txtDate.getText();
            String kode = txtItemCode.getText();
            String nama = txtItemName.getText();
            int harga = Integer.parseInt(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            int subtotal = Integer.parseInt(txtTotal.getText().replace("Rp", "").replace(".", "").replace(",", "").trim());
            
            if (!txtItemCode.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "The Item Code must be a number");
                return;
            }
        
            String cekStokQuery = "SELECT stok FROM barang WHERE kodeBarang = '" + kode + "'";
            ResultSet rsStok = st.executeQuery(cekStokQuery);
            if (rsStok.next()) {
                int stokSekarang = rsStok.getInt("stok");
                if (stokSekarang < quantity) {
                    JOptionPane.showMessageDialog(null, "Stok tidak mencukupi. Stok tersedia: " + stokSekarang);
                    return;
                }
            }
           
            String sql = "INSERT INTO transaksi VALUES ('" + idTransaksi + "',"
                + "'" + idUser + "',"
                + "'" + date + "',"
                + "'" + kode + "',"
                + "'" + nama + "',"
                + "'" + harga + "',"
                + "'" + quantity + "',"
                + "'" + subtotal + "')";
            st.execute(sql);
        
            String sqlKeranjang = "INSERT INTO keranjang VALUES ('" + idTransaksi + "',"
                + "'" + nama + "',"
                + "'" + harga + "',"
                + "'" + quantity + "',"
                + "'" + subtotal + "')";
            st.execute(sqlKeranjang);
            tampilkanTotalSubtotal();

            
            JOptionPane.showMessageDialog(null, "Data successfully saved!");
            
            String updateStok = "UPDATE barang SET stok = stok - " + quantity + " WHERE kodeBarang = '" + kode + "'";
            st.executeUpdate(updateStok);
            
            // Ambil stok terakhir setelah update
            int stokTerakhir = 0;
            ResultSet rsStokAkhir = st.executeQuery("SELECT stok FROM barang WHERE kodeBarang = '" + kode + "'");
            if (rsStokAkhir.next()) {
                stokTerakhir = rsStokAkhir.getInt("stok");
            }

            // Catat mutasi stok keluar ke tabel stok_mutasi
            String insertMutasi = "INSERT INTO stok_mutasi (tanggal, kodeBarang, namaBarang, tipe, jumlah, stokTerakhir) " +
                                  "VALUES ('" + date + "', " + kode + ", '" + nama + "', 'keluar', " + quantity + ", " + stokTerakhir + ")";
            st.executeUpdate(insertMutasi);
            
            Clear();
            TampilData();
            
            btnAddTransaction.setEnabled(true);
            txtItemCode.setEditable(false);
            txtItemCode.setBackground(new java.awt.Color(235,235,235));
            txtQuantity.setEditable(false);
            txtQuantity.setBackground(new java.awt.Color(235,235,235));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
           
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnItemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemListActionPerformed
        // TODO add your handling code here:
        new FormDaftarBarang(namaUser, idUserLogin, role).setVisible(true);
    }//GEN-LAST:event_btnItemListActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (role.equalsIgnoreCase("admin")) {
            new FormAdminDashboard(namaUser, idUserLogin, role).setVisible(true);
        }else{
            new FormMenu(namaUser, idUserLogin, role).setVisible(true);
        }
        
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try {
            InputStream reportStream = getClass().getResourceAsStream("/Invoice.jasper");
            if (reportStream == null) {
                throw new FileNotFoundException("File laporan tidak ditemukan!");
            }

            // Ambil nilai dan parsing sebagai Integer
            String subtotalText = txtSubtotal.getText().replaceAll("[^\\d]", "");
            String cashText = txtCash.getText().replaceAll("[^\\d]", "");
            String changeText = txtChange.getText().replaceAll("[^\\d]", "");

            int subtotal = Integer.parseInt(subtotalText);
            int cash = Integer.parseInt(cashText);
            int change = Integer.parseInt(changeText);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("subtotal", subtotal);
            parameters.put("cash", cash);     // kirim sebagai Integer
            parameters.put("change", change); // kirim sebagai Integer

            JasperPrint print = JasperFillManager.fillReport(reportStream, parameters, conn);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        // Kosongkan keranjang
        String sql = "TRUNCATE TABLE keranjang;";
        try {
            st.execute(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        // Reset form
        TampilData();
        txtSubtotal.setText("");
        txtCash.setText("");
        txtChange.setText("");
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyReleased
        // TODO add your handling code here:
        hitungKembalian();
    }//GEN-LAST:event_txtCashKeyReleased

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtTransactionIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransactionIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTransactionIDActionPerformed

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FormTransaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTransaction;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnItemList;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTransaction;
    private javax.swing.JTextField txtCash;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTransactionID;
    private javax.swing.JTextField txtUserID;
    // End of variables declaration//GEN-END:variables
}
