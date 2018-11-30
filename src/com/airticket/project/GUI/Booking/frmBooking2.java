package com.airticket.project.GUI.Booking;

import com.airticket.project.Controller.BookingDAO.BookingDAO;
import com.airticket.project.Controller.BookingDAO.FlightStepTwo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class frmBooking2 extends JFrame implements ActionListener {
    String[] columnNames = {"Flight Date", "Departs", "Arrives", "Flight Detail","Promo", "Eco","Skyboss"};
    Object [][] data = {};
    ArrayList<FlightStepTwo> flightList = new ArrayList<FlightStepTwo>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private String airport_in;
    private String airport_out;
    private Date timeFlight;
    int size = 15;

    public String getAirport_in() {
        return airport_in;
    }

    public void setAirport_in(String airport_in) {
        this.airport_in = airport_in;
    }

    public String getAirport_out() {
        return airport_out;
    }

    public void setAirport_out(String airport_out) {
        this.airport_out = airport_out;
    }

    public Date getTimeFlight() {
        return timeFlight;
    }

    public void setTimeFlight(Date timeFlight) {
        this.timeFlight = timeFlight;
    }

    public frmBooking2() throws SQLException {
        setTitle("Booking - step 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        add(createMainPanel());
        add(createButtonPanel());

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public frmBooking2(String airportIn, String airportOut, Date time_Flight) throws SQLException {
        this.setAirport_in(airportIn);
        this.setAirport_out(airportOut);
        this.setTimeFlight(time_Flight);

        setTitle("Booking - step 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        add(createMainPanel());
        add(createButtonPanel());

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel() throws SQLException {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.add(new JLabel("Select Travel Options"),BorderLayout.NORTH);
        panel.add(createInputPanel(),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,5,5));
        panel.add(createJButton("Continue"));
        panel.add(createJButton("Back"));
        return panel;
    }

    private JPanel createInputPanel() throws SQLException {
        JPanel panel = new JPanel();
        JTable table = new JTable();
        BookingDAO bookingDAO = new BookingDAO();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        flightList = bookingDAO.getFlightList(airport_in, airport_out, timeFlight);
        System.out.println("1");
        for (FlightStepTwo temp: flightList) {
            System.out.println("2");
            String rows[] = new String[6];
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            rows[0] = formatter.format(temp.getFlightDate());
            rows[1] = temp.getDeparts();
            rows[2] = temp.getArrives();
            rows[3] = temp.getEconomy();
            rows[4] = temp.getPremium();
            rows[5] = temp.getBusiness();
            System.out.println(rows);
            tableModel.addRow(rows);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }

    private JButton createJButton(String name){
        JButton btn = new JButton(name);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent evt){
        String command = evt.getActionCommand();
        if(command == "Continue"){
            setVisible(false);
            new frmBooking3();
        }

        if(command == "Back"){
            setVisible(false);
            try {
                new frmBooking();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        new frmBooking2();
    }


}
