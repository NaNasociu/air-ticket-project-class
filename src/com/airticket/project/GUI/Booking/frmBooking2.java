package com.airticket.project.GUI.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.border.EmptyBorder;

public class frmBooking2 extends JFrame implements ActionListener {
    String[] columnNames = {"Flight Date", "Departs", "Arrives", "Flight Detail","Promo", "Eco","Skyboss"};
    Object [][] data = {};

    int size = 15;

    public frmBooking2(){
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

    private JPanel createMainPanel(){
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

    private JPanel createInputPanel(){
        JPanel panel = new JPanel();
        JTable t = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(t);
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

    public static void main(String[] args) {
        new frmBooking2();
    }


}
