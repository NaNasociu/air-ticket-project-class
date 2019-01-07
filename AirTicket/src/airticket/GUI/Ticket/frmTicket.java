/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Ticket;

import airticket.Connector.Connector;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author NhatTan
 */
public class frmTicket extends JFrame{
    
    int size = 15;
    double total = 0;
    double average = 0;
    int i;
    private JButton btn_search, btn_xuat, btn_print;
    private String txt_from, txt_to;
    private JTextField txt_total,txt_average;
    DefaultTableModel  df_table;
    Object[][] data = {};    
    
    public frmTicket(){
        setTitle("Revenue Report");
        setSize(1500,650);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lbl_from = new JLabel("From");
        JLabel lbl_to = new JLabel("To");
        JLabel lbl_total = new JLabel("Total");
        JLabel lbl_average = new JLabel("Average");
        lbl_from.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_to.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_total.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_average.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(15,15,15,15);
        cons.gridx = 0;
        cons.gridy = 0;
        add(lbl_from,cons);
        cons.gridx = 1;
        cons.gridy = 0;
        JDateChooser from = new JDateChooser();
        from.setDateFormatString("dd/MM/yyyy");
        from.setDate(new Date());
        from.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        from.setPreferredSize(new Dimension(200, 50));
        add(from,cons);        
               
        cons.gridx = 0;
        cons.gridy = 1;
        add(lbl_to,cons);
        cons.gridx = 1;
        cons.gridy = 1;
        JDateChooser to = new JDateChooser();
        to.setDateFormatString("dd/MM/yyyy");
        to.setDate(new Date());
        to.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        to.setPreferredSize(new Dimension(200, 50));
        add(to,cons);
        
        cons.gridx = 0;
        cons.gridy = 2;
        add(lbl_total,cons);
        cons.gridx = 1;
        cons.gridy = 2;
        txt_total = new JTextField(size);
        txt_total.setEditable(false);
        txt_total.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(txt_total,cons);      
        
        cons.gridx = 0;
        cons.gridy = 3;
        add(lbl_average,cons);
        cons.gridx = 1;
        cons.gridy = 3;
        txt_average = new JTextField(size);
        txt_average.setEditable(false);
        txt_average.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(txt_average,cons);        
        
        String[] columnNames = {"TicketID", "CardNumber","BookingDate", "Flight", "SeatID", "TicketFare"};

        df_table = new DefaultTableModel(data, columnNames);
        JTable table = new JTable();
        table.setModel(df_table);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        table.setFillsViewportHeight(true);          
        JScrollPane sp=new JScrollPane(table);
        cons.gridx = 2;
        cons.gridy = 1;        
        sp.setPreferredSize(new Dimension(900, 150));
        add(sp,cons);        

        cons.gridx = 2;
        cons.gridy = 4;
        btn_xuat = new JButton("Report", new ImageIcon("image/xeploai.png"));
        btn_xuat.setPreferredSize(new Dimension(150, 50));
        btn_xuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                txt_from = df.format(from.getDate());
                txt_to = df.format(to.getDate());
                try {
         
                    int rowCount = df_table.getRowCount();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        df_table.removeRow(i);
                    }                      
                    
                    Connector db = new Connector();
                    Connection conn = db.getConnection();
                    PreparedStatement stat = conn.prepareStatement("select * from ticket where booking_date between (?) and (?)");
                    stat.setString(1, txt_from); 
                    stat.setString(2, txt_to); 
                    boolean hadResults = stat.execute();   
                    if(hadResults){
                        ResultSet rs = stat.getResultSet();
                        while (rs.next()) {
                            i = 1;
                            Vector v = new Vector();
                            v.add(rs.getString("ticket_id"));
                            v.add(rs.getString("id_card_number"));
                            v.add(rs.getString("booking_date"));
                            v.add(rs.getString("f_id"));
                            v.add(rs.getString("seat_id"));
                            v.add(rs.getString("ticket_fare"));
                            total += Double.parseDouble(rs.getString("ticket_fare"));
                            df_table.addRow(v);
                            i++;
                        }
                        hadResults = stat.getMoreResults();              
                    }
                    average = total/i;
                    txt_total.setText(String.valueOf(total));
                    txt_average.setText(String.valueOf(average));
                } catch (Exception ex) {
                    ex.toString();
                }
                
            }
        });
        add(btn_xuat,cons);   
        
        
        cons.gridx = 1;
        cons.gridy = 4;
        btn_print = new JButton("Save", new ImageIcon("image/icon_Save.png"));
        btn_print.setPreferredSize(new Dimension(150, 50));     
        btn_print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connector db = new Connector();  
                    Connection conn = db.getConnection();            
                    String link = "C:\\Users\\NhatTan\\Documents\\NetBeansProjects\\AirTicket\\src\\airticket\\GUI\\Ticket\\report1.jrxml";
                    JasperReport j_report = JasperCompileManager.compileReport(link);
                    JasperPrint j_print = JasperFillManager.fillReport(j_report, null, conn);
                    Map<String, Object> params = new HashMap<String, Object>(); 
                    JasperViewer.viewReport(j_print);
                }catch(Exception e2){
                    System.out.println("Loi:" + e2.toString());
                } 
            }
        });
        add(btn_print,cons); 
        
        setVisible(true);
        setLocationRelativeTo(null);     
    
    }
    
    public static void main(String[] args) {
        new frmTicket();
    }
}
