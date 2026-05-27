package com.pao.laboratory13.exercise2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int PORT = 9000;

    public static void main(String[] args) throws Exception {

        ExecutorService clientPool = Executors.newCachedThreadPool();

        Thread serverThread = new Thread(() -> {
            System.out.println("[SERVER] Listening on port " + PORT);
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {

                int clientCounter = 0;

                while (clientCounter < 2) {
                    Socket clientSocket = serverSocket.accept();
                    clientCounter++;
                    String clientId = "CLIENT-" + clientCounter;
                    clientPool.execute(new ClientSessionHandler(clientSocket, clientId));
                }

            } catch (Exception e) {
                System.out.println("[SERVER] Error: " + e.getMessage());
            }
        });
        serverThread.start();
        Thread.sleep(500);

        Thread client1 = new Thread(() -> runMockClient("alice", "SEND hi"));
        Thread client2 = new Thread(() -> runMockClient("bob", "BROADCAST x"));

        client1.start();
        client2.start();

        client1.join();
        client2.join();

        System.out.println("[SERVER] Initiating graceful shutdown...");
        clientPool.shutdown();

        if (clientPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("[SERVER] All clients done. Shutting down.");
        } else {
            System.out.println("[SERVER] Shutdown forced due to timeout.");
        }
    }


    private static void runMockClient(String username, String messageCommand) {
        try (Socket socket = new Socket("localhost", PORT)) {

            socket.setSoTimeout(3000);

            try (
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {

                out.println("AUTH " + username);
                System.out.println("-> Client [" + username + "] received response: " + in.readLine());
                Thread.sleep(200);

                out.println("OPEN");
                System.out.println("-> Client [" + username + "] received response: " + in.readLine());
                Thread.sleep(200);


                out.println(messageCommand);
                System.out.println("-> Client [" + username + "] received response: " + in.readLine());
                Thread.sleep(200);
            }
        } catch (Exception e) {
            System.err.println("Client [" + username + "] error: " + e.getMessage());
        }
    }
}