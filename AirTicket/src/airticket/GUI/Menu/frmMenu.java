/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Menu;

import airticket.GUI.Flight.*;
import airticket.GUI.Booking.*;
import airticket.GUI.Checking.CheckingCode;
import airticket.GUI.Info.frmInfo;
import airticket.GUI.Ticket.frmTicket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author NhatTan
 */
public class frmMenu extends JFrame{
    
    private JButton btn1,btn2,btn3,btn4,btn5,btn6;
    
    public frmMenu(){
        setTitle("Menu");
        setLayout(new GridLayout(2, 1, 5, 5));
        setSize(1600,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create image
        Icon icon = new ImageIcon("image/main.jpg");

        //create label
        JPanel panel = new JPanel(new GridLayout(2,3,5,5));
        
        btn1 = new JButton("Booking Ticket",new ImageIcon("image/ticket.png"));
        btn2 = new JButton("Airport",new ImageIcon("image/plane.png"));
        btn3 = new JButton("Checking Code",new ImageIcon("image/check_code.png"));
        btn4 = new JButton("Revenue Report",new ImageIcon("image/reports.png"));   
        btn5 = new JButton("Flight",new ImageIcon("image/flight.png")); 
        btn6 = new JButton("About",new ImageIcon("image/Info.png")); 
        JLabel lb5 = new JLabel(null, icon, JLabel.CENTER);

        panel.add(btn1);
        panel.add(btn5);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn6);
        add(lb5);
        add(panel);
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    new frmBooking();
                } catch (SQLException ex) {
                    Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new frmAirport();
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckingCode();
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frmTicket();
            }
        });        
        
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frmFlight();
            }
        });       
        
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frmInfo();
            }
        });         

        //display
        setLocationRelativeTo(null);
        setVisible(true);         
    }

    public static void main(String[] args){
        new frmMenu();
    }

    
    
}
