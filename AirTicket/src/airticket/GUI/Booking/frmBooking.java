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
    private JButton btn_search, btn_cancel;
    String[] airport_in;
    String[] airport_out;    
    
    private BookingDAO bookingDAO;
    
    public frmBooking() throws SQLException{
        this.bookingDAO = new BookingDAO();
        airport_in = bookingDAO.getAirportName();
        airport_out = bookingDAO.getAirportName();        
        setTitle("Booking - step 1");
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
//        setResizable(false);

        date = new JDateChooser();
        date.setDateFormatString("yyyy-MM-dd");
        date.setPreferredSize(new Dimension(240, 40));
        date.setFont(new Font("Times New Roman", Font.PLAIN, 20));        

        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(15,15,15,15);
        
        JLabel lbl = new JLabel("Origin:");
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JLabel lb2 = new JLabel("Destination:");
        lb2.setFont(new Font("Times New Roman", Font.PLAIN, 20));   
        JLabel lb3 = new JLabel("From:");
        lb3.setFont(new Font("Times New Roman", Font.PLAIN, 20));       
        
        jBoxAirport_in = new JComboBox(airport_in);
        jBoxAirport_in.setFont(new Font("Times New Roman", Font.PLAIN, 20));  
        jBoxAirport_out = new JComboBox(airport_out);
        jBoxAirport_out.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        
        cons.gridx = 0;
        cons.gridy = 0;
        add(lbl,cons);
        cons.gridx = 1;
        cons.gridy = 0;
        add(jBoxAirport_in,cons);        
       
        cons.gridx = 0;
        cons.gridy = 1;
        add(lb2,cons); 
        cons.gridx = 1;
        cons.gridy = 1;        
        add(jBoxAirport_out,cons);
        
        cons.gridx = 0;
        cons.gridy = 2;
        add(lb3,cons);   
        cons.gridx = 1;
        cons.gridy = 2;
        add(date,cons);        
 
        cons.gridx = 1;
        cons.gridy = 3;
        btn_search = new JButton("Search", new ImageIcon("image/timkiem_small.png"));
        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
        add(btn_search,cons);          
        
        cons.gridx = 0;
        cons.gridy = 3;
        btn_cancel = new JButton("Cancel", new ImageIcon("image/quaylai_small.png"));
        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new frmMenu();
            }
        });
        add(btn_cancel,cons);         
        
        setVisible(true);
        setLocationRelativeTo(null);        
    }

    
    public static void main(String[] args) throws SQLException {
        new frmBooking();
    }

}
