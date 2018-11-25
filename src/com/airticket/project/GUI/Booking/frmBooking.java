package com.airticket.project.GUI.Booking;

import com.airticket.project.Controller.BookingDAO.BookingDAO;
import com.airticket.project.GUI.Menu.frmMainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.border.EmptyBorder;

public class frmBooking extends JFrame implements ActionListener {
    private JTextField tf_temp, tf_temp2, tf_temp3, tf_temp4;
    String[] airport_in;
    String[] airport_out;
    String[] choices_2 = {"VND"};
    int size = 15;

    public frmBooking() throws SQLException {
        BookingDAO bookingDAO = new BookingDAO();
        airport_in = bookingDAO.getAirport_in();
        airport_out = bookingDAO.getAirport_out();
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
        panel.add(new JComboBox(airport_in));
        panel.add(new JLabel("Destination:"));
        panel.add(new JComboBox(airport_out));
        panel.add(new JLabel("From:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("To:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Number of passengers:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Currency:"));
        panel.add(new JComboBox(choices_2));
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
