package com.chatbox.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {
    
    private String clientId;
    private String clientName;
    private Socket clientSocket;
    private PrintWriter printWriter;

    public ClientHandler(String clientId, String clientName, Socket clientSocket) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSocket = clientSocket;

        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public PrintWriter getWriter() {
        return printWriter;
    }

    public void sendMessageToClient(String message) {
        try(PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream())) {
            printWriter.println(message);
        }
        catch(IOException e) {
            System.out.println("Error while sending message to client ...");
            e.printStackTrace();
        }
    }
    

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ClientHandler) {
            ClientHandler other = (ClientHandler) obj;
            return this.getClientId().equals(other.getClientId());
        }
        else {
            return false;
        }
    }
}
