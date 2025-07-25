
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class FormReportTransaction extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormDaftarBarang.class.getName());

    public Statement st;
    public ResultSet rs;
    Connection conn = koneksi.Koneksi.BukaKoneksi();
    
    private int idUserLogin;
    private String namaUser;
    private String role;
    
    /**
     * Creates new form FormDaftarBarang
     */
    public FormReportTransaction(String namaUser, int idUserLogin, String role) {
        setTitle("Form Report Transaction");
        initComponents();
        
        this.idUserLogin = idUserLogin;
        this.namaUser = namaUser;
        this.role = role;
        
        TampilData();
    }

    private FormReportTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void TampilData(){
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM transaksi");
            
            DefaultTableModel model = new DefaultTableModel();
            
            model.addColumn("Transaction ID");
            model.addColumn("User ID");
            model.addColumn("Date");
            model.addColumn("Item Code");
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
                    rs.getInt("idUser"),
                    rs.getString("tanggal"),
                    rs.getInt("kodeBarang"),
                    rs.getString("namaBarang"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah"),
                    rs.getInt("subtotal"),
                };
                model.addRow(data);
            }
            tblTransaction.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
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
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaction = new javax.swing.JTable();
        btnPrint = new javax.swing.JButton();
        dateTo = new com.toedter.calendar.JDateChooser();
        dateFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Report Transaction");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel1)
                .addContainerGap(340, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnBack.setBackground(new java.awt.Color(0, 153, 153));
        btnBack.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblTransaction.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        tblTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "User ID", "Date", "Item Code", "Item Name", "Price", "Quantity", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tblTransaction);

        btnPrint.setBackground(new java.awt.Color(0, 153, 153));
        btnPrint.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Print");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Select time range.");

        btnFilter.setBackground(new java.awt.Color(0, 153, 153));
        btnFilter.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnFilter.setForeground(new java.awt.Color(255, 255, 255));
        btnFilter.setText("Filter");
        btnFilter.setBorder(null);
        btnFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 153, 153));
        btnClear.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.setBorder(null);
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64)
                            .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 910, 670));

        setSize(new java.awt.Dimension(916, 735));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            // Pastikan dateFrom dan dateTo sudah dipilih
            if (dateFrom.getDate() == null || dateTo.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Silakan pilih rentang tanggal terlebih dahulu.");
                return;
            }

            // Format tanggal
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = sdf.format(dateFrom.getDate());
            String toDate = sdf.format(dateTo.getDate());

            // Ambil file laporan dari classpath
            InputStream reportStream = getClass().getResourceAsStream("/LaporanPenjualan.jasper");
            if (reportStream == null) {
                throw new FileNotFoundException("File laporan tidak ditemukan!");
            }

            // Siapkan parameter
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("fromDate", fromDate);
            parameters.put("toDate", toDate);

            // Cetak laporan
            JasperPrint print = JasperFillManager.fillReport(reportStream, parameters, conn);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mencetak laporan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
            try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = sdf.format(dateFrom.getDate());
            String toDate = sdf.format(dateTo.getDate());

            if (dateFrom.getDate() == null || dateTo.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Silakan pilih rentang tanggal terlebih dahulu.");
                return;
            }
            
            // Tampilkan periode
            SimpleDateFormat sdfPeriode = new SimpleDateFormat("MMMM yyyy", new Locale("in", "ID"));
            String namaBulan = sdfPeriode.format(dateFrom.getDate()) + " - " + sdfPeriode.format(dateTo.getDate());
            
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM transaksi WHERE tanggal BETWEEN '" + fromDate + "' AND '" + toDate + "'");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Transaction ID");
            model.addColumn("User ID");
            model.addColumn("Date");
            model.addColumn("Item Code");
            model.addColumn("Item Name");
            model.addColumn("Price");
            model.addColumn("Quantity");
            model.addColumn("Subtotal");

            model.setRowCount(0);

            while(rs.next()) {
                Object[] data = {
                    rs.getInt("idTransaksi"),
                    rs.getInt("idUser"),
                    rs.getString("tanggal"),
                    rs.getInt("kodeBarang"),
                    rs.getString("namaBarang"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah"),
                    rs.getInt("subtotal"),
                };
                model.addRow(data);
            }
            tblTransaction.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memfilter data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        dateFrom.setDate(null);
        dateTo.setDate(null);
        TampilData();
    }//GEN-LAST:event_btnClearActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FormReportTransaction().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnPrint;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTransaction;
    // End of variables declaration//GEN-END:variables
}
