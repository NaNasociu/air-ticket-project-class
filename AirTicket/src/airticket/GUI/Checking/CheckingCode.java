/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Checking;

import airticket.Controller.CheckingCode.CheckingCodeDAO;
import airticket.GUI.Booking.frmBooking;
import airticket.GUI.Menu.frmMenu;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author NhatTan
 */
public class CheckingCode extends JFrame implements ActionListener{
    
    private JTextField tf_code;
    private int size = 30;
    
    public CheckingCode(){
        setTitle("Checking Code");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
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
        panel.add(createInputPanel(),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(2,2,5,5));
        panel.add(new JLabel("Ma code:"));
        panel.add(tf_code = new JTextField(size));
        return panel;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel(new GridLayout(2,1,5,5));
        panel.add(createJButton("Xuat ve"));
        panel.add(createJButton("Cancel"));
        return panel;
    }

    private JButton createJButton(String name){
        JButton btn = new JButton(name);
        btn.addActionListener(this);
        return btn;
    }

    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command == "Cancel") {
            setVisible(false);
            new frmMenu();
        }
        if (command == "Xuat ve") {
            String code = tf_code.getText();
            CheckingCodeDAO checkingCodeDAO = new CheckingCodeDAO(code);
            try {
                checkingCodeDAO.searchInfo();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }    
}
