/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airticket.GUI.Login;

import airticket.Controller.Login.LoginDAO;
import airticket.GUI.Menu.frmMenu;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
/**
 *
 * @author NhatTan
 */
public class frmLogin extends JFrame implements ActionListener{
    
    private JTextField tf_username;
    private JPasswordField tf_password;
    private JButton btn_login, btn_clear;
    String act_login = "login";
    String act_clear = "clear";  
            
    public frmLogin(){
        setTitle("Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(createMainPanel());

        //display
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(100,100,100,100));
        panel.add(createTitlePanel(),BorderLayout.NORTH);
        panel.add(createInputPanel(),BorderLayout.CENTER);
        panel.add(createButtonPanel(),BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createTitlePanel(){
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("AirTicket Login");
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        panel.add(lbl);
        return panel;
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        JLabel lb2 = new JLabel("User name:");
        lb2.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        panel.add(lb2);    
        tf_username = new JTextField(15);
        tf_username.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_username);
        JLabel lb3 = new JLabel("Password:");
        lb3.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        panel.add(lb3);          
        panel.add(tf_password = createPasswordField(act_login,15));
        return panel;
    }

    private JPasswordField createPasswordField(String action, int col){
        JPasswordField password = new JPasswordField(col);
        password.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
        password.setActionCommand(action);
        password.addActionListener(this);
        return password;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel();
        btn_login = createButton(act_login,"Login","image/khoa.png");
        btn_login.setPreferredSize(new Dimension(90, 30));
        panel.add(btn_login);
        btn_clear = createButton(act_clear,"Clear","image/huy.png");
        btn_clear.setPreferredSize(new Dimension(90, 30));
        panel.add(btn_clear);
        return panel;
    }

    private JButton createButton(String action, String name, String URL){
        JButton btn = new JButton(name,new ImageIcon(URL));
        btn.setPreferredSize(new Dimension(100, 50));
        btn.addActionListener(this);
        btn.setActionCommand(action);
        return btn;
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if (act_login.equals(command)) {
            try {
                LoginDAO loginDao = new LoginDAO();
                loginDao.setUsername(tf_username.getText());
                loginDao.setPassword(String.valueOf(tf_password.getPassword()));
                if (loginDao.loginSession()) {
                    JOptionPane.showMessageDialog(null,"Login Success!","Success",JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    new frmMenu();
                } else {
                    JOptionPane.showMessageDialog(null,"Wrong username or password","Fail",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if(act_clear.equals(command)){
            tf_username.setText("");
            tf_password.setText("");
        }           
    }
    
    public static void main(String[] args) {
        new frmLogin();
    }
}
