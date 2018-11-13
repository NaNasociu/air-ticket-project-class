package com.airticket.project.GUI;

import javax.swing.*;
import java.awt.*;

public class frmEmployee {

    public static void show(){
        JFrame fr = new JFrame("Employee");
        fr.setSize(800,600);
        JPanel pn=new JPanel(new GridLayout(3,2,5,5));
        pn.setSize(200,200);

        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pn.add( new JLabel("Look Up"));
        pn.add( new JLabel("Emp ID"));
        JTextField txt_emp_id = new JTextField();
        pn.add(txt_emp_id);
        pn.add( new JLabel("Emp Name"));
        JTextField txt_emp_name = new JTextField();
        pn.add(txt_emp_name);


        fr.getContentPane().add(pn);

        fr.setLocationRelativeTo(null);
        fr.pack();
        fr.setVisible(true);

    }


}
