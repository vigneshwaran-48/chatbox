package com.chatbox.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class TextField extends JLabel {

        public TextField(String text, Color color, Font font) {
         
            setText(text);
            setFont(font);
            setForeground(color);
        } 

        public TextField(String text, Color color) {
            this(text, color, new Font("Serif", Font.BOLD, 18));
        }
    }