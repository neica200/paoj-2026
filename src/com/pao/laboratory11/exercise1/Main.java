package com.pao.laboratory11.exercise1;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Transaction> listTranzactii = new ArrayList<>();
        Map<Integer, Transaction> mapTranzactii = new HashMap<>();

        int n = 0;
        int q = 0;
        int id;
        double amount;
        String date;
        String country;
        String channel;
        String comanda;
        int k;
        int limita;

        Predicate<Transaction> amountOverThreshold = tx -> tx.getAmount() >= 1000;

        Predicate<Transaction> countryInRisk = tx ->
                Transaction.HIGH_RISK_COUNTRIES.contains(tx.getCountry());

        Predicate<Transaction> channelSuspicious = tx ->
                tx.getChannel().equals("WEB") ||
                        tx.getChannel().equals("APP") ||
                        tx.getChannel().equals("CRYPTO");

        Predicate<Transaction> flaggedRule = amountOverThreshold.or(countryInRisk).or(channelSuspicious);

        Comparator<Transaction> BY_RISK_DESC_THEN_ID_ASC = Comparator.comparingInt(Transaction::calculateScore).reversed().thenComparingInt(Transaction::getId);

        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                id = scanner.nextInt();
                amount = Double.parseDouble(scanner.next());
                date = scanner.next();
                country = scanner.next();
                channel = scanner.next();

                Transaction tx = new Transaction(id, amount, date, country, channel);
                listTranzactii.add(tx);
                mapTranzactii.put(id, tx);
            }
        }

        if (scanner.hasNextInt()) {
            q = scanner.nextInt();
            for (int i = 0; i < q; i++) {
                comanda = scanner.next();

                switch (comanda) {
                    case "CHECK":
                        id = scanner.nextInt();
                        Transaction txCheck = mapTranzactii.get(id);

                        if (txCheck == null) {
                            System.out.println("CHECK " + id + " => NOT_FOUND");
                        } else {
                            System.out.println("CHECK " + id + " => " + txCheck.getVerdict() + " score=" + txCheck.calculateScore());
                        }
                        break;

                    case "LIST_FLAGGED":
                        List<Transaction> flaggedList = new ArrayList<>();

                        for (Transaction tx : listTranzactii) {
                            if (tx.getVerdict().equals("FLAG")) {
                                flaggedList.add(tx);
                            }
                        }

                        if (flaggedList.isEmpty()) {
                            System.out.println("NONE");
                        } else {
                            flaggedList.sort(BY_RISK_DESC_THEN_ID_ASC);
                            for (Transaction tx : flaggedList) {
                                System.out.println("[" + tx.getId() + "] FLAG score=" + tx.calculateScore());
                            }
                        }
                        break;

                    case "TOP_RISK":
                        k = scanner.nextInt();
                        if (k > 0 && !listTranzactii.isEmpty()) {
                            List<Transaction> sortedCopy = new ArrayList<>(listTranzactii);

                            sortedCopy.sort(BY_RISK_DESC_THEN_ID_ASC);

                            limita = Math.min(k, sortedCopy.size());
                            for (int j = 0; j < limita; j++) {
                                Transaction tx = sortedCopy.get(j);
                                System.out.println("[" + tx.getId() + "] " + tx.getVerdict() + " score=" + tx.calculateScore());
                            }
                        }
                        break;

                    default:
                        System.out.println("ERR UNKNOWN_COMMAND");
                        break;
                }
            }
        }
        scanner.close();
    }
}

