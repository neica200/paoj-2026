package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        List<Student> studenti = citesteStudenti();
        if (input.equals("PRINT")) {
            studenti.forEach(System.out::println);
            return;
        }

        String[] tokens = input.split(" ", 2);
        String comanda = tokens[0];
        String nume = tokens[1];

        Student student = studenti.stream()
                .filter(s -> s.getNume().equals(nume))
                .findFirst()
                .orElse(null);

        if (student == null) return;

        try {
            if (comanda.equals("SHALLOW")) {
                Student clona = student.shallowClone();
                clona.getAdresa().setOras("MODIFICAT");

                System.out.println("Original: " + student);
                System.out.println("Clona: " + clona);
            } else if (comanda.equals("DEEP")) {
                Student clona = student.deepClone();
                clona.getAdresa().setOras("MODIFICAT");

                System.out.println("Original: " + student);
                System.out.println("Clona: " + clona);
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private  static List<Student> citesteStudenti() {
        List<Student> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/com/pao/laboratory08/tests/studenti.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length < 4) continue;

                String nume = parts[0].trim();
                int varsta = Integer.parseInt(parts[1].trim());
                String oras = parts[2].trim();
                String strada = parts[3].trim();

                Adresa adresa = new Adresa(oras, strada);
                Student student = new Student(nume, varsta, adresa);

                lista.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
