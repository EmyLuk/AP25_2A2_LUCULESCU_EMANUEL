package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean running = true;

    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("GameServer started on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                new ClientThread(this, clientSocket).start();
            }

        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            running = false;
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.err.println("Error stopping server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 1234;
        new GameServer(port);
    }
}
