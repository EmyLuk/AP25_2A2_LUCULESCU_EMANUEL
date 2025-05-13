package org.example;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private final GameServer server;
    private final Socket socket;

    public ClientThread(GameServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request;
            while ((request = input.readLine()) != null) {
                System.out.println("Received: " + request);

                if (request.equalsIgnoreCase("stop")) {
                    output.println("Server stopped");
                    server.stop();
                    break;
                } else {
                    output.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            System.err.println("ClientThread error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket.");
            }
        }
    }
}
