package com.airticket.project.GUI.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class frmBooking3 extends JFrame implements ActionListener {
    String[] choices   = {"", "Mr","Ms/Mrs"};

    int size = 15;

    public frmBooking3(){
        setTitle("Booking - step 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
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
        //panel.add(new JLabel("Primary Reservation Contact Information"),BorderLayout.NORTH);
        panel.add(createInputPanel(),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,5,5));
        panel.add(createJButton("Continue"));
        panel.add(createJButton("Back"));
        return panel;
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(12,2,5,5));
        panel.add(new JLabel("Title:"));
        panel.add(new JComboBox(choices));
        panel.add(new JLabel("Family Name:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Middle and Given Name:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Address:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("City:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Province/State:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Email:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Date of Birth:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Mobile Number:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Passport Number:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Passport Expiry:"));
        panel.add(new JTextField(size));
        panel.add(new JLabel("Nationality:"));
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
        if(command == "Continue"){
            setVisible(false);
            new frmBooking4();
        }

        if(command == "Back"){
            setVisible(false);
            new frmBooking2();
        }
    }

    public static void main(String[] args) {
        new frmBooking3();
    }

}
