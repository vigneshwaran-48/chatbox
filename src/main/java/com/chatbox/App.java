package com.chatbox;

import java.io.IOException;

import com.chatbox.client.SocketClient;
import com.chatbox.server.SocketServer;

public class App {

    public static void main( String[] args ) throws IOException {
        if(args.length > 1) {
            if(args[0].equals("server")) {
                SocketServer server = new SocketServer(Integer.parseInt(args[1]));
                server.startConnection();
            }
            else {
                SocketClient client = new SocketClient(Integer.parseInt(args[1]), args[2]);
                client.connect();
            }
        }
    }
}
