package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer>{
    private double sold;

    public Inginer(String nume, String prenume, String telefon, double salariu, double sold) {
        super(nume, prenume, telefon, salariu);
        this.sold = sold;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Date autentificare invalide");
        }
        System.out.println("Autentificare reusita pentru " + user);
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) throw new IllegalArgumentException("Suma invalida");

        if (sold >= suma) {
            sold -= suma;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Inginer inginer) {
        return this.nume.compareTo(inginer.nume);
    }

    public double getSalariu() {
        return salariu;
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " salariu=" + salariu;
    }





}
