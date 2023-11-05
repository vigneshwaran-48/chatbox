package com.chatbox;

import com.chatbox.gui.ChatAppFrame;
import java.io.IOException;

import com.chatbox.gui.MainFrame;

public class App {

    public static void main( String[] args ) throws Exception {
     
        ChatAppFrame app = new ChatAppFrame();
        app.setVisible(true);
        
        app.showLoginPage();
    }
}
