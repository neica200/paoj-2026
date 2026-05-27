package com.pao.laboratory13.exercise1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int q = scanner.nextInt();
        scanner.nextLine();

        ProtocolEngine engine = new ProtocolEngine();

        for (int i = 0; i < q; i++) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                continue;
            }
            String response = engine.processCommand(line);
            if (response != null) {
                System.out.println(response);
            }
        }

        scanner.close();

    }
}
