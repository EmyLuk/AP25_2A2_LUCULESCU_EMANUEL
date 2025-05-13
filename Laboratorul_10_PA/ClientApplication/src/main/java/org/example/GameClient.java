package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 1234;

        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to the server. Type 'exit' to quit.");
            String command;

            while (true) {
                System.out.print(">> ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("exit")) {
                    break;
                }

                output.println(command);
                String response = input.readLine();
                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            System.err.println("GameClient error: " + e.getMessage());
        }
    }
}
