package com.chatbox.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chatbox.AppManager;

public class LoginPanel extends JPanel {

    private AppManager clientManager;
    
    public LoginPanel(JFrame mainFrame, AppManager clientManager) {

        this.clientManager = clientManager;
        setPreferredSize(new Dimension(mainFrame.getWidth(), 400));
        setBackground(Color.GRAY);

        JTextField nameField = new JTextField();
        nameField.setColumns(25);
        nameField.setFont(new Font("Serif", Font.PLAIN, 16));
        nameField.setLocation(100, 200);
        
        JTextField passwordField = new JTextField();
        passwordField.setColumns(25);
        passwordField.setFont(new Font("Serif", Font.PLAIN, 16));

        add(nameField);
        add(passwordField);
    }
    
}
