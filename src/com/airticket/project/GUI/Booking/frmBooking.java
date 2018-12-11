package com.airticket.project.GUI.Booking;

import com.airticket.project.Controller.BookingDAO.Booking;
import com.airticket.project.Controller.BookingDAO.BookingDAO;
import com.airticket.project.GUI.Menu.frmMainPage;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.EmptyBorder;

public class frmBooking extends JFrame implements ActionListener {
    private JTextField tf_temp, tf_temp2, tf_temp3, tf_temp4, tf_time;
    private JComboBox jBoxAirport_in, jBoxAirport_out;
    private JDateChooser date;
    String[] airport_in;
    String[] airport_out;
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
        date = new JDateChooser();
        date.setDateFormatString("yyyy-MM-dd");
        panel.add(date);
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

            String airPortIn = bookingDAO.convertNameToId(jBoxAirport_out.getSelectedItem().toString());
            String airPortOut = bookingDAO.convertNameToId(jBoxAirport_in.getSelectedItem().toString());
//            java.sql.Date sqlDate = java.sql.Date.valueOf(date.getDate());
            Date timeFlight = date.getDate();
//            Date timeFlight = new Date();
//            System.out.println(timeFlight);
            try {
                frmBooking2 frmBooking2 = new frmBooking2(airPortIn, airPortOut, timeFlight);
                setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if(command == "Cancel"){
            setVisible(false);
            new frmMainPage();
        }
    }

    public static void main(String[] args) throws SQLException {
        new frmBooking();
    }



}
