package com.airticket.project.GUI.Booking;

import com.airticket.project.Controller.BookingDAO.Booking;
import com.airticket.project.Controller.BookingDAO.BookingDAO;
import com.airticket.project.GUI.Menu.frmMainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class frmBooking extends JFrame implements ActionListener {
    private JTextField tf_temp, tf_temp2, tf_temp3, tf_temp4;
    private JComboBox jBoxAirport_in, jBoxAirport_out;
    String[] airport_in;
    String[] airport_out;
    String[] choices_2 = {"VND"};
    int size = 15;
    private BookingDAO bookingDAO = new BookingDAO();
    public frmBooking() throws SQLException {
        airport_in = bookingDAO.getAirportName();
        airport_out = bookingDAO.getAirportName();
        setTitle("Booking - step 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(false);

        add(createMainPanel());
        add(createButtonPanel());

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.add(createInputPanel(),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,5,5));
        panel.add(createJButton("Search Flights"));
        panel.add(createJButton("Cancel"));
        return panel;
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(6,2,5,5));
        panel.add(new JLabel("Origin:"));
        panel.add(jBoxAirport_in = new JComboBox(airport_in));
        panel.add(new JLabel("Destination:"));
        panel.add(jBoxAirport_out = new JComboBox(airport_out));
        panel.add(new JLabel("From:"));
        panel.add(new JTextField(size));
        return panel;
    }

    private JButton createJButton(String name){
        JButton btn = new JButton(name);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent evt){
        String command = evt.getActionCommand();
        if(command == "Search Flights"){

            System.out.println(bookingDAO.convertNameToId(jBoxAirport_out.getSelectedItem().toString()));
            System.out.println(bookingDAO.convertNameToId(jBoxAirport_in.getSelectedItem().toString()));

            setVisible(false);
            new frmBooking2();
        }

        if(command == "Cancel"){
            setVisible(false);
            new frmMainPage();
        }
    }

    private void clear(){
        tf_temp.setText("");
        tf_temp.requestFocus();
    }

    public static void main(String[] args) throws SQLException {
        new frmBooking();
    }



}
