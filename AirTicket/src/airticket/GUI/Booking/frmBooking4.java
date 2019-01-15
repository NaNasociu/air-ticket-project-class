/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Booking;

import airticket.Controller.Booking.*;
import airticket.GUI.Menu.frmMenu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author NhatTan
 */
public class frmBooking4 extends JFrame{
    
    private Customers customers;
    private String flightId;
    private FlightStepFour flightStepFour;
    private FlightStepTwo flightInfo;

    public frmBooking4() {}
    
    public frmBooking4(Customers customers, String flightId) throws SQLException {
        this.customers = customers;
        this.flightId = flightId;

        flightStepFour = new FlightStepFour(customers, flightId);
        flightInfo = flightStepFour.getFligtInfo();

        setTitle("Booking - Summary");
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7,1,10,10));
//        setResizable(false);

        //Flight
        JLabel lb_flight = new JLabel("Flight Details");
        lb_flight.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lb_flight.setOpaque(true);
        lb_flight.setBackground(Color.PINK);
        add(lb_flight);
        add(createFlightPanel());

        //Contact
        JLabel lb_contact = new JLabel("Contact Details");
        lb_contact.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lb_contact.setOpaque(true);
        lb_contact.setBackground(Color.cyan);
        add(lb_contact);
        add(createContactPanel());

        //Payment
        JLabel lb_payment = new JLabel("Payment Details");
        lb_payment.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lb_payment.setOpaque(true);
        lb_payment.setBackground(Color.gray);
        add(lb_payment);
        add(createPaymentPanel());

        //button
        add(createButtonPanel());

//        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    } 
    
    private JPanel createButtonPanel(){
        JPanel panel = new JPanel();
        panel.add(createJButton("CONFIRM","image/icon_Ok.png"));
        panel.add(createJButton("Cancel","image/icon_Delete.png"));
        return panel;
    }

    private JPanel createFlightPanel(){
        JPanel panel = new JPanel(new GridLayout(3,4,5,5));
        panel.add(new JLabel("From:",JLabel.CENTER));
        panel.add(new JLabel(flightInfo.getDeparts()));
        panel.add(new JLabel("To:",JLabel.CENTER));
        panel.add(new JLabel(flightInfo.getArrives()));
        panel.add(new JLabel("Departure Date:",JLabel.CENTER));
        panel.add(new JLabel(String.valueOf(flightInfo.getFlightDate())));
        panel.add(new JLabel("Ticket Class:",JLabel.CENTER));
        panel.add(new JLabel("ECO"));
        panel.add(new JLabel("Number passengers:",JLabel.CENTER));
        panel.add(new JLabel("1"));
        return panel;
    }    
    
    private JPanel createContactPanel(){
        JPanel panel = new JPanel(new GridLayout(3,4,5,5));
        System.out.print(customers.getAddress());
        panel.add(new JLabel("Name:",JLabel.CENTER));
        panel.add(new JLabel(customers.getFamilyName()));
        panel.add(new JLabel("Email:",JLabel.CENTER));
        panel.add(new JLabel(customers.getEmail()));
        panel.add(new JLabel("Address:",JLabel.CENTER));
        panel.add(new JLabel(customers.getAddress()));
        panel.add(new JLabel("City:",JLabel.CENTER));
        panel.add(new JLabel(customers.getCity()));
        panel.add(new JLabel("Country:",JLabel.CENTER));
        panel.add(new JLabel("Vietnamese"));
        panel.add(new JLabel("Mobile Phone:",JLabel.CENTER));
        panel.add(new JLabel(customers.getMobileNumber()));
        return panel;
    }

    private JPanel createPaymentPanel(){
        JPanel panel = new JPanel(new GridLayout(2,4,5,5));
        panel.add(new JLabel("Charge:",JLabel.CENTER));
        panel.add(new JLabel("100.000"));
        panel.add(new JLabel("Fare:",JLabel.CENTER));
        panel.add(new JLabel("100.000"));
        panel.add(new JLabel("Tax:",JLabel.CENTER));
        panel.add(new JLabel("10.000"));
        panel.add(new JLabel("Total:",JLabel.CENTER));
        panel.add(new JLabel("10.000"));
        return panel;
    }

    private JButton createJButton(String name,String URL){
        JButton btn = new JButton(name,new ImageIcon(URL));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                    if(command == "Cancel"){
                        setVisible(false);
                        new frmMenu();
                    }

                    if (command == "CONFIRM") {
                        try {
                            flightStepFour.booking();
                            JOptionPane.showMessageDialog(null,"Booking success!","Thank you <3",JOptionPane.INFORMATION_MESSAGE);
                            setVisible(false);
                            new frmMenu();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }}
                });
        return btn;
    }
    
}
