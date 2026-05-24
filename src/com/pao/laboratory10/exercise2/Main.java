package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tranzactie> listaTranzactii = new ArrayList<>();
        int n;
        int id;
        double suma;
        String data;
        TipTranzactie tip;
        String comanda;

        if (scanner.hasNextInt()) {
            n = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                id = scanner.nextInt();
                suma = Double.parseDouble(scanner.next());
                data = scanner.next();
                tip = TipTranzactie.valueOf(scanner.next());

                listaTranzactii.add(new Tranzactie(id, suma, data, tip));
            }
        }

        int topN;
        int limita;
        LinkedHashSet<Integer> unici;
        TreeMap<String, double[]> raport;
        ArrayList<Tranzactie> copieLista;
        Tranzactie tranzactieMin;
        Tranzactie tranzactieMax;

        while (scanner.hasNext()) {
            comanda = scanner.next();

            switch (comanda) {
                case "UNIQUE_IDS":
                    unici = new LinkedHashSet<>();
                    for (Tranzactie t : listaTranzactii) {
                        unici.add(t.getId());
                    }
                    System.out.println("IDs unice (" + unici.size() + "): " + unici);
                    break;

                case "MONTHLY_REPORT":
                    raport = new TreeMap<>();
                    for (Tranzactie t : listaTranzactii) {
                        String luna = t.getData().substring(0, 7);

                        if (!raport.containsKey(luna)) {
                            raport.put(luna, new double[2]);
                        }

                        double[] sume = raport.get(luna);
                        if (t.getTip() == TipTranzactie.CREDIT) {
                            sume[0] += t.getSuma();
                        } else {
                            sume[1] += t.getSuma();
                        }
                    }

                    for (Map.Entry<String, double[]> entry : raport.entrySet()) {
                        String luna = entry.getKey();
                        double[] sume = entry.getValue();
                        System.out.format("%s: CREDIT %.2f RON, DEBIT %.2f RON%n", luna, sume[0], sume[1]);
                    }
                    break;

                case "TOP":
                    topN = scanner.nextInt();
                    copieLista = new ArrayList<>(listaTranzactii);

                    Collections.sort(copieLista, Comparator.comparingDouble(Tranzactie::getSuma).reversed());
                    limita = Math.min(topN, copieLista.size());

                    System.out.println("Top " + topN + ":");
                    for (Tranzactie t : copieLista.subList(0, limita)) {
                        System.out.println(t);
                    }
                    break;

                case "SORT_ASC":
                    Collections.sort(listaTranzactii, Comparator.comparingDouble(Tranzactie::getSuma));
                    for (Tranzactie t : listaTranzactii) {
                        System.out.println(t);
                    }
                    break;

                case "SORT_DESC":
                    Collections.sort(listaTranzactii, Comparator.comparingDouble(Tranzactie::getSuma).reversed());
                    for (Tranzactie t : listaTranzactii) {
                        System.out.println(t);
                    }
                    break;

                case "REVERSE":
                    Collections.reverse(listaTranzactii);
                    for (Tranzactie t : listaTranzactii) {
                        System.out.println(t);
                    }
                    break;

                case "MIN_MAX":
                    if (!listaTranzactii.isEmpty()) {
                        tranzactieMin = Collections.min(listaTranzactii, Comparator.comparingDouble(Tranzactie::getSuma));
                        tranzactieMax = Collections.max(listaTranzactii, Comparator.comparingDouble(Tranzactie::getSuma));

                        System.out.println("MIN: " + tranzactieMin);
                        System.out.println("MAX: " + tranzactieMax);
                    }
                    break;

                case "CME_DEMO":
                    try {
                        for (Tranzactie t : listaTranzactii) {
                            listaTranzactii.remove(t);
                        }
                    } catch (ConcurrentModificationException e) {
                        System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                    }
                    break;
            }
        }

        scanner.close();
    }
}
