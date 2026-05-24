package com.pao.laboratory10.exercise1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Tranzactie> coada = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int id;
        double suma;
        String data;
        TipTranzactie tip;
        double threshold;
        int contor;
        Iterator<Tranzactie> itr;

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            switch (comanda) {
                case "ENQUEUE":
                    id = scanner.nextInt();
                    suma = Double.parseDouble(scanner.next());
                    data = scanner.next();
                    tip = TipTranzactie.valueOf(scanner.next());

                    coada.addLast(new Tranzactie(id, suma, data, tip));
                    break;
                case "DEQUEUE":
                    if (coada.isEmpty()) {
                        System.out.println("Coada goala.");
                    } else {
                        Tranzactie t = coada.removeFirst();
                        System.out.println("Procesat: " + t);
                    }
                    break;

                case "PUSH":
                    id = scanner.nextInt();
                    suma = Double.parseDouble(scanner.next());
                    data = scanner.next();
                    tip = TipTranzactie.valueOf(scanner.next());

                    coada.addFirst(new Tranzactie(id, suma, data, tip));
                    break;

                case "POP":
                    if (coada.isEmpty()) {
                        System.out.println("Coada goala.");
                    } else {
                        Tranzactie t = coada.removeFirst();
                        System.out.println("Extras: " + t);
                    }
                    break;

                case "REMOVE_DEBIT":
                    contor = 0;
                    itr = coada.iterator();

                    while (itr.hasNext()) {
                        Tranzactie t = itr.next();
                        if (t.getTip() == TipTranzactie.DEBIT) {
                            itr.remove();
                            contor++;
                        }
                    }
                    System.out.println("Eliminat " + contor + " tranzactii DEBIT.");
                    break;

                case "REMOVE_BELOW":
                    threshold = Double.parseDouble(scanner.next());
                    contor = 0;
                    itr = coada.iterator();

                    while (itr.hasNext()) {
                        Tranzactie t = itr.next();
                        if (t.getSuma() < threshold) {
                            itr.remove();
                            contor++;
                        }
                    }
                    System.out.format("Eliminat %d tranzactii sub %.2f RON.%n", contor, threshold);
                    break;

                case "PRINT":
                    for (Tranzactie t : coada) {
                        System.out.println(t);
                    }
                    break;

                case "SIZE":
                    System.out.println("Dimensiune coada: " + coada.size());
                    break;
                default:
                    // Opțional: tratare comandă necunoscută
                    break;
            }
        }
        scanner.close();
    }

}
