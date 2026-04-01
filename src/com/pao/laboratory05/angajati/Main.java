package com.pao.laboratory05.angajati;

import com.pao.laboratory05.angajati.Angajat;
import com.pao.laboratory05.angajati.AngajatService;
import com.pao.laboratory05.angajati.Departament;

import java.util.Scanner;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();
        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");
            // citește opțiunea și execută acțiunea

            String optiune = scanner.nextLine();

            switch (optiune) {

                case "1" -> {
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();

                    System.out.print("Departament (nume): ");
                    String numeDept = scanner.nextLine();

                    System.out.print("Departament (locație): ");
                    String locatieDept = scanner.nextLine();

                    System.out.print("Salariu: ");
                    double salariu = Double.parseDouble(scanner.nextLine());

                    // Construire obiecte
                    Departament departament = new Departament(numeDept, locatieDept);
                    Angajat angajat = new Angajat(nume, departament, salariu);

                    service.addAngajat(angajat);
                }

                case "2" -> service.listBySalary();

                case "3" -> {
                    System.out.print("Departament: ");
                    String dept = scanner.nextLine();
                    service.findByDepartment(dept);
                }
            }
        }
    }
}
