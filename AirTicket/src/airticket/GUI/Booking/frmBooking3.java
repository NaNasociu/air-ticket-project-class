/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Booking;

import airticket.Controller.Booking.Customers;
import airticket.GUI.Menu.frmMenu;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author NhatTan
 */
public class frmBooking3 extends JFrame{
    int size = 20;
    String[] choices   = {"Mr","Ms/Mrs"};
    private JDateChooser birthdate, passportExipry;
    private JComboBox jTitle;
    private String selectedAirport_Id;
    private JTextField tf_familyName, tf_middleName, tf_idCard, tf_address, tf_city, tf_state, tf_email, tf_mobile, tf_passsport, tf_nationality;
    
    public void setSelectedAirport_Id(String selectedAirport_Id) {
        this.selectedAirport_Id = selectedAirport_Id;
    }   
    
    public frmBooking3(){
        setTitle("Booking - step 3");
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
//        setResizable(false);
        add(createMainPanel());
        add(createButtonPanel());

//        pack();
        setLocationRelativeTo(null);
    } 
    
    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(50,50,50,50));
        panel.add(createInputPanel(),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,20,20));
        panel.add(createJButton("Continue","image/next_medium.png"));
        panel.add(createJButton("Back","image/back_medium.png"));
        return panel;
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(13,2,10,10));
        JLabel lb_title = new JLabel("Title:");
        lb_title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(lb_title);
        jTitle = new JComboBox(choices);
        jTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(jTitle);
        JLabel lb_fname = new JLabel("Family Name:");
        lb_fname.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        panel.add(lb_fname);
        tf_familyName = new JTextField(size);
        tf_familyName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_familyName);
        JLabel lb_lname = new JLabel("Middle and Given Name:");
        lb_lname.setFont(new Font("Times New Roman", Font.PLAIN, 20));           
        panel.add(lb_lname);
        tf_middleName = new JTextField(size);
        tf_middleName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_middleName);
        JLabel lb_idcard = new JLabel("ID Card:");
        lb_idcard.setFont(new Font("Times New Roman", Font.PLAIN, 20));          
        panel.add(lb_idcard);
        tf_idCard = new JTextField(size);
        tf_idCard.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_idCard);
        JLabel lb_address = new JLabel("Address:");
        lb_address.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        panel.add(lb_address);
        tf_address = new JTextField(size);
        tf_address.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_address);
        JLabel lb_city = new JLabel("City:");
        lb_city.setFont(new Font("Times New Roman", Font.PLAIN, 20));            
        panel.add(lb_city);
        tf_city = new JTextField(size);
        tf_city.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_city);
        JLabel lb_province = new JLabel("Province/State:");
        lb_province.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        panel.add(lb_province);
        tf_state = new JTextField(size);
        tf_state.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        panel.add(tf_state);
        JLabel lb_email = new JLabel("Email:");
        lb_email.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        panel.add(lb_email);
        tf_email = new JTextField(size);
        tf_email.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        panel.add(tf_email);
        JLabel lb_bday = new JLabel("Date of Birth:");
        lb_bday.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        panel.add(lb_bday);
        birthdate = new JDateChooser();
        birthdate.setDateFormatString("yyyy-MM-dd");
        birthdate.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        panel.add(birthdate);
        JLabel lb_mobile = new JLabel("Mobile:");
        lb_mobile.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        panel.add(lb_mobile);
        tf_mobile = new JTextField(size);
        tf_mobile.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_mobile);
        JLabel lb_passport = new JLabel("Passport Number:");
        lb_passport.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        panel.add(lb_passport);
        tf_passsport = new JTextField(size);
        tf_passsport.setFont(new Font("Times New Roman", Font.PLAIN, 20)); 
        panel.add(tf_passsport);
        JLabel lb_passport_ex = new JLabel("Passport Expiry:");
        lb_passport_ex.setFont(new Font("Times New Roman", Font.PLAIN, 20));         
        panel.add(lb_passport_ex);
        passportExipry = new JDateChooser();
        passportExipry.setDateFormatString("yyyy-MM-dd");
        passportExipry.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(passportExipry);
        JLabel lb_nation = new JLabel("Nationality:");
        lb_nation.setFont(new Font("Times New Roman", Font.PLAIN, 20));          
        panel.add(lb_nation);
        tf_nationality = new JTextField(size);
        tf_nationality.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_nationality);
        return panel;
    }    
    
    private JButton createJButton(String name, String URL){
        JButton btn = new JButton(name,new ImageIcon(URL));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
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
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    setVisible(false);
                    frmBooking4 frmBooking4;
                    try {
                        frmBooking4 = new frmBooking4(customers, selectedAirport_Id);
                        frmBooking4.setVisible(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if(command == "Back"){
                    setVisible(false);
                    new frmMenu();
                }                
            }
        });
        return btn;
    }    
    
}
