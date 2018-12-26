/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Booking;

import airticket.Controller.Booking.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NhatTan
 */
public class frmBooking2 extends JFrame{
    String[] columnNames = {"Id", "Flight Date", "Departs", "Arrives", "Flight Detail","Promo", "Eco","Skyboss"};
    ArrayList<FlightStepTwo> flightList = new ArrayList<FlightStepTwo>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    
    private String airport_in;
    private String airport_out;
    private String timeFlight;
    private JTable table = new JTable();
    
    private frmBooking3 step3 = new frmBooking3();
    
    public void setAirport_in(String airport_in) {
        this.airport_in = airport_in;
    }
    
    public void setAirport_out(String airport_out) {
        this.airport_out = airport_out;
    }
    
    public void setTimeFlight(String timeFlight) {
        this.timeFlight = timeFlight;
    }
    
    public frmBooking2(String airportIn, String airportOut, String time_Flight) throws SQLException {
        this.setAirport_in(airportIn);
        this.setAirport_out(airportOut);
        this.setTimeFlight(time_Flight);

        setTitle("Booking - step 2");
        setSize(820,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
//        setResizable(false);

        add(createMainPanel());
        add(createButtonPanel());

//        pack();
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
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command == "Continue"){
                    setVisible(false);
                    step3.setVisible(true);
                }

                if(command == "Back"){
                    setVisible(false);
                    try {
                        new frmBooking();
                    } catch (SQLException ex) {
                        Logger.getLogger(frmBooking2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                              
            }
        });
        return btn;
    }

}
