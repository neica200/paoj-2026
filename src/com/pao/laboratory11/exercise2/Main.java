package com.pao.laboratory11.exercise2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TransactionWithAccount> tranzactii = new ArrayList<>();

        String linie;
        String[] tokens;
        int n = 0;
        int q = 0;

        int id;
        double amount;
        String date;
        String country;
        String channel;
        String accountId;
        String comanda;

        while (scanner.hasNextLine()) {
            linie = scanner.nextLine().trim();
            if (linie.isEmpty()) continue;
            n = Integer.parseInt(linie);
            break;
        }

        int contorTxs = 0;
        while (contorTxs < n && scanner.hasNextLine()) {
            linie = scanner.nextLine().trim();
            if (linie.isEmpty()) continue;

            tokens = linie.split("\\s+");
            id = Integer.parseInt(tokens[0]);
            amount = Double.parseDouble(tokens[1]);
            date = tokens[2];
            country = tokens[3];
            channel = tokens[4];
            accountId = tokens[5];

            tranzactii.add(new TransactionWithAccount(id, amount, date, country, channel, accountId));
            contorTxs++;
        }


        while (scanner.hasNextLine()) {
            linie = scanner.nextLine().trim();
            if (linie.isEmpty()) continue;
            q = Integer.parseInt(linie);
            break;
        }

        int contorComenzi = 0;
        while (contorComenzi < q && scanner.hasNextLine()) {
            linie = scanner.nextLine().trim();
            if (linie.isEmpty()) continue;

            tokens = linie.split("\\s+");
            comanda = tokens[0];

            switch (comanda) {
                case "REPORT_MONTH":
                    String lunaCautata = tokens[1];

                    double totalLuna = tranzactii.stream()
                            .filter(tx -> tx.getDate().startsWith(lunaCautata))
                            .mapToDouble(TransactionWithAccount::getAmount)
                            .sum();

                    long countLuna = tranzactii.stream()
                            .filter(tx -> tx.getDate().startsWith(lunaCautata))
                            .count();

                    System.out.printf(Locale.US, "MONTH %s total=%.2f count=%d%n", lunaCautata, totalLuna, countLuna);
                    break;

                case "REPORT_ACCOUNT":
                    String contCautat = tokens[1];

                    double totalAccount = tranzactii.stream()
                            .filter(tx -> tx.getAccountId().equals(contCautat))
                            .mapToDouble(TransactionWithAccount::getAmount)
                            .sum();

                    long countAccount = tranzactii.stream()
                            .filter(tx -> tx.getAccountId().equals(contCautat))
                            .count();

                    System.out.printf(Locale.US, "ACCOUNT %s total=%.2f count=%d%n", contCautat, totalAccount, countAccount);
                    break;

                case "TOP_CHANNELS":
                    int k = Integer.parseInt(tokens[1]);

                    if (tranzactii.isEmpty()) {
                        System.out.println("NONE");
                    } else if (k > 0) {

                        Map<String, Long> counts = tranzactii.stream()
                                .collect(Collectors.groupingBy(TransactionWithAccount::getChannel, Collectors.counting()));

                        counts.entrySet().stream()
                                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                        .thenComparing(Map.Entry.comparingByKey()))
                                .limit(k)
                                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
                    }
                    break;

                default:

                    break;
            }
            contorComenzi++;
        }

        scanner.close();
    }
}