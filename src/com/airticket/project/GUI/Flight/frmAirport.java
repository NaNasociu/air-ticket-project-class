package com.airticket.project.GUI.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class frmAirport extends JFrame {

    private int size = 20;
    private static int row,col,numcols;
    private JTextField txt_airport_id,txt_airport_name,txt_nation,txt_airport_type,txt_address,txt_phone;
    private JButton btn_add, btn_update, btn_delete;

    public frmAirport(){
        setTitle("Airport");
        setSize(1600,900);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
//        cons.weightx = 1;
//        cons.weighty = 1;

        //AirportID
        cons.gridx = 0;
        cons.gridy = 0;
        pn.add(new JLabel("Airport ID"),cons);
        cons.gridx = 1;
        cons.gridy = 0;
        txt_airport_id = new JTextField(size);
        pn.add(txt_airport_id,cons);

        //AirportName
        cons.gridx = 0;
        cons.gridy = 1;
        pn.add(new JLabel("Airport Name"),cons);
        cons.gridx = 1;
        cons.gridy = 1;
        txt_airport_name = new JTextField(size);
        pn.add(txt_airport_name,cons);

        //NationID
        cons.gridx = 0;
        cons.gridy = 2;
        pn.add(new JLabel("Nation"),cons);
        cons.gridx = 1;
        cons.gridy = 2;
        txt_nation = new JTextField(size);
        pn.add(txt_nation,cons);

        //Airport Type
        cons.gridx = 3;
        cons.gridy = 0;
        pn.add(new JLabel("Airport type"),cons);
        cons.gridx = 4;
        cons.gridy = 0;
        txt_airport_type = new JTextField(size);
        pn.add(txt_airport_type,cons);

        //Address
        cons.gridx = 3;
        cons.gridy = 1;
        pn.add(new JLabel("Address"),cons);
        cons.gridx = 4;
        cons.gridy = 1;
        txt_address = new JTextField(size);
        pn.add(txt_address,cons);

        //Phone
        cons.gridx = 3;
        cons.gridy = 2;
        pn.add(new JLabel("Phone"),cons);
        cons.gridx = 4;
        cons.gridy = 2;
        txt_phone = new JTextField(size);
        pn.add(txt_phone,cons);

        //Button
        cons.gridx = 1;
        cons.gridy = 4;
        btn_add    = new JButton("Add");
        pn.add(btn_add,cons);
        cons.gridx = 3;
        cons.gridy = 4;
        btn_update = new JButton("Update");
        pn.add(btn_update,cons);
        cons.gridx = 4;
        cons.gridy = 4;
        btn_delete = new JButton("Delete");
        pn.add(btn_delete,cons);

        return pn;
    }

    private JPanel tablePanel(){
        JPanel pn = new JPanel(new GridLayout(1,1,5,5));
        pn.setPreferredSize(new Dimension(1500,500));

        String cols[] = {"STT","Airport_ID","Airport_Name","Nation_ID","Airport_Type","Address","Phone"};
        Object data[][] = {
                {1,"BMV","Buon Ma Thuot","VietNam","NATIONAL","DakLak,Vietnam","0165464987"},
                {2,"CXR","Cam Ranh","VietNam","NATIONAL","KhanhHoa,Vietnam","01345465"}
        };

        DefaultTableModel df_table = new DefaultTableModel(data, cols);
        final JTable table = new JTable(df_table);
        table.setFillsViewportHeight(true);
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
                //
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
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
