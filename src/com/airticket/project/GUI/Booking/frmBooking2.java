package com.airticket.project.GUI.Booking;

import com.airticket.project.Controller.BookingDAO.BookingDAO;
import com.airticket.project.Controller.BookingDAO.FlightStepTwo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class frmBooking2 extends JFrame implements ActionListener {
    String[] columnNames = {"Id", "Flight Date", "Departs", "Arrives", "Flight Detail","Promo", "Eco","Skyboss"};
    Object [][] data = {};
    ArrayList<FlightStepTwo> flightList = new ArrayList<FlightStepTwo>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private String airport_in;
    private String airport_out;
    private String selectedAirport_Id;
    private Date timeFlight;
    private JTable table = new JTable();
    int size = 15;
    private frmBooking3 step3 = new frmBooking3();

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

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                int numcol = tableModel.getColumnCount();
                String temp = (String)tableModel.getValueAt(row, 0);
                step3.setSelectedAirport_Id(temp);
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

    }

    private JPanel createMainPanel() throws SQLException {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.add(new JLabel("Select Travel Options"),BorderLayout.NORTH);
        panel.add(createInputPanel(),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,6,5));
        panel.add(createJButton("Continue"));
        panel.add(createJButton("Back"));
        return panel;
    }

    private JPanel createInputPanel() throws SQLException {
        JPanel panel = new JPanel();
        BookingDAO bookingDAO = new BookingDAO();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        flightList = bookingDAO.getFlightList(airport_in, airport_out, timeFlight);
        for (FlightStepTwo temp: flightList) {
            String rows[] = new String[6];
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rows[0] = temp.getId();
            rows[1] = formatter.format(temp.getFlightDate());
            rows[2] = temp.getDeparts();
            rows[3] = temp.getArrives();
            rows[4] = temp.getEconomy();
            rows[5] = temp.getPremium();
//            rows[6] = temp.getBusiness();
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
        System.out.println(table.getSelectedRow());
        if(command == "Continue"){
            setVisible(false);
            step3.setVisible(true);
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
