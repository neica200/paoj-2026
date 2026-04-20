package com.pao.laboratory07.exercise3;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<Comanda> comenzi = new ArrayList<>();

        // 🔹 Citire comenzi
        for (int i = 0; i < n; i++) {
            String[] t = sc.nextLine().split(" ");

            switch (t[0]) {
                case "STANDARD" -> comenzi.add(
                        new ComandaStandard(t[1], Double.parseDouble(t[2]), t[3])
                );
                case "DISCOUNTED" -> comenzi.add(
                        new ComandaRedusa(t[1], Double.parseDouble(t[2]),
                                Integer.parseInt(t[3]), t[4])
                );
                case "GIFT" -> comenzi.add(
                        new ComandaGratuita(t[1], t[2])
                );
            }
        }

        // 🔹 Afișare inițială
        comenzi.forEach(c -> System.out.println(c.descriere()));

        // 🔹 Procesare comenzi
        while (true) {
            String line = sc.nextLine();

            if (line.equals("QUIT")) break;

            if (line.equals("STATS")) {
                System.out.println("\n--- STATS ---");

                comenzi.stream()
                        .collect(Collectors.groupingBy(
                                c -> {
                                    if (c instanceof ComandaStandard) return "STANDARD";
                                    if (c instanceof ComandaRedusa) return "DISCOUNTED";
                                    return "GIFT";
                                },
                                Collectors.averagingDouble(Comanda::pretFinal)
                        ))
                        .forEach((tip, medie) ->
                                System.out.printf("%s: medie = %.2f lei\n", tip, medie)
                        );
            }

            else if (line.startsWith("FILTER")) {
                double threshold = Double.parseDouble(line.split(" ")[1]);

                System.out.println("\n--- FILTER (>= " + String.format("%.2f", threshold) + ") ---");

                comenzi.stream()
                        .filter(c -> c.pretFinal() >= threshold)
                        .forEach(c ->
                                System.out.printf("%s, pret: %.2f lei - client: %s\n",
                                        getTip(c) + ": " + c.nume,
                                        c.pretFinal(),
                                        c.client
                                )
                        );
            }

            else if (line.equals("SORT")) {
                System.out.println("\n--- SORT (by client, then by pret) ---");

                comenzi.stream()
                        .sorted(Comparator
                                .comparing((Comanda c) -> c.client)
                                .thenComparing(Comanda::pretFinal)
                        )
                        .forEach(c ->
                                System.out.printf("%s, pret: %.2f lei - client: %s\n",
                                        getTip(c) + ": " + c.nume,
                                        c.pretFinal(),
                                        c.client
                                )
                        );
            }

            else if (line.equals("SPECIAL")) {
                System.out.println("\n--- SPECIAL (discount > 15%) ---");

                comenzi.stream()
                        .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscount() > 15)
                        .forEach(c -> {
                            ComandaRedusa cr = (ComandaRedusa) c;
                            System.out.printf(
                                    "DISCOUNTED: %s, pret: %.2f lei (-%d%%) - client: %s\n",
                                    cr.nume,
                                    cr.pretFinal(),
                                    cr.getDiscount(),
                                    cr.client
                            );
                        });
            }
        }
    }

    // 🔹 helper pentru tip
    private static String getTip(Comanda c) {
        if (c instanceof ComandaStandard) return "STANDARD";
        if (c instanceof ComandaRedusa) return "DISCOUNTED";
        return "GIFT";
    }
}