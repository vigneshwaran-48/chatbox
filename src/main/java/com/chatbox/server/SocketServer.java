package com.chatbox.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SocketServer {
    
    private ServerSocket serverSocket;
    private int port;

    private List<ClientHandler> CLIENTS = new ArrayList<>();

    /**
     * Creates a instance of socket server with given port and DEFAULT_IP (127.0.0.1)
     * @param port
     */
    public SocketServer(int port) {
        this.port = port;
    }

    /**
     * Creates instance of ServerSocket and opens the connection.
     * @throws IOException
     */
    public void startConnection() throws IOException {

        serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for a client to connect ...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Got connection from client => " + clientSocket.getInetAddress().getHostAddress());


            ClientHandler clientHandler = new ClientHandler(UUID.randomUUID().toString(), 
                                                            clientSocket.getInetAddress().getHostName(),
                                                            clientSocket);
            CLIENTS.add(clientHandler);

            Thread cliendHandlerThread = new Thread() {
                public void run() {
                    try (Scanner scanner = new Scanner(clientSocket.getInputStream())) {
                        while(!clientSocket.isClosed()) {
                            if(scanner.hasNextLine()) {
                                sendMessageToGroup(scanner.nextLine());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            };

            cliendHandlerThread.start();
            
        }
    }

    private void sendMessageToGroup(String message) {
        for(ClientHandler clientHandler : CLIENTS) {
            clientHandler.getWriter().println(message);
            clientHandler.getWriter().flush();
        }
    }
}
