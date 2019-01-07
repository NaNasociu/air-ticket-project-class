/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Flight;

import airticket.Connector.Connector;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NhatTan
 */
public class frmFlight extends JFrame{
    
    private int size = 15;
    private static int row,col,numcols;
    private JTextField txt_flight_id,txt_route,txt_airplane,txt_amount,txt_fare,txt_start_time;
    private JButton btn_add, btn_update, btn_delete;
    private DefaultTableModel df_table;
    private JTable table;

    public frmFlight(){
        setTitle("Flight");
        setSize(1600,900);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(inputPanel());
        add(tablePanel());

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private JPanel inputPanel(){
        JPanel pn = new JPanel(new GridBagLayout());
        pn.setPreferredSize(new Dimension(1300,300));
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(15,15,15,15);

        //AirportID
        cons.gridx = 0;
        cons.gridy = 0;
        JLabel lb_id = new JLabel("Flight ID");
        lb_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        pn.add(lb_id,cons);
        cons.gridx = 1;
        cons.gridy = 0;
        txt_flight_id = new JTextField(size);
        txt_flight_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(txt_flight_id,cons);

        //AirportName
        cons.gridx = 0;
        cons.gridy = 1;
        JLabel lb_name = new JLabel("Route");
        lb_name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(lb_name,cons);
        cons.gridx = 1;
        cons.gridy = 1;
        txt_route = new JTextField(size);
        txt_route.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(txt_route,cons);

        //NationID
        cons.gridx = 0;
        cons.gridy = 2;
        JLabel lb_nation = new JLabel("AirplaneID");
        lb_nation.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        pn.add(lb_nation,cons);
        cons.gridx = 1;
        cons.gridy = 2;
        txt_airplane = new JTextField(size);
        txt_airplane.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        pn.add(txt_airplane,cons);

        //Airport Type
        cons.gridx = 3;
        cons.gridy = 0;
        JLabel lb_type = new JLabel("Ticket Amount");
        lb_type.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        pn.add(lb_type,cons);
        cons.gridx = 4;
        cons.gridy = 0;
        txt_amount = new JTextField(size);
        txt_amount.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        pn.add(txt_amount,cons);

        //Address
        cons.gridx = 3;
        cons.gridy = 1;
        JLabel lb_address = new JLabel("Fare");
        lb_address.setFont(new Font("Times New Roman", Font.PLAIN, 20));          
        pn.add(lb_address,cons);
        cons.gridx = 4;
        cons.gridy = 1;
        txt_fare = new JTextField(size);
        txt_fare.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        pn.add(txt_fare,cons);

        //Phone
        cons.gridx = 3;
        cons.gridy = 2;
        JLabel lb_phone = new JLabel("Start Time");
        lb_phone.setFont(new Font("Times New Roman", Font.PLAIN, 20));          
        pn.add(lb_phone,cons);
        cons.gridx = 4;
        cons.gridy = 2;
        txt_start_time = new JTextField(size);
        txt_start_time.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(txt_start_time,cons);

        //Button
        cons.gridx = 1;
        cons.gridy = 4;
        btn_add    = new JButton("Add",new ImageIcon("image/them.png"));
        pn.add(btn_add,cons);
        cons.gridx = 3;
        cons.gridy = 4;
        btn_update = new JButton("Refesh",new ImageIcon("image/capnhat.png"));
        pn.add(btn_update,cons);
        cons.gridx = 4;
        cons.gridy = 4;
        btn_delete = new JButton("Delete",new ImageIcon("image/xoa_small.png"));
//        btn_delete.setEnabled(false);
        pn.add(btn_delete,cons);

        return pn;
    }

    private JPanel tablePanel(){
        JPanel pn = new JPanel(new GridLayout(1,1,5,5));
        pn.setPreferredSize(new Dimension(1500,500));

        String cols[] = {"FlightID","Route","AirPlaneID","TicketAmount","TicketRemaining","Fare","StartTime"};
        Object data[][] = {};

        df_table = new DefaultTableModel(data, cols);
        table = new JTable(df_table);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 20));       
        Refresh();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.rowAtPoint(e.getPoint());
                col = table.columnAtPoint(e.getPoint());
                numcols = table.getColumnCount();

                for(int i=0;i<numcols;i++){
                    String temp = (String)table.getValueAt(row,i);
                    switch (i){
                        case 0: txt_flight_id.setText(temp);break;
                        case 1: txt_route.setText(temp);break;
                        case 2: txt_airplane.setText(temp);break;
                        case 3: txt_amount.setText(temp);break;
                        case 5: txt_fare.setText(temp);break;
                        case 6: txt_start_time.setText(temp);break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connector db = new Connector();
                    Connection conn = db.getConnection();                
                    String SQL = "INSERT INTO flight VALUES (?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stat = conn.prepareStatement(SQL);
                    stat.setString(1,txt_flight_id.getText());
                    stat.setString(2,txt_route.getText());
                    stat.setString(3,txt_airplane.getText());
                    stat.setString(4,txt_amount.getText());
                    stat.setString(5,txt_amount.getText());
                    stat.setString(6,txt_fare.getText());
                    stat.setString(7,txt_start_time.getText());
                    stat.setString(8,null);
                    stat.setString(9,"2");
                    stat.setString(10,"1");
    
                    int result = stat.executeUpdate();
                    if(result == 1){
                        Refresh();
                    }
                } catch (Exception e3) {
                    e3.getMessage();
                }
              
            }
        });

        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Refresh();
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        pn.add(scrollPane);

        return pn;
    }  
    
    public void Refresh(){
        
        int rowCount = df_table.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            df_table.removeRow(i);
        }        
        try {
            Connector db = new Connector();
            Connection conn = db.getConnection(); 
            String SQl = "SELECT * FROM flight";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(SQl);
            int count = 1;
            while(rs.next()){
                
                String SQL2 = "SELECT * FROM FLIGHT_ROUTE where fr_id = ?";
                PreparedStatement stat2 = conn.prepareStatement(SQL2);
                stat2.setString(1,rs.getString("fr_id"));
                ResultSet rs2 = stat2.executeQuery();
                String airport_in = null, airport_out = null;
                while(rs2.next()){
                    airport_in = rs2.getString("airport_in");
                    airport_out = rs2.getString("airport_out");
                }
                
                String SQL3 = "SELECT * FROM airplane where airplane_id = ?";
                PreparedStatement stat3 = conn.prepareStatement(SQL3);
                stat3.setString(1,rs.getString("airplane_id"));
                ResultSet rs3 = stat3.executeQuery();
                String plane_name = null;
                while(rs3.next()){
                    plane_name = rs3.getString("airplane_name");
                }                
                
                Object rows[] = new Object[7];
                rows[0] = rs.getString("f_id");
                rows[1] = rs.getString("fr_id") + " =>" +airport_in + " to " + airport_out;
                rows[2] = rs.getString("airplane_id") + " =>" +plane_name;
                rows[3] = rs.getString("ticket_amount");
                rows[4] = rs.getString("ticket_remaining");
                rows[5] = rs.getString("fare");
                rows[6] = rs.getString("start_time");
                df_table.addRow(rows);
            }              

        } catch (Exception e) {
            System.out.println("Loi:"+e.toString());
        }         
    }
    
    public static void main(String[] args) {
        new frmFlight();
    }
    
}
