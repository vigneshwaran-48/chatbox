package com.chatbox.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    
    private String appName;

    private static final int APP_MIN_WIDTH = 900;
    private static final int APP_MIN_HEIGHT = 600;

    private static final int HEADER_HEIGHT = 50;
    private static final int SIDEPANEL_WIDTH = 200;

    public MainFrame(String appName) {
        this.appName = appName;

        setTitle(appName);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(APP_MIN_WIDTH, APP_MIN_HEIGHT));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(getHeader(), BorderLayout.PAGE_START);
        mainPanel.add(getBody(), BorderLayout.CENTER);
        mainPanel.add(getSidePanel(), BorderLayout.WEST);

        add(mainPanel);
        
        setVisible(true);
    }

    public JPanel getHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(getWidth(), HEADER_HEIGHT));
        header.setBackground(Color.BLACK);

        JLabel label = new JLabel(appName);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        
        header.add(label, BorderLayout.CENTER);

        return header;
    }

    public JPanel getBody() {
        JPanel body = new JPanel(new BorderLayout());

        int bodyHeight = getHeight() - HEADER_HEIGHT;
        int bodyWidth = getWidth() - SIDEPANEL_WIDTH;

        body.setPreferredSize(new Dimension(bodyWidth, bodyHeight));
        
        JButton createButton = new JButton("Create");
        createButton.setForeground(Color.BLACK);
        createButton.setFont(new Font("Serif", Font.PLAIN, 18));
        createButton.setBounds(createButton.getX(), bodyHeight / 2, createButton.getWidth(), createButton.getHeight());

        JButton joinButton = new JButton("Join");
        joinButton.setForeground(Color.BLACK);
        joinButton.setFont(new Font("Serif", Font.PLAIN, 18));
        joinButton.setBounds(joinButton.getX(), bodyHeight / 2, joinButton.getWidth(), joinButton.getHeight());

        JPanel buttonContainer = new JPanel();
        buttonContainer.add(createButton);
        buttonContainer.add(joinButton);
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(bodyHeight /2, bodyWidth / 4, bodyHeight /2 , bodyWidth / 4));

        body.add(buttonContainer, BorderLayout.CENTER);
        return body;
    }

    public JPanel getSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(SIDEPANEL_WIDTH, getHeight() - HEADER_HEIGHT));
        sidePanel.setBackground(Color.GREEN);
        return sidePanel;
    }
}
