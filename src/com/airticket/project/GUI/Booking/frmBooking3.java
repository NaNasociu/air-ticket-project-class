package com.airticket.project.GUI.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.border.EmptyBorder;

import com.airticket.project.Controller.BookingDAO.Customers;
import com.toedter.calendar.JDateChooser;

public class frmBooking3 extends JFrame implements ActionListener {
    String[] choices   = {"Mr","Ms/Mrs"};
    private JDateChooser birthdate, passportExipry;
    private JComboBox jTitle;
    private String selectedAirport_Id;
    private JTextField tf_familyName, tf_middleName, tf_idCard, tf_address, tf_city, tf_state, tf_email, tf_birthDate, tf_mobile, tf_passsport, tf_passportExpiry, tf_nationality;

    public String getSelectedAirport_Id() {
        return selectedAirport_Id;
    }

    public void setSelectedAirport_Id(String selectedAirport_Id) {
        System.out.println("Run here1123");
        System.out.print(selectedAirport_Id);
        this.selectedAirport_Id = selectedAirport_Id;
    }

    int size = 15;

    public frmBooking3(){
        setTitle("Booking - step 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        add(createMainPanel());
        add(createButtonPanel());

        pack();
//        setVisible(true);
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
        JPanel panel = new JPanel(new GridLayout(13,2,5,5));
        panel.add(new JLabel("Title:"));
        panel.add(jTitle = new JComboBox(choices));
        panel.add(new JLabel("Family Name:"));
        panel.add(tf_familyName = new JTextField(size));
        panel.add(new JLabel("Middle and Given Name:"));
        panel.add(tf_middleName = new JTextField(size));
        panel.add(new JLabel("ID Card"));
        panel.add(tf_idCard = new JTextField(size));
        panel.add(new JLabel("Address:"));
        panel.add(tf_address = new JTextField(size));
        panel.add(new JLabel("City:"));
        panel.add(tf_city = new JTextField(size));
        panel.add(new JLabel("Province/State:"));
        panel.add(tf_state = new JTextField(size));
        panel.add(new JLabel("Email:"));
        panel.add(tf_email = new JTextField(size));
        panel.add(new JLabel("Date of Birth:"));
        birthdate = new JDateChooser();
        birthdate.setDateFormatString("yyyy-MM-dd");
        panel.add(birthdate);
        panel.add(new JLabel("Mobile Number:"));
        panel.add(tf_mobile = new JTextField(size));
        panel.add(new JLabel("Passport Number:"));
        panel.add(tf_passsport = new JTextField(size));
        panel.add(new JLabel("Passport Expiry:"));
        passportExipry = new JDateChooser();
        passportExipry.setDateFormatString("yyyy-MM-dd");
        panel.add(passportExipry);
        panel.add(new JLabel("Nationality:"));
        panel.add(tf_nationality = new JTextField(size));
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
            Customers customers = new Customers();
            if (jTitle.getSelectedItem() == "Mr") {
                customers.setSex(0);
            } else {
                customers.setSex(1);
            }
            DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
            customers.setFamilyName(tf_familyName.getText());
            customers.setMiddleAndGivenName(tf_middleName.getText());
            customers.setAddress(tf_address.getText());
            customers.setCity(tf_city.getText());
            customers.setEmail(tf_email.getText());
            customers.setMobileNumber(tf_mobile.getText());
            customers.setNationality(tf_nationality.getText());
            customers.setPassportNumber(tf_passsport.getText());
            customers.setState(tf_state.getText());
            customers.setIdCard(tf_idCard.getText());
            customers.setBirthdate(df.format(birthdate.getDate()));
            customers.setPassportExpiry(df.format(passportExipry.getDate()));
            try {
                customers.createCustomer();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            setVisible(false);
            frmBooking4 frmBooking4;
            try {
                frmBooking4 = new frmBooking4(customers, selectedAirport_Id);
                frmBooking4.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if(command == "Back"){
            setVisible(false);
            try {
                new frmBooking2();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new frmBooking3();
    }

}
