package com.pao.laboratory06.exercise3;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Inginer[] ingineri = {
                new Inginer("Popescu", "Andrei", "071", 8000, 10000),
                new Inginer("Ionescu", "Mihai", "072", 9000, 12000),
                new Inginer("Popa", "Maria", "073", 7000, 8000)
        };

        Arrays.sort(ingineri);
        System.out.println("Sortare dupa nume:");
        Arrays.stream(ingineri).forEach(System.out::println);

        PlataOnline p = ingineri[0];
        p.autentificare("user", "pass");
        System.out.println("Sold: " + p.consultareSold());

        PlataOnlineSMS pj = new PersoanaJuridica("Firma", "SRL", "074", 20000, 15000);
        pj.autentificare("admin", "123");
        pj.trimiteSMS("Plata efectuata");
        pj.trimiteSMS("");

        PlataOnlineSMS pjFaraTelefon = new PersoanaJuridica("Firma2", "SRL", "", 10000,8000);
        System.out.println("SMS trimis? " + pjFaraTelefon.trimiteSMS("Test"));

        PersoanaJuridica realPJ = (PersoanaJuridica) pj;
        System.out.println("SMS-uri: " + realPJ.getSmsTrimise());

        System.out.println("\nTVA: " + ConstanteFinanciare.TVA.getValoare());

        try {
            p.autentificare(null, "pass");
        } catch (Exception e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    }

