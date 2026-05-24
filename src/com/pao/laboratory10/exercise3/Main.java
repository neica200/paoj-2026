package com.pao.laboratory10.exercise3;
import com.pao.laboratory10.exercise1.TipTranzactie;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<TranzactieExtinsa> tranzactii = new ArrayList<>();

        tranzactii.add(new TranzactieExtinsa(1, 500.00, "2026-01-10", TipTranzactie.CREDIT, "RO12BANC1"));
        tranzactii.add(new TranzactieExtinsa(2, 150.50, "2026-01-15", TipTranzactie.DEBIT, "RO12BANC2"));
        tranzactii.add(new TranzactieExtinsa(3, 1200.00, "2026-01-20", TipTranzactie.CREDIT, "RO12BANC1"));

        tranzactii.add(new TranzactieExtinsa(4, 300.00, "2026-02-05", TipTranzactie.DEBIT, "RO12BANC3"));
        tranzactii.add(new TranzactieExtinsa(5, 450.00, "2026-02-14", TipTranzactie.CREDIT, "RO12BANC2"));
        tranzactii.add(new TranzactieExtinsa(6, 50.00, "2026-02-22", TipTranzactie.DEBIT, "RO12BANC1"));

        tranzactii.add(new TranzactieExtinsa(7, 2100.00, "2026-03-01", TipTranzactie.CREDIT, "RO12BANC4"));
        tranzactii.add(new TranzactieExtinsa(8, 95.00, "2026-03-10", TipTranzactie.DEBIT, "RO12BANC3"));
        tranzactii.add(new TranzactieExtinsa(9, 620.00, "2026-03-18", TipTranzactie.CREDIT, "RO12BANC1"));
        tranzactii.add(new TranzactieExtinsa(10, 110.00, "2026-03-25", TipTranzactie.DEBIT, "RO12BANC2"));

        System.out.println("\n 1. Toate tranzactiile de tip CREDIT:");
        tranzactii.stream().filter(t -> t.getTip() == TipTranzactie.CREDIT).forEach(System.out::println);

        System.out.println("\n 2. Total procesat general:");
        double totalProcesat = tranzactii.stream().mapToDouble(TranzactieExtinsa::getSuma).sum();
        System.out.format("Total procesat: %.2f RON%n", totalProcesat);

        System.out.println("\n 3. Suma totala a tranzactiilor grouped by month (sortate):");
        Map<String, Double> sumePeLuni = tranzactii.stream().collect(Collectors.groupingBy(
                TranzactieExtinsa::getLuna, TreeMap::new, Collectors.summingDouble(TranzactieExtinsa::getSuma)));
        sumePeLuni.forEach((luna, total) -> System.out.format("Per luna %s: %.2f RON%n", luna, total));

        System.out.println("\n 4. Top 3 cele mai mari tranzactii:");
        tranzactii.stream().sorted(Comparator.comparingDouble(TranzactieExtinsa::getSuma).reversed()).limit(3).forEach(System.out::println);

        System.out.println("\n 5. Lista conturilor sursa unice:");
        List<String> conturiUnice = tranzactii.stream().map(TranzactieExtinsa::getContSursa).distinct().collect(Collectors.toList());
        System.out.println("Conturi sursa unice: " + conturiUnice);

        System.out.println("\n 6. Suma medie a tranzactiilor:");
        double medieSuma = tranzactii.stream().mapToDouble(TranzactieExtinsa::getSuma).average().orElse(0.0);
        System.out.format("Suma medie: %.2f RON%n", medieSuma);

        System.out.println("\n 7. Extrase de cont lunare detaliate:");
        Map<String, List<TranzactieExtinsa>> grupatePeLuni = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getLuna(),
                        TreeMap::new,
                        Collectors.toList()
                ));

        grupatePeLuni.forEach((luna, listaLuna) -> {
            double totalLuna = listaLuna.stream().mapToDouble(t -> t.getSuma()).sum();
            System.out.format("EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON%n",
                    luna, listaLuna.size(), totalLuna);
        });
    }
}
