/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Booking;

import airticket.Controller.Booking.BookingDAO;
import airticket.GUI.Menu.frmMenu;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author NhatTan
 */
public class frmBooking extends JFrame{
    
    private JComboBox jBoxAirport_in, jBoxAirport_out;
    private JDateChooser date;
    String[] airport_in;
    String[] airport_out;    
    
    private BookingDAO bookingDAO;
    
    public frmBooking() throws SQLException{
        this.bookingDAO = new BookingDAO();
        airport_in = bookingDAO.getAirportName();
        airport_out = bookingDAO.getAirportName();        
        setTitle("Booking - step 1");
        setSize(820,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
//        setResizable(false);

        add(createMainPanel());
        add(createButtonPanel());

        setVisible(true);
        setLocationRelativeTo(null);        
    }
    
    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.add(createInputPanel(),BorderLayout.CENTER);
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
    
    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,5,5));
        panel.add(createJButton("Search Flights"));
        panel.add(createJButton("Cancel"));
        return panel;
    }

    private JButton createJButton(String name){
        JButton btn = new JButton(name);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command == "Search Flights"){
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String airPortOut = bookingDAO.convertNameToId(jBoxAirport_out.getSelectedItem().toString());
                    String airPortIn = bookingDAO.convertNameToId(jBoxAirport_in.getSelectedItem().toString());
                    String timeFlight;
                    if (date.getDate() == null){
                        timeFlight = null;                    
                    }else{
                        timeFlight = df.format(date.getDate());
                    }

                    try {
                        frmBooking2 frmBooking2 = new frmBooking2(airPortIn, airPortOut, timeFlight);
                    } catch (SQLException ex) {
                        Logger.getLogger(frmBooking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    setVisible(false);
                }

                if(command == "Cancel"){
                    setVisible(false);
                    new frmMenu();
                }           
            }
        });
        return btn;
    }   
    
    public static void main(String[] args) throws SQLException {
        new frmBooking();
    }

}
