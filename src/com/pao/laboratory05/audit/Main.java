package com.pao.laboratory05.audit;
import com.pao.laboratory05.audit.Departament;
import com.pao.laboratory05.audit.Angajat;
import java.util.Scanner;


/**
 * Exercise 4 (Bonus) — Audit Log
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 4 (Bonus) — Audit"
 *
 * Extinde soluția de la Exercise 3 cu un sistem de audit bazat pe record.
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
            System.out.println("4. Afișează audit log");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

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

                case "4" -> service.printAuditLog();

                case "0" -> {
                    System.out.println("Ieșire...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Opțiune invalidă!");
            }



        }
    }
}