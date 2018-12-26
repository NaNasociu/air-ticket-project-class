/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Menu;

import airticket.GUI.Flight.*;
import airticket.GUI.Booking.*;
import airticket.GUI.Checking.CheckingCode;
import airticket.GUI.Ticket.frmTicket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author NhatTan
 */
public class frmMenu extends JFrame implements ActionListener{
    
    private JButton btn1,btn2,btn3,btn4;
    
    public frmMenu(){
        setTitle("Menu");
        setLayout(new GridLayout(2, 1, 5, 5));
        setSize(1355,820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create image
        Icon icon = new ImageIcon(getClass().getResource("airplane2.png"));

        //create label
        JPanel panel = new JPanel(new GridLayout(2,2,5,5));
        
        btn1 = createJButton("Booking Ticket", Color.black, Color.pink);
        btn2 = createJButton("Airport", Color.black, Color.yellow);
        btn3 = createJButton("Checking Code", Color.black, Color.cyan);
        btn4 = createJButton("Revenue Report", Color.black, Color.pink);
        JLabel lb5 = new JLabel(null, icon, JLabel.CENTER);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        
        add(lb5);
        add(panel);

        //display
        setLocationRelativeTo(null);
        setVisible(true);         
    }
    
    private JButton createJButton(String txt, Color txtColor, Color bgColor){
        JButton btn = new JButton(txt);
        btn.setForeground(txtColor);
        //set bg color
        btn.setOpaque(true);
        btn.setBackground(bgColor);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "Booking Ticket"){
            setVisible(false);
            try {
                new frmBooking();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (command == "Checking Code") {
            setVisible(false);
            try {
                new CheckingCode();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }        
        if(command == "Airport"){
            try {
                new frmAirport();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        
        if(command == "Revenue Report"){
            try {
                new frmTicket();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }        
    }    
    
    public static void main(String[] args){
        new frmMenu();
    }

    
    
}
