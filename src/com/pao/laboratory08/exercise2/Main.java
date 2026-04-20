package com.pao.laboratory08.exercise2;
import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;
import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int prag = Integer.parseInt(sc.nextLine().trim());

        List<Student> studenti = citesteStudenti();

        List<Student> filtrati = studenti.stream()
                .filter(s -> s.getVarsta() >= prag)
                .toList();

        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + filtrati.size() + " studenti\n");

        filtrati.forEach(System.out::println);

        System.out.println("\nScris in: rezultate.txt");

        scrieFisier(filtrati);
    }

    private static List<Student> citesteStudenti() {
        List<Student> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/com/pao/laboratory08/tests/studenti.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(",");

                String nume = p[0].trim();
                int varsta = Integer.parseInt(p[1].trim());
                String oras = p[2].trim();
                String strada = p[3].trim();

                Adresa adresa = new Adresa(oras, strada);
                lista.add(new Student(nume, varsta, adresa));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private static void scrieFisier(List<Student> studenti) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("rezultate.txt"))) {

            for (Student s : studenti) {
                bw.write(s.toString());
                bw.newLine();
            }

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

