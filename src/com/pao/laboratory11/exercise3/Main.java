package com.pao.laboratory11.exercise3;

import com.pao.laboratory11.exercise2.TransactionWithAccount;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<TransactionWithAccount> dateDemo = Arrays.asList(
                new TransactionWithAccount(1, 1500.00, "2026-05-01", "RO", "WEB", "A1"),
                new TransactionWithAccount(2, 500.00,  "2026-05-02", "NL", "APP", "A2"),
                new TransactionWithAccount(3, 3000.00, "2026-05-02", "RO", "CRYPTO", "A1"),
                new TransactionWithAccount(4, 3000.00, "2026-05-03", "RU", "WEB", "A3"),
                new TransactionWithAccount(5, 120.00,  "2026-05-04", "NL", "APP", "A2"),
                new TransactionWithAccount(6, 450.00,  "2026-05-05", "RO", "ATM", "A1")
        );

        Snapshot snapshot = dateDemo.stream()
                .collect(CustomCollectors.toSnapshot(3));
        System.out.println("DEMO SNAPSHOT ANALITIC IMUTABIL");
        System.out.printf(Locale.US, "Suma totala procesata in snapshot: %.2f RON%n", snapshot.getTotalAmount());

        System.out.println("\n[Interogare 1] Top 3 Tranzactii de Risc (Suma desc, ID asc):");
        snapshot.getTopTransactions().forEach(tx ->
                System.out.printf(Locale.US, " -> ID: %d | Suma: %.2f RON | Tara: %s | Canal: %s%n",
                        tx.getId(), tx.getAmount(), tx.getCountry(), tx.getChannel()));

        System.out.println("\n[Interogare 2] Distributia tranzactiilor pe tari (Descrescator):");
        snapshot.getCountByCountry().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> System.out.println(" -> Tara " + e.getKey() + ": " + e.getValue() + " tranzactii"));

        System.out.println("\n[Interogare 3] Canale active ordonate alfabetic:");
        snapshot.getCountByChannel().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(" -> Canal " + e.getKey() + ": " + e.getValue() + " utilizari"));
    }
}
