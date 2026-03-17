package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.model.*;
import com.pao.laboratory03.exercise.exception.*;
import java.util.*;

public class StudentService {
    private List<Student> students = new ArrayList<>();
    private static StudentService instance;
    private StudentService() {}
    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String name, int age) {
        // Verificăm dacă există deja un student cu acest nume
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul '" + name + "' există deja!");
            }
        }
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Studentul '" + name + "' nu a fost găsit."));
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        Student s = findByName(studentName);
        s.addGrade(subject, grade);
    }

    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Nu există studenți în sistem.");
            return;
        }
        students.forEach(System.out::println);
    }

    public void printTopStudents() {
        // Copiem lista pentru a nu modifica ordinea originală și o sortăm descrescător după medie
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));

        System.out.println("--- Top Studenți ---");
        sorted.forEach(s -> System.out.printf("%s - Medie: %.2f%n", s.getName(), s.getAverage()));
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> averages = new HashMap<>();

        for (Subject sub : Subject.values()) {
            double sum = 0;
            int count = 0;
            for (Student s : students) {
                if (s.getGrades().containsKey(sub)) {
                    sum += s.getGrades().get(sub);
                    count++;
                }
            }
            if (count > 0) {
                averages.put(sub, sum / count);
            }
        }
        return averages;
    }
}
