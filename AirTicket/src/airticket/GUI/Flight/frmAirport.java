/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Flight;

import airticket.Connector.Connector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author NhatTan
 */
public class frmAirport extends JFrame{
    
    private int size = 15;
    private static int row,col,numcols;
    private JTextField txt_airport_id,txt_airport_name,txt_nation,txt_airport_type,txt_address,txt_phone;
    private JButton btn_add, btn_update, btn_delete;

    public frmAirport(){
        setTitle("Airport");
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
        JLabel lb_id = new JLabel("Airport ID");
        lb_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        pn.add(lb_id,cons);
        cons.gridx = 1;
        cons.gridy = 0;
        txt_airport_id = new JTextField(size);
        txt_airport_id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(txt_airport_id,cons);

        //AirportName
        cons.gridx = 0;
        cons.gridy = 1;
        JLabel lb_name = new JLabel("Airport Name");
        lb_name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(lb_name,cons);
        cons.gridx = 1;
        cons.gridy = 1;
        txt_airport_name = new JTextField(size);
        txt_airport_name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(txt_airport_name,cons);

        //NationID
        cons.gridx = 0;
        cons.gridy = 2;
        JLabel lb_nation = new JLabel("Nation");
        lb_nation.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        pn.add(lb_nation,cons);
        cons.gridx = 1;
        cons.gridy = 2;
        txt_nation = new JTextField(size);
        txt_nation.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        pn.add(txt_nation,cons);

        //Airport Type
        cons.gridx = 3;
        cons.gridy = 0;
        JLabel lb_type = new JLabel("Airport type");
        lb_type.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        pn.add(lb_type,cons);
        cons.gridx = 4;
        cons.gridy = 0;
        txt_airport_type = new JTextField(size);
        txt_airport_type.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        pn.add(txt_airport_type,cons);

        //Address
        cons.gridx = 3;
        cons.gridy = 1;
        JLabel lb_address = new JLabel("Address");
        lb_address.setFont(new Font("Times New Roman", Font.PLAIN, 20));          
        pn.add(lb_address,cons);
        cons.gridx = 4;
        cons.gridy = 1;
        txt_address = new JTextField(size);
        txt_address.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        pn.add(txt_address,cons);

        //Phone
        cons.gridx = 3;
        cons.gridy = 2;
        JLabel lb_phone = new JLabel("Phone");
        lb_phone.setFont(new Font("Times New Roman", Font.PLAIN, 20));          
        pn.add(lb_phone,cons);
        cons.gridx = 4;
        cons.gridy = 2;
        txt_phone = new JTextField(size);
        txt_phone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pn.add(txt_phone,cons);

        //Button
        cons.gridx = 1;
        cons.gridy = 4;
        btn_add    = new JButton("Add",new ImageIcon("image/them.png"));
        pn.add(btn_add,cons);
        cons.gridx = 3;
        cons.gridy = 4;
        btn_update = new JButton("Update",new ImageIcon("image/capnhat.png"));
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

        String cols[] = {"STT","Airport_ID","Airport_Name","Nation_ID","Airport_Type","Address","Phone"};
        Object data[][] = {
//                {1,"BMV","Buon Ma Thuot","VietNam","NATIONAL","DakLak,Vietnam","0165464987"},
//                {2,"CXR","Cam Ranh","VietNam","NATIONAL","KhanhHoa,Vietnam","01345465"}
        };

        DefaultTableModel df_table = new DefaultTableModel(data, cols);
        JTable table = new JTable(df_table);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        
        try {
            Connector db = new Connector();
            Connection conn = db.getConnection(); 
            String SQl = "SELECT * FROM airport";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(SQl);
            int count = 1;
            while(rs.next()){
                Object rows[] = new Object[7];
                rows[0] = Integer.toString(count);
                rows[1] = rs.getString("airport_id");
                rows[2] = rs.getString("airportname");
                rows[3] = rs.getString("nation_id");
                rows[4] = rs.getString("Type");
                rows[5] = rs.getString("address");
                rows[6] = rs.getString("phone");
                count++;
                df_table.addRow(rows);
            }              

        } catch (Exception e) {
            System.out.println("Loi:"+e.toString());
        }        
        
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.rowAtPoint(e.getPoint());
                col = table.columnAtPoint(e.getPoint());
                numcols = table.getColumnCount();

                for(int i=1;i<numcols;i++){
                    String temp = (String)table.getValueAt(row,i);
                    switch (i){
                        case 1: txt_airport_id.setText(temp);break;
                        case 2: txt_airport_name.setText(temp);break;
                        case 3: txt_nation.setText(temp);break;
                        case 4: txt_airport_type.setText(temp);break;
                        case 5: txt_address.setText(temp);break;
                        case 6: txt_phone.setText(temp);break;
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
                //
            }
        });

        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object temp[] = {"",txt_airport_id.getText(),txt_airport_name.getText(),txt_nation.getText(),txt_airport_type.getText(),txt_address.getText(),txt_phone.getText()};
                for(int i=1;i<numcols;i++){
                    table.setValueAt(temp[i],row,i);  
                } 
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
    
    public static void main(String[] args) {
        new frmAirport();
    }
   
}
