package com.airticket.project.GUI.Menu;

import javax.swing.*;
import java.awt.*;

public class frmMainPage extends JFrame {
    public frmMainPage() {
        setTitle("Menu");
        setLayout(new GridLayout(3, 2, 5, 5));
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create image
        Icon icon = new ImageIcon(getClass().getResource("airplane.png"));

        //create label
        JButton lb1,lb2,lb3,lb4;
        lb1 = createJButton("Booking", Color.red, Color.pink);
        lb2 = createJButton("Function 2", Color.red, Color.yellow);
        lb3 = createJButton("Function 3", Color.red, Color.cyan);
        lb4 = createJButton("Function 4", Color.red, Color.gray);
        JLabel lb5 = new JLabel("icon", icon, JLabel.CENTER);

        add(lb1);
        add(lb2);
        add(lb3);
        add(lb4);
        add(lb5);

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
        return btn;
    }
    
    public static void main(String[] args){
        new frmMainPage();
    }
}
