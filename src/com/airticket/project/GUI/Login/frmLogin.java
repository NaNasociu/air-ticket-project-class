package com.airticket.project.GUI.Login;

import com.airticket.project.Controller.LoginDAO.LoginDAO;
import com.airticket.project.GUI.Menu.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

public class frmLogin extends JFrame implements ActionListener {
    private JTextField tf_username;
    private JPasswordField tf_password;
    private JButton btn_login, btn_clear;
    String act_login = "login";
    String act_clear = "clear";

    public frmLogin(){
        setTitle("Login");
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
        panel.setBorder(new EmptyBorder(10,10,10,10));
        //panel.add(createTitlePanel(),BorderLayout.NORTH);
        panel.add(createInputPanel(),BorderLayout.CENTER);
        panel.add(createButtonPanel(),BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createTitlePanel(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Air Ticket Login"));
        return panel;
    }

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(2,2,5,5));
        panel.add(new JLabel("User name:"));
        panel.add(tf_username = new JTextField(15));
        panel.add(new JLabel("Password:"));
        panel.add(tf_password = createPasswordField(act_login,15));
        return panel;
    }

    private JPasswordField createPasswordField(String action, int col){
        JPasswordField password = new JPasswordField(col);
        password.setActionCommand(action);
        password.addActionListener(this);
        return password;
    }

    private JPanel createButtonPanel(){
        JPanel panel = new JPanel();
        panel.add(btn_login = createButton(act_login,"LoginDAO"));
        panel.add(btn_clear = createButton(act_clear,"Clear"));
        return panel;
    }

    private JButton createButton(String action, String name){
        JButton btn = new JButton(name);
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
                    JOptionPane.showMessageDialog(null,"login success!","Success",JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    new frmMainPage();
                } else {
                    JOptionPane.showMessageDialog(null,"Wrong username or password","Fail",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new frmLogin();
    }
}
