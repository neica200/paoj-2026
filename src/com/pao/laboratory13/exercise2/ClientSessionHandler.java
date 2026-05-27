package com.pao.laboratory13.exercise2;

import com.pao.laboratory13.exercise1.ProtocolEngine;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSessionHandler implements Runnable{
    private final Socket clientSocket;
    private final String clientId;
    private final ProtocolEngine engine;

    public ClientSessionHandler(Socket socket, String clientId) {
        this.clientSocket = socket;
        this.clientId = clientId;
        this.engine = new ProtocolEngine();
    }

    @Override
    public void run() {
        System.out.println("[" + clientId + "] Connected.");

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("[" + clientId + "] >> " + inputLine);

                String response = engine.processCommand(inputLine);

                if (response != null) {
                    writer.println(response);
                    System.out.println("[" + clientId + "] << " + response);
                }
            }
        } catch (Exception e) {
            System.out.println("[" + clientId + "] Error during session: " + e.getMessage());
        } finally {

            try {
                clientSocket.close();
                System.out.println("[" + clientId + "] Disconnected.");
            } catch (Exception ignored) {}
        }
    }
}