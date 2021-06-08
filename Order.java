
import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 113329631
 */
public class Order extends javax.swing.JFrame { 

    public double runningCost;
    public double rowCost;
    //Declaring a new instance of classes
    Starter s = new Starter();
    Main m = new Main();
    Dessert ds = new Dessert();
    Drink dr = new Drink();
    TotalPrice p = new TotalPrice();
    Person_Order o = new Person_Order();
    //Declaring a new instance of database class
    database a = new database();
    //Declaring a new instance of random class to use for order number
    Random rand = new Random();
    int x = rand.nextInt(10000);
    public String xx = Integer.toString(x);
    //declaring our table models which pertain to table structures
    DefaultTableModel model;
    DefaultTableModel Startermodel;
    DefaultTableModel Mainmodel;
    DefaultTableModel Dessertmodel;
    DefaultTableModel Drinkmodel;
     
    public Order(){
        initComponents(); 
        //a.dropTable1();
       //a.createTable1();
        //a.dropTable2();
        //a.createTable2();
        //This will stop the user exiting the program mid order as this will cause an inconsistency in the database tables
        txtRowCost.setVisible(false);
        Startermodel =  (DefaultTableModel) tblStarters.getModel();
        Mainmodel =     (DefaultTableModel) tblMains.getModel();
        Dessertmodel =  (DefaultTableModel) tblDesserts.getModel();
        Drinkmodel =    (DefaultTableModel) tblDrinks.getModel();
        txtFinish.setEnabled(false);
        btnDelete.setEnabled(false);
       
       
        model = (DefaultTableModel) tblOrder.getModel(); 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system?allowPublicKeyRetrieval=true&useSSL=false","root","");
	    Statement s = c.createStatement();
            int id=0;
            String q="Select *  from person_order";
            ResultSet rs=s.executeQuery(q);
            while(rs.next()){
                model.insertRow(model.getRowCount(), new Object[]{rs.getString("oid"),rs.getString("starter"),rs.getString("main_course"),rs.getString("dessert"),rs.getString("drinks"),rs.getString("price")});
            }
            
        }catch(Exception e ){
            System.out.println(e);
        }
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        //populate combobox with employee names
        fill();
        //populate tables with database info
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system?allowPublicKeyRetrieval=true&useSSL=false","root","");
	    Statement s = c.createStatement();
            String s1="select * from starter";
            ResultSet rs1=s.executeQuery(s1);
            while(rs1.next()){
                
            Startermodel.insertRow(Startermodel.getRowCount(), new Object[]{rs1.getString("name"), rs1.getString("price")});
            }
            String s2="select * from main_course";
            ResultSet rs2=s.executeQuery(s2);
            while(rs2.next()){
                
            Mainmodel.insertRow(Mainmodel.getRowCount(), new Object[]{rs2.getString("name"), rs2.getString("price")});
            }
            String s3="select * from dessert";
            ResultSet rs3=s.executeQuery(s3);
            while(rs3.next()){
                
            Dessertmodel.insertRow(Dessertmodel.getRowCount(), new Object[]{rs3.getString("name"), rs3.getString("price")});
            }
            String s4="select * from drink";
            ResultSet rs4=s.executeQuery(s4);
            while(rs4.next()){
                
            Drinkmodel.insertRow(Drinkmodel.getRowCount(), new Object[]{rs4.getString("name"), rs4.getString("price")});
            }
            String s5="select * from emp_detail where emp_position='waiter'";
            ResultSet rs5=s.executeQuery(s5);
            String name[]=new String[100];
            int cnt=0,i=0;
            while(rs5.next()){
                name[i]=rs5.getString("first_name");
                name[i]+=" "+rs5.getString("last_name");
                i++;
                cnt++;
            }
            String cbbox[]=new String[cnt+1];
            cbbox[0]="---Select---";
            i=1;
            int j=0;
            while(cnt>0){
                cbbox[i]=name[j];
                i++;j++;cnt--;
            }
            cbxEmployees.setModel(new DefaultComboBoxModel(cbbox));
		
        }catch(Exception e){
            System.out.println(e);
        }
        populateStarter();
        populateMain();
        populateDessert();
        populateDrink();
        //Document Listeners for validation, buttons become enabled when certain conditions are met
        //Code manipulated from http://stackoverflow.com/questions/10848335/how-to-implement-documentlistener
      /*  txtTable.getDocument().addDocumentListener(new DocumentListener() {
            @Override
        public void changedUpdate(DocumentEvent e){
          lengthCheck();
        }
           @Override
        public void insertUpdate(DocumentEvent e) {
          lengthCheck();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
           lengthCheck();
        }
       });*/
        txtCost.getDocument().addDocumentListener(new DocumentListener() {
            @Override
        public void changedUpdate(DocumentEvent e){
           lengthCheck();
        }
           @Override
        public void insertUpdate(DocumentEvent e) {
          lengthCheck();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
          lengthCheck();
        }
       });
    }
    //method to check text field for validation
    public void lengthCheck(){
          if(txtCost.getText().equals("")||txtCost.getText().equals("0.0")){
              txtFinish.setEnabled(false);
        
          }
          else{
                btnBack.setEnabled(false);
               txtFinish.setEnabled(true);
               
          }
      }
    //method to fill combobox with employee names
    private void fill(){
     
    }
    
    
    
    //inserts order to tabel the same way as previous inserts
    private void insertOrder(Person_Order o)
    {
       
    }   
    //inserts order to tabel the same way as previous inserts
    private void insertFinalOrder(TotalPrice p)
    {
        
    }  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtStarterValue = new javax.swing.JTextField();
        txtMainValue = new javax.swing.JTextField();
        txtDessertValue = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        txtDrinkValue = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        cbxEmployees = new javax.swing.JComboBox();
        txtRowCost = new javax.swing.JTextField();
        txtFinish = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDrinks = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStarters = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMains = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDesserts = new javax.swing.JTable();
        txtStarterPrice = new javax.swing.JTextField();
        txtMainPrice = new javax.swing.JTextField();
        txtDessertPrice = new javax.swing.JTextField();
        txtDrinkPrice = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtTable = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStarterValue.setEditable(false);
        txtStarterValue.setText("-");
        getContentPane().add(txtStarterValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 369, 188, -1));

        txtMainValue.setEditable(false);
        txtMainValue.setText("-");
        txtMainValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMainValueActionPerformed(evt);
            }
        });
        getContentPane().add(txtMainValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 188, -1));

        txtDessertValue.setEditable(false);
        txtDessertValue.setText("-");
        getContentPane().add(txtDessertValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 431, 188, -1));

        txtCost.setEditable(false);
        txtCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostActionPerformed(evt);
            }
        });
        getContentPane().add(txtCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 463, 134, -1));

        btnAdd.setBackground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add Order");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, -1, 25));

        txtDrinkValue.setEditable(false);
        txtDrinkValue.setText("-");
        getContentPane().add(txtDrinkValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 463, 188, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("€");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 465, 15, -1));

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Starters", "Main Course", "Desserts", "Drinks", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.getTableHeader().setReorderingAllowed(false);
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblOrder);
        if (tblOrder.getColumnModel().getColumnCount() > 0) {
            tblOrder.getColumnModel().getColumn(0).setMinWidth(50);
            tblOrder.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblOrder.getColumnModel().getColumn(0).setMaxWidth(50);
            tblOrder.getColumnModel().getColumn(1).setMinWidth(120);
            tblOrder.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblOrder.getColumnModel().getColumn(1).setMaxWidth(120);
            tblOrder.getColumnModel().getColumn(2).setMinWidth(120);
            tblOrder.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblOrder.getColumnModel().getColumn(2).setMaxWidth(120);
            tblOrder.getColumnModel().getColumn(3).setMinWidth(120);
            tblOrder.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblOrder.getColumnModel().getColumn(3).setMaxWidth(120);
            tblOrder.getColumnModel().getColumn(4).setMinWidth(120);
            tblOrder.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblOrder.getColumnModel().getColumn(4).setMaxWidth(120);
            tblOrder.getColumnModel().getColumn(5).setMinWidth(70);
            tblOrder.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblOrder.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 17, 597, 402));

        cbxEmployees.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEmployeesActionPerformed(evt);
            }
        });
        getContentPane().add(cbxEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 369, 133, -1));
        getContentPane().add(txtRowCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 82, -1));

        txtFinish.setText("Finish Order");
        txtFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinishActionPerformed(evt);
            }
        });
        getContentPane().add(txtFinish, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 490, 110, 25));

        tblDrinks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Drinks", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDrinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDrinksMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblDrinks);
        if (tblDrinks.getColumnModel().getColumnCount() > 0) {
            tblDrinks.getColumnModel().getColumn(1).setMinWidth(70);
            tblDrinks.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblDrinks.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 189, 250, 154));

        tblStarters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Starters", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStarters.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStartersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStarters);
        if (tblStarters.getColumnModel().getColumnCount() > 0) {
            tblStarters.getColumnModel().getColumn(1).setMinWidth(70);
            tblStarters.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblStarters.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, 250, 154));

        tblMains.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Main Courses", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMains.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMainsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMains);
        if (tblMains.getColumnModel().getColumnCount() > 0) {
            tblMains.getColumnModel().getColumn(1).setMinWidth(70);
            tblMains.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblMains.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 17, 250, 154));

        tblDesserts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Desserts", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDesserts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDessertsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDesserts);
        if (tblDesserts.getColumnModel().getColumnCount() > 0) {
            tblDesserts.getColumnModel().getColumn(1).setMinWidth(70);
            tblDesserts.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblDesserts.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 189, 250, 154));

        txtStarterPrice.setEditable(false);
        txtStarterPrice.setText("0.0");
        getContentPane().add(txtStarterPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 369, 54, -1));

        txtMainPrice.setEditable(false);
        txtMainPrice.setText("0.0");
        getContentPane().add(txtMainPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 400, 54, -1));

        txtDessertPrice.setEditable(false);
        txtDessertPrice.setText("0.0");
        getContentPane().add(txtDessertPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 431, 54, -1));

        txtDrinkPrice.setEditable(false);
        txtDrinkPrice.setText("0.0");
        getContentPane().add(txtDrinkPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 463, 54, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Employee");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 370, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Table Number");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 401, -1, -1));

        btnDelete.setText("Order Delivered");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 110, 25));

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 90, 25));
        getContentPane().add(txtTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, 130, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Grey-Background-GREY.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -2, 1150, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system?allowPublicKeyRetrieval=true&useSSL=false","root","");
	    Statement s = c.createStatement();
            String quer="Select count(oid) as cnt from person_order";
            ResultSet rs=s.executeQuery(quer);
            int oid=0;
            if(rs.next()){
                oid=rs.getInt("cnt")+1;
            }
           
             String sel1=(String)cbxEmployees.getSelectedItem();
             int tno=Integer.parseInt(txtTable.getValue().toString());
            String starter="NULL",mainC="NULL",dessert="NULL",drink="NULL";
            Double total=Double.parseDouble(txtStarterPrice.getText())+Double.parseDouble(txtDessertPrice.getText())+Double.parseDouble(txtDrinkPrice.getText())+Double.parseDouble(txtMainPrice.getText());
            if(txtStarterValue.getText()!="-"){
                starter=txtStarterValue.getText();
            }
            if(txtMainValue.getText()!="-"){
                mainC=txtMainValue.getText();
            }
            if(txtDessertValue.getText()!="-"){
                dessert=txtDessertValue.getText();
            }
            if(txtDrinkValue.getText()!="-"){
                drink=txtDrinkValue.getText();
            }
            String query1 = "insert into person_order values('"+oid+"','"+starter+"','"+mainC+"','"+dessert+"','"+drink+"','"+total+"','"+tno+"','"+sel1+"')";
            int rs1 = s.executeUpdate(query1);
            if(rs1>0){
                txtCost.setText(String.valueOf(total));
                JOptionPane.showMessageDialog(null, "Order Placed Succesfully!!!");
                model.insertRow(model.getRowCount(), new Object[]{oid,txtStarterValue.getText(),txtMainValue.getText(),txtDessertValue.getText(),txtDrinkValue.getText(),total});
            }
        }catch(Exception e ){
            System.out.println(e);
        } 
        
      

    }//GEN-LAST:event_btnAddActionPerformed

    private void cbxEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEmployeesActionPerformed
            
    }//GEN-LAST:event_cbxEmployeesActionPerformed

    private void txtFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinishActionPerformed
        
        //Sends summary of the order to total price table
        try{
        Integer.parseInt(txtTable.getValue().toString());
        p.setOrderId(xx);
        p.setTableNum(txtTable.getValue().toString());
        p.setEmployee((String) cbxEmployees.getSelectedItem());
        p.setTotalPrice(txtCost.getText());
        insertFinalOrder(p);
        
        //opens hompage form
        Homepage h = new Homepage();
        h.setVisible(true);
                this.setVisible(false);
        }//error handling if the user inputs a non numeric character
        catch(Exception c){
            JOptionPane.showMessageDialog(null, "You can only enter numbers into the Table Number textfield");
            txtTable.setValue(null);
        }
    }//GEN-LAST:event_txtFinishActionPerformed

    private void tblDrinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDrinksMouseClicked
        //adds selected table row to textfields
        int row = tblDrinks.getSelectedRow();
        
        String selected = tblDrinks.getValueAt(row, 0).toString();
        DefaultTableModel model= (DefaultTableModel)tblDrinks.getModel();
        try{
            if(selected !=null ){
                txtDrinkValue.setText(String.valueOf(model.getValueAt(tblDrinks.getSelectedRow(), 0)));
                txtDrinkPrice.setText(String.valueOf(model.getValueAt(tblDrinks.getSelectedRow(), 1)));
            }
        } catch(NullPointerException a){
            JOptionPane.showMessageDialog(null, "Deleted");
        }

    }//GEN-LAST:event_tblDrinksMouseClicked

    private void tblStartersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStartersMouseClicked
         //adds selected table row to textfields
        int row = tblStarters.getSelectedRow();

        String selected = tblStarters.getValueAt(row, 0).toString();
        DefaultTableModel model= (DefaultTableModel)tblStarters.getModel();
        try{
            if(selected !=null ){
                txtStarterValue.setText(String.valueOf(model.getValueAt(tblStarters.getSelectedRow(), 0)));
                txtStarterPrice.setText(String.valueOf(model.getValueAt(tblStarters.getSelectedRow(), 1)));
            }
        } catch(NullPointerException a){
            JOptionPane.showMessageDialog(null, "Deleted");
        }
    }//GEN-LAST:event_tblStartersMouseClicked

    private void tblMainsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMainsMouseClicked
        //adds selected table row to textfields
        int row = tblMains.getSelectedRow();

        String selected = tblMains.getValueAt(row, 0).toString();
        DefaultTableModel model= (DefaultTableModel)tblMains.getModel();
        try{
            if(selected !=null ){
                txtMainValue.setText(String.valueOf(model.getValueAt(tblMains.getSelectedRow(), 0)));
                txtMainPrice.setText(String.valueOf(model.getValueAt(tblMains.getSelectedRow(), 1)));
            }
        } catch(NullPointerException a){
            JOptionPane.showMessageDialog(null, "Deleted");
        }
    }//GEN-LAST:event_tblMainsMouseClicked

    private void tblDessertsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDessertsMouseClicked
        //adds selected table row to textfields
        int row = tblDesserts.getSelectedRow();

        String selected = tblDesserts.getValueAt(row, 0).toString();
        DefaultTableModel model= (DefaultTableModel)tblDesserts.getModel();
        try{
            if(selected !=null ){
                txtDessertValue.setText(String.valueOf(model.getValueAt(tblDesserts.getSelectedRow(), 0)));
                txtDessertPrice.setText(String.valueOf(model.getValueAt(tblDesserts.getSelectedRow(), 1)));

                
            }
        } catch(NullPointerException a){
            JOptionPane.showMessageDialog(null, "Deleted");
        }
    }//GEN-LAST:event_tblDessertsMouseClicked

    private void txtMainValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMainValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMainValueActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //adds selected table row to textfields
        int row = tblOrder.getSelectedRow();
        DefaultTableModel model= (DefaultTableModel)tblOrder.getModel();
        String selected = tblOrder.getValueAt(row, 0).toString();//assigns selected variable to the selected order id for deletion purposes
        String Price = tblOrder.getValueAt(row, 5).toString();
        String sel1=(String)cbxEmployees.getSelectedItem();
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_ordering_system?allowPublicKeyRetrieval=true&useSSL=false","root","");
	    Statement s = c.createStatement();
            int cnt = 0;
            String query2 = "select count(or_id) as cnt from placed_order";
            ResultSet rs2 = s.executeQuery(query2);
            if(rs2.next())
            {
                cnt = rs2.getInt("cnt")+1;
            }
            String date= "";
            String query1 = "select current_date as date";
            ResultSet rs = s.executeQuery(query1);
            if(rs.next())
            {
                date= rs.getString("date");
            }
            String q="insert into placed_order values('"+cnt+"','"+Price+"','"+date+"','"+txtTable.getValue()+"','"+sel1+"')";
            int ins=s.executeUpdate(q);
            if(ins>0){
                String q1="delete from person_order where oid='"+selected+"'";
                int ins1=s.executeUpdate(q1);
                if(ins1>0){
                    model.removeRow(row);
                }
            }
            
            
        }catch(Exception e ){
            System.out.println(e);
        }
        
        if (row >= 0) {
     
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostActionPerformed

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
       //enabled delete button
       btnDelete.setEnabled(true);
        
        
    }//GEN-LAST:event_tblOrderMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //goes back to the prevous page
        Homepage h = new Homepage();
        h.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }
    //populates starter table
    public void populateStarter(){
    
    }
    //populates main table
    public void populateMain(){
      
        
    }
    //populate dessert table
    public void populateDessert(){
    
    }
    
    public void populateDrink(){
       
        
        
    }
    
    public String extractDigits(String src) 
    {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < src.length(); i++) {
        char c = src.charAt(i);
        if (Character.isDigit(c)) {
            builder.append(c);
        }
    }
    return builder.toString();
    }
    
    public void openStream(){
       
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox cbxEmployees;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblDesserts;
    private javax.swing.JTable tblDrinks;
    private javax.swing.JTable tblMains;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblStarters;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtDessertPrice;
    private javax.swing.JTextField txtDessertValue;
    private javax.swing.JTextField txtDrinkPrice;
    private javax.swing.JTextField txtDrinkValue;
    private javax.swing.JButton txtFinish;
    private javax.swing.JTextField txtMainPrice;
    private javax.swing.JTextField txtMainValue;
    private javax.swing.JTextField txtRowCost;
    private javax.swing.JTextField txtStarterPrice;
    private javax.swing.JTextField txtStarterValue;
    private javax.swing.JSpinner txtTable;
    // End of variables declaration//GEN-END:variables
}
