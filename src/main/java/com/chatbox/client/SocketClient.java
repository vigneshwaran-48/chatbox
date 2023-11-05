package com.chatbox.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.chatbox.dto.Message;

public class SocketClient {
    
    private int port;
    private String ip;
    private String name;

    private static final String DEFAULT_IP = "127.0.0.1";

    /**
     * Creates a instance of socket client with given port ip
     * @param port
     * @param ip
     */
    public SocketClient(String name, int port, String ip) {
        this.port = port;
        this.ip = ip;
        this.name = name;
    }

    /**
     * Creates a instance of socket client with given port and DEFAULT_IP (127.0.0.1)
     * @param port
     */
    public SocketClient(String name, int port) {
        this.port = port;
        this.ip = DEFAULT_IP;
        this.name = name;
    }

    public void connect() throws UnknownHostException, IOException {

        Socket server = new Socket(ip, port);

        if(!server.isClosed()) {
            SenderThread senderThread = new SenderThread(server);
            senderThread.start();

            ReceiverThread receiverThread = new ReceiverThread(server);
            receiverThread.start();
        }
        else {
            System.out.println("Server is closed ....");
        }
        
    }

    private class SenderThread extends Thread {

        private Socket socket;

        public SenderThread(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try(Scanner scanner = new Scanner(System.in); 
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {

                while (!socket.isClosed()) {
                    if(scanner.hasNextLine()) {
                        
                        String text = scanner.nextLine();
                        Message message = new Message(name, text);

                        printWriter.println(message.toString());
                        printWriter.flush();
                    }
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReceiverThread extends Thread {
        private Socket socket;

        public ReceiverThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            
            try(Scanner scanner = new Scanner(socket.getInputStream())) {
                while(!socket.isClosed()) {
                    if(scanner.hasNextLine()) {
                        String text = scanner.nextLine();
                        System.out.println(Message.toMessage(text).getMessage());
                    }
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        SocketClient client = new SocketClient("Vicky", 3456);
        client.connect();
    }
}
