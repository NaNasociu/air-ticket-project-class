package com.airticket.project.GUI.Booking;

import com.airticket.project.GUI.Menu.frmMainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmBooking4 extends JFrame implements ActionListener {

    public frmBooking4(){
        setTitle("Booking - Summary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7,1,10,10));
        setResizable(false);

        //Flight
        JLabel lb_flight = new JLabel("Flight Details");
        lb_flight.setOpaque(true);
        lb_flight.setBackground(Color.PINK);
        add(lb_flight);
        add(createFlightPanel());

        //Contact
        JLabel lb_contact = new JLabel("Contact Details");
        lb_contact.setOpaque(true);
        lb_contact.setBackground(Color.cyan);
        add(lb_contact);
        add(createContactPanel());

        //Payment
        JLabel lb_payment = new JLabel("Payment Details");
        lb_payment.setOpaque(true);
        lb_payment.setBackground(Color.gray);
        add(lb_payment);
        add(createPaymentPanel());

        //button
        add(createButtonPanel());

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }


    private JPanel createButtonPanel(){
        JPanel panel = new JPanel();
        panel.add(createJButton("Finish"));
        return panel;
    }

    private JPanel createFlightPanel(){
        JPanel panel = new JPanel(new GridLayout(3,4,5,5));
        panel.add(new JLabel("From:",JLabel.CENTER));
        panel.add(new JLabel("Biên Hòa"));
        panel.add(new JLabel("To:",JLabel.CENTER));
        panel.add(new JLabel("Chợ Rẫy"));
        panel.add(new JLabel("Departure Date:",JLabel.CENTER));
        panel.add(new JLabel("19-11-2018"));
        panel.add(new JLabel("Ticket Class:",JLabel.CENTER));
        panel.add(new JLabel("VIP"));
        panel.add(new JLabel("Number passengers:",JLabel.CENTER));
        panel.add(new JLabel("1"));
        panel.add(new JLabel("Flight Time:",JLabel.CENTER));
        panel.add(new JLabel("999"));

        return panel;
    }

    private JPanel createContactPanel(){
        JPanel panel = new JPanel(new GridLayout(3,4,5,5));
        panel.add(new JLabel("Name:",JLabel.CENTER));
        panel.add(new JLabel("TanDepTrai"));
        panel.add(new JLabel("Email:",JLabel.CENTER));
        panel.add(new JLabel("test1@bede.com"));
        panel.add(new JLabel("Address:",JLabel.CENTER));
        panel.add(new JLabel("Hospital"));
        panel.add(new JLabel("City:",JLabel.CENTER));
        panel.add(new JLabel("Ho Chi Minh"));
        panel.add(new JLabel("Country:",JLabel.CENTER));
        panel.add(new JLabel("Vietnamese"));
        panel.add(new JLabel("Mobile Phone:",JLabel.CENTER));
        panel.add(new JLabel("113"));
        return panel;
    }

    private JPanel createPaymentPanel(){
        JPanel panel = new JPanel(new GridLayout(2,4,5,5));
        panel.add(new JLabel("Charge:",JLabel.CENTER));
        panel.add(new JLabel("10.000"));
        panel.add(new JLabel("Fare:",JLabel.CENTER));
        panel.add(new JLabel("10.000"));
        panel.add(new JLabel("Tax:",JLabel.CENTER));
        panel.add(new JLabel("10.000"));
        panel.add(new JLabel("Total:",JLabel.CENTER));
        panel.add(new JLabel("10.000"));
        return panel;
    }

    private JButton createJButton(String name){
        JButton btn = new JButton(name);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent evt){
        String command = evt.getActionCommand();
        if(command == "Finish"){
            setVisible(false);
            new frmMainPage();
        }

    }

    public static void main(String[] args) {
        new frmBooking4();
    }


}
