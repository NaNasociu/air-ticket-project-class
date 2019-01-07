/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Checking;

import airticket.Controller.CheckingCode.CheckingCodeDAO;
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
public class CheckingCode extends JFrame{
    
    private JTextField txt_code;
    private JButton btn_search, btn_cancel;
    private int size = 20;
    
    public CheckingCode(){
        setTitle("Checking Code");
        setSize(1300,750);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel lbl_ma_sp = new JLabel("Insert Card Number:");
        lbl_ma_sp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(15,15,15,15);
        
        cons.gridx = 0;
        cons.gridy = 1;
        add(lbl_ma_sp,cons);
        cons.gridx = 1;
        cons.gridy = 1;
        txt_code = new JTextField(size);
        txt_code.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(txt_code,cons);  
        
        cons.gridx = 2;
        cons.gridy = 1;
        btn_search = new JButton("Search", new ImageIcon("image/timkiem_small.png"));
        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = txt_code.getText();
                CheckingCodeDAO checkingCodeDAO = new CheckingCodeDAO(code);
                try {
                    checkingCodeDAO.searchInfo();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(btn_search,cons); 

        Icon icon = new ImageIcon("image/boarding.jpg");
        JLabel lb5 = new JLabel(null, icon, JLabel.CENTER);
        cons.gridx = 0;
        cons.gridy = 0;   
        cons.gridwidth = 3;
        add(lb5,cons);
        setVisible(true);
        setLocationRelativeTo(null);
    }


}
