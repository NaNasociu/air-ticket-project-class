package com.airticket.project.GUI;

import javax.swing.*;

public class frmLogin {

    public static void show(){
        JFrame fr = new JFrame("Login");
        fr.setSize(400,300);

        fr.setResizable(false);
        fr.setLayout(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel name     = new JLabel("User name");
        JLabel password = new JLabel("Password");
        JButton Ok      = new JButton("Login");
        JTextField txt_name = new JTextField();
        JTextField txt_pass = new JTextField();
        txt_name.setSize(150,30);
        txt_pass.setSize(150,30);
        name.setSize(100,30);
        password.setSize(100,30);
        Ok.setSize(100,30);

        name.setLocation(50,50);
        password.setLocation(50,100);
        txt_name.setLocation(150,50);
        txt_pass.setLocation(150,100);
        Ok.setLocation(150,160);

        fr.add(name);
        fr.add(password);
        fr.add(txt_name);
        fr.add(txt_pass);
        fr.add(Ok);

        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }



}
