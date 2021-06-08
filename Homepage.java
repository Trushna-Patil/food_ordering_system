//imports used in this frame
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class Homepage extends javax.swing.JFrame {
    //declaring our table models which pertain to table structures
    DefaultTableModel model;
    DefaultTableModel model1;
    
    //declaring a new instance of our database class
    database a =new database();
    private Object DriverManger; 
            
    public Homepage() { //initial contructor (will automatically call initialize components method)
        initComponents();
        btnNewOrder.setToolTipText("This will create a new order");
        btnAdmin.setToolTipText("This will take you to the admin view (where credentials are necessary");
        //assigning the default table model to our two tables
        model = (DefaultTableModel) tblOrders.getModel();
        model1 = (DefaultTableModel) tblBill.getModel();
        //calling the method which will populate tables upon initialization
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system?allowPublicKeyRetrieval=true&useSSL=false","root","");
	    Statement s = c.createStatement();
            int srno=0;
            String query="select * from person_order";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                model1.insertRow(model1.getRowCount(), new Object[]{rs.getString("oid"),rs.getString("table_no"),rs.getString("emp_name"),rs.getString("price")});
            }
            String date= "";
            String query1 = "select current_date as date";
            ResultSet rs3 = s.executeQuery(query1);
            if(rs3.next())
            {
                date= rs3.getString("date");
            }
            String q="Select * from placed_order where date='"+date+"'";
            ResultSet rs1=s.executeQuery(q);
            while(rs1.next()){
                model1.insertRow(model1.getRowCount(), new Object[]{rs1.getString("or_id"),rs1.getString("table_no"),rs1.getString("emp_name"),rs1.getString("price")});
            }
            String query2="select * from person_order";
            ResultSet rs2=s.executeQuery(query2);
            while(rs2.next()){
                model.insertRow(model.getRowCount(), new Object[]{rs2.getString("oid"),rs2.getString("starter"),rs2.getString("main_course"),rs2.getString("dessert"),rs2.getString("drinks"),rs2.getString("price")});
            }
            
            
        }catch(Exception e ){
            System.out.println(e);
        }
        
    }
    

 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        btnNewOrder = new javax.swing.JButton();
        CheckProfit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        btnAdmin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Table No.", "Employee Name", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBill.getTableHeader().setReorderingAllowed(false);
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBill);
        if (tblBill.getColumnModel().getColumnCount() > 0) {
            tblBill.getColumnModel().getColumn(0).setResizable(false);
            tblBill.getColumnModel().getColumn(1).setResizable(false);
            tblBill.getColumnModel().getColumn(2).setResizable(false);
            tblBill.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 560, 130));

        btnNewOrder.setBackground(new java.awt.Color(255, 255, 255));
        btnNewOrder.setText("New Order");
        btnNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewOrderActionPerformed(evt);
            }
        });
        getContentPane().add(btnNewOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 100, 25));

        CheckProfit.setText("Check Profit");
        CheckProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckProfitActionPerformed(evt);
            }
        });
        getContentPane().add(CheckProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 130, 30));

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Order Number", "Starter", "Main", "Dessert", "Drink", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrders.setGridColor(new java.awt.Color(255, 255, 255));
        tblOrders.setRowSelectionAllowed(false);
        tblOrders.getTableHeader().setReorderingAllowed(false);
        tblOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOrders);
        if (tblOrders.getColumnModel().getColumnCount() > 0) {
            tblOrders.getColumnModel().getColumn(0).setResizable(false);
            tblOrders.getColumnModel().getColumn(1).setResizable(false);
            tblOrders.getColumnModel().getColumn(2).setResizable(false);
            tblOrders.getColumnModel().getColumn(3).setResizable(false);
            tblOrders.getColumnModel().getColumn(4).setResizable(false);
            tblOrders.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 560, 183));

        btnAdmin.setBackground(new java.awt.Color(255, 255, 255));
        btnAdmin.setText("Admin");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 100, 25));

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pending Orders");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Order Summary");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grey-Background-GREY.jpg"))); // NOI18N
        jLabel1.setName(""); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-7, -10, 590, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewOrderActionPerformed
        
        Order o = new Order();//creates 'o', a new object based off the Order class
        //controlling visibiiilty
        o.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_btnNewOrderActionPerformed

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked
       
    }//GEN-LAST:event_tblBillMouseClicked

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
         //creates a new instnace of the admin password frame and sets it to visible
        AdminPassword ap = new AdminPassword();
         ap.setVisible(true);
         this.setVisible(false);
    }//GEN-LAST:event_btnAdminActionPerformed

    private void tblOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdersMouseClicked
        
    }//GEN-LAST:event_tblOrdersMouseClicked

    private void CheckProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckProfitActionPerformed
        // TODO add your handling code here:
        new checkProfit().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CheckProfitActionPerformed

    
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
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckProfit;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnNewOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBill;
    private javax.swing.JTable tblOrders;
    // End of variables declaration//GEN-END:variables

    private database createConnection(String jdbcmysqllocalhost3306dborderingsystem, String root, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
