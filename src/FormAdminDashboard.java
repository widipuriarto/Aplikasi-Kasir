
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class FormAdminDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormAdminDashboard.class.getName());
    
    public Statement st;
    public ResultSet rs;
    Connection conn = koneksi.Koneksi.BukaKoneksi();
    
     private int idUserLogin;
     private String namaUser;
     private String role;
    /**
     * Creates new form FormMenu
     */
    public FormAdminDashboard(String namaUser, int idUser, String role) {
        setTitle("Form Dashboard Admin");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        tampilkanStatistikDashboard();
        
        lblUsername.setText(namaUser + "!");
        lblRole.setText("Role: " + role);
        this.idUserLogin = idUser;
        this.namaUser = namaUser;
        this.role = role;
        
        if (role.equalsIgnoreCase("admin")) {
            tampilkanDashboard(); // tampilkan total hanya untuk admin
        }
    }

    
    public FormAdminDashboard(){
        
    }
    
    private void tampilkanDashboard() {
        try {
            st = conn.createStatement();
            
            // 1. Total Earning dari subtotal transaksi
            ResultSet rsEarning = st.executeQuery("SELECT SUM(subtotal) AS totalEarning FROM transaksi");
            if (rsEarning.next()) {
                int total = rsEarning.getInt("totalEarning");

                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                String formatted = formatRupiah.format(total);

            }

            // 2. Total Cashier dari users yang rolenya "kasir"
            ResultSet rsKasir = st.executeQuery("SELECT COUNT(*) AS totalKasir FROM users WHERE role = 'kasir'");
            if (rsKasir.next()) {
                lblTotalCashier.setText(String.valueOf(rsKasir.getInt("totalKasir")));
            }

            // 3. Total Item dari tabel barang
            ResultSet rsItem = st.executeQuery("SELECT COUNT(*) AS totalItem FROM barang");
            if (rsItem.next()) {
                lblTotalItem.setText(String.valueOf(rsItem.getInt("totalItem")));
            }

            // 4. Total Transaksi dari jumlah baris di tabel transaksi
            ResultSet rsTransaksi = st.executeQuery("SELECT COUNT(*) AS totalTransaksi FROM transaksi");
            if (rsTransaksi.next()) {
                lblTotalTransaction.setText(String.valueOf(rsTransaksi.getInt("totalTransaksi")));
            }

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan total: " + e.getMessage());
        }
    }

    private void tampilkanStatistikDashboard() {
        try {
            st = conn.createStatement();

            // 1. Tetapkan rentang waktu: dari 1 Mei 2025 sampai hari ini
            String tanggalAwal = "2025-07-01";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tanggalAkhir = LocalDate.now().format(formatter);

            // 3. Hitung income dari tabel transaksi
            int income = 0;
            ResultSet rsIncome = st.executeQuery("SELECT SUM(subtotal) AS total FROM transaksi WHERE tanggal >= '" + tanggalAwal + "' AND tanggal <= '" + tanggalAkhir + "'");
            if (rsIncome.next()) {
                income = rsIncome.getInt("total");
            }

            // 4. Hitung outcome dari tabel pembelian
            int outcome = 0;
            ResultSet rsOutcome = st.executeQuery("SELECT SUM(totalHarga) AS total FROM pembelian WHERE tanggal >= '" + tanggalAwal + "' AND tanggal <= '" + tanggalAkhir + "'");
            if (rsOutcome.next()) {
                outcome = rsOutcome.getInt("total");
            }

            // 5. Hitung saldo akhir
            int laba = income - outcome;

            // 6. Format dan tampilkan ke label
            Locale locale = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(locale);
            lblTotalIncome.setText(formatRupiah.format(income));
            lblTotalOutcome.setText(formatRupiah.format(outcome));
            lblLaba.setText(formatRupiah.format(laba));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
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

        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lblRole = new javax.swing.JLabel();
        btnLogout1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTotalTransaction = new javax.swing.JLabel();
        lblTransaction = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblItem = new javax.swing.JLabel();
        lblTotalItem = new javax.swing.JLabel();
        lblEarning1 = new javax.swing.JLabel();
        lblEarning3 = new javax.swing.JLabel();
        lblEarning4 = new javax.swing.JLabel();
        lblEarning6 = new javax.swing.JLabel();
        lblEarning7 = new javax.swing.JLabel();
        lblEarning8 = new javax.swing.JLabel();
        lblTotalIncome = new javax.swing.JLabel();
        lblTotalOutcome = new javax.swing.JLabel();
        lblLaba = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblCashier4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblCashier = new javax.swing.JLabel();
        lblTotalCashier = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnTransaction = new javax.swing.JButton();
        btnTransactionReport = new javax.swing.JButton();
        btnCashier = new javax.swing.JButton();
        lblCashier2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnManageProduct = new javax.swing.JButton();
        btnProduct = new javax.swing.JButton();
        lblCashier3 = new javax.swing.JLabel();
        btnStokMutasi1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lblCashier1 = new javax.swing.JLabel();
        btnItemSupplier = new javax.swing.JButton();
        btnPembelian1 = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnLaporan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblRole.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        lblRole.setForeground(new java.awt.Color(0, 153, 153));
        lblRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRole.setText("!");

        btnLogout1.setBackground(new java.awt.Color(0, 153, 153));
        btnLogout1.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btnLogout1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout1.setText("Logout");
        btnLogout1.setBorder(null);
        btnLogout1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogout1ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        lblTotalTransaction.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        lblTotalTransaction.setForeground(new java.awt.Color(0, 153, 153));
        lblTotalTransaction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalTransaction.setText("!");

        lblTransaction.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblTransaction.setForeground(new java.awt.Color(0, 153, 153));
        lblTransaction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransaction.setText("Total Transaction");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTransaction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalTransaction)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        lblItem.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblItem.setForeground(new java.awt.Color(0, 153, 153));
        lblItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblItem.setText("Total Item");

        lblTotalItem.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        lblTotalItem.setForeground(new java.awt.Color(0, 153, 153));
        lblTotalItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalItem.setText("!");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblItem, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(lblTotalItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(lblItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalItem)
                .addContainerGap())
        );

        lblEarning1.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblEarning1.setForeground(new java.awt.Color(0, 153, 153));
        lblEarning1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEarning1.setText("Total Outcome");

        lblEarning3.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblEarning3.setForeground(new java.awt.Color(0, 153, 153));
        lblEarning3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEarning3.setText("Total Income");

        lblEarning4.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblEarning4.setForeground(new java.awt.Color(0, 153, 153));
        lblEarning4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEarning4.setText("Laba");

        lblEarning6.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        lblEarning6.setForeground(new java.awt.Color(0, 153, 153));
        lblEarning6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEarning6.setText(":");

        lblEarning7.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        lblEarning7.setForeground(new java.awt.Color(0, 153, 153));
        lblEarning7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEarning7.setText(":");

        lblEarning8.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        lblEarning8.setForeground(new java.awt.Color(0, 153, 153));
        lblEarning8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEarning8.setText(":");

        lblTotalIncome.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblTotalIncome.setForeground(new java.awt.Color(0, 153, 153));
        lblTotalIncome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalIncome.setText("!");

        lblTotalOutcome.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblTotalOutcome.setForeground(new java.awt.Color(0, 153, 153));
        lblTotalOutcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalOutcome.setText("!");

        lblLaba.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblLaba.setForeground(new java.awt.Color(0, 153, 153));
        lblLaba.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLaba.setText("!");

        lblCashier4.setFont(new java.awt.Font("Tw Cen MT", 1, 22)); // NOI18N
        lblCashier4.setForeground(new java.awt.Color(0, 153, 153));
        lblCashier4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCashier4.setText("Statistics Dashboard Shop Pedia");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        lblCashier.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblCashier.setForeground(new java.awt.Color(0, 153, 153));
        lblCashier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCashier.setText("Total Cashier");

        lblTotalCashier.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        lblTotalCashier.setForeground(new java.awt.Color(0, 153, 153));
        lblTotalCashier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalCashier.setText("!");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTotalCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblCashier, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCashier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalCashier)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(lblCashier4)
                        .addGap(107, 107, 107))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lblEarning3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEarning6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lblEarning1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEarning7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTotalOutcome, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(lblTotalIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lblEarning4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEarning8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblLaba, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(31, 31, 31))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblCashier4)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEarning3)
                            .addComponent(lblEarning6)
                            .addComponent(lblTotalIncome))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEarning1)
                            .addComponent(lblEarning7)
                            .addComponent(lblTotalOutcome))
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEarning4)
                            .addComponent(lblLaba)
                            .addComponent(lblEarning8)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        btnTransaction.setBackground(new java.awt.Color(0, 153, 153));
        btnTransaction.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnTransaction.setText("Transaction");
        btnTransaction.setBorder(null);
        btnTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransactionActionPerformed(evt);
            }
        });

        btnTransactionReport.setBackground(new java.awt.Color(0, 153, 153));
        btnTransactionReport.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnTransactionReport.setForeground(new java.awt.Color(255, 255, 255));
        btnTransactionReport.setText("Transaction Report");
        btnTransactionReport.setBorder(null);
        btnTransactionReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransactionReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransactionReportActionPerformed(evt);
            }
        });

        btnCashier.setBackground(new java.awt.Color(0, 153, 153));
        btnCashier.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnCashier.setForeground(new java.awt.Color(255, 255, 255));
        btnCashier.setText("Cashier");
        btnCashier.setBorder(null);
        btnCashier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashierActionPerformed(evt);
            }
        });

        lblCashier2.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblCashier2.setForeground(new java.awt.Color(0, 153, 153));
        lblCashier2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCashier2.setText("Transaction");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransactionReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCashier2)
                .addGap(51, 51, 51))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblCashier2)
                .addGap(18, 18, 18)
                .addComponent(btnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransactionReport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        btnManageProduct.setBackground(new java.awt.Color(0, 153, 153));
        btnManageProduct.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnManageProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnManageProduct.setText("Manage Product");
        btnManageProduct.setBorder(null);
        btnManageProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManageProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageProductActionPerformed(evt);
            }
        });

        btnProduct.setBackground(new java.awt.Color(0, 153, 153));
        btnProduct.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnProduct.setText("Product");
        btnProduct.setBorder(null);
        btnProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });

        lblCashier3.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblCashier3.setForeground(new java.awt.Color(0, 153, 153));
        lblCashier3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCashier3.setText("Management Product");

        btnStokMutasi1.setBackground(new java.awt.Color(0, 153, 153));
        btnStokMutasi1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnStokMutasi1.setForeground(new java.awt.Color(255, 255, 255));
        btnStokMutasi1.setText("Stok Mutasi");
        btnStokMutasi1.setBorder(null);
        btnStokMutasi1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStokMutasi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStokMutasi1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnManageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStokMutasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lblCashier3)
                        .addGap(14, 14, 14))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblCashier3)
                .addGap(18, 18, 18)
                .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStokMutasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        lblCashier1.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        lblCashier1.setForeground(new java.awt.Color(0, 153, 153));
        lblCashier1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCashier1.setText("Restock & Supplier");

        btnItemSupplier.setBackground(new java.awt.Color(0, 153, 153));
        btnItemSupplier.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnItemSupplier.setForeground(new java.awt.Color(255, 255, 255));
        btnItemSupplier.setText("Buy Item");
        btnItemSupplier.setBorder(null);
        btnItemSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnItemSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemSupplierActionPerformed(evt);
            }
        });

        btnPembelian1.setBackground(new java.awt.Color(0, 153, 153));
        btnPembelian1.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnPembelian1.setForeground(new java.awt.Color(255, 255, 255));
        btnPembelian1.setText("History Transaction");
        btnPembelian1.setBorder(null);
        btnPembelian1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPembelian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembelian1ActionPerformed(evt);
            }
        });

        btnSupplier.setBackground(new java.awt.Color(0, 153, 153));
        btnSupplier.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnSupplier.setForeground(new java.awt.Color(255, 255, 255));
        btnSupplier.setText("Supplier");
        btnSupplier.setBorder(null);
        btnSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCashier1)
                    .addComponent(btnPembelian1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnItemSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblCashier1)
                .addGap(18, 18, 18)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnItemSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPembelian1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        btnLaporan.setBackground(new java.awt.Color(0, 153, 153));
        btnLaporan.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporan.setText("Laporan");
        btnLaporan.setBorder(null);
        btnLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(275, 275, 275))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblRole)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(btnLogout1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 870, 800));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dashboard");

        lblUsername.setBackground(new java.awt.Color(255, 255, 255));
        lblUsername.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(299, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(295, 295, 295))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 80));

        setSize(new java.awt.Dimension(878, 887));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnItemSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemSupplierActionPerformed
        // TODO add your handling code here:
        new FormItemSupplier(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnItemSupplierActionPerformed

    private void btnStokMutasi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStokMutasi1ActionPerformed
        // TODO add your handling code here:
        new FormStokMutasi(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnStokMutasi1ActionPerformed

    private void btnPembelian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembelian1ActionPerformed
        // TODO add your handling code here:
        new FormPembelian(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPembelian1ActionPerformed

    private void btnCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashierActionPerformed
        // TODO add your handling code here:
        new FormCashier(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCashierActionPerformed

    private void btnTransactionReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransactionReportActionPerformed
        // TODO add your handling code here:
        new FormReportTransaction(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTransactionReportActionPerformed

    private void btnManageProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageProductActionPerformed
        // TODO add your handling code here:
        new FormBarang(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageProductActionPerformed

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        // TODO add your handling code here:
        new FormDaftarBarang(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProductActionPerformed

    private void btnLogout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout1ActionPerformed
        // TODO add your handling code here:
        new FormLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogout1ActionPerformed

    private void btnTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransactionActionPerformed
        // TODO add your handling code here:
        new FormTransaksi(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTransactionActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        // TODO add your handling code here:
        new FormLaporanLabaRugi(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        // TODO add your handling code here:
        new FormSupplier(namaUser, idUserLogin, role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSupplierActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FormMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCashier;
    private javax.swing.JButton btnItemSupplier;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout1;
    private javax.swing.JButton btnManageProduct;
    private javax.swing.JButton btnPembelian1;
    private javax.swing.JButton btnProduct;
    private javax.swing.JButton btnStokMutasi1;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaction;
    private javax.swing.JButton btnTransactionReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCashier;
    private javax.swing.JLabel lblCashier1;
    private javax.swing.JLabel lblCashier2;
    private javax.swing.JLabel lblCashier3;
    private javax.swing.JLabel lblCashier4;
    private javax.swing.JLabel lblEarning1;
    private javax.swing.JLabel lblEarning3;
    private javax.swing.JLabel lblEarning4;
    private javax.swing.JLabel lblEarning6;
    private javax.swing.JLabel lblEarning7;
    private javax.swing.JLabel lblEarning8;
    private javax.swing.JLabel lblItem;
    private javax.swing.JLabel lblLaba;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTotalCashier;
    private javax.swing.JLabel lblTotalIncome;
    private javax.swing.JLabel lblTotalItem;
    private javax.swing.JLabel lblTotalOutcome;
    private javax.swing.JLabel lblTotalTransaction;
    private javax.swing.JLabel lblTransaction;
    private javax.swing.JLabel lblUsername;
    // End of variables declaration//GEN-END:variables
}
