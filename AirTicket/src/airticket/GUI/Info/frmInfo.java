/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Info;

import airticket.Controller.CheckingCode.CheckingCodeDAO;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author NhatTan
 */
public class frmInfo extends JFrame{
    
    private JTextField txt_code;
    private JButton btn_search, btn_cancel;
    private int size = 20;
    
    public frmInfo(){
        setTitle("Software Version");
        setSize(1300,750);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel lb1 = new JLabel("16521062 - Do Xuan Dai");
        lb1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(15,15,15,15);
       

        cons.gridx = 0;
        cons.gridy = 1;
        add(lb1,cons);
        
        JLabel lb2 = new JLabel("16521076 - Nguyen Hoang Nhat Tan");
        lb2.setFont(new Font("Times New Roman", Font.PLAIN, 20));      
        cons.gridx = 3;
        cons.gridy = 1;
        add(lb2,cons);        
 
        Icon icon2 = new ImageIcon("image/2.jpg");
        JLabel lb6 = new JLabel(null, icon2, JLabel.CENTER);
        cons.gridx = 1;
        cons.gridy = 0;   
        add(lb6,cons);        
        
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        new frmInfo();
    }
    
    
}
