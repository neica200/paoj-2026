package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Angajat implements  PlataOnlineSMS{
    private List<String> smsTrimise = new ArrayList<>();
    private double sold;

    public PersoanaJuridica(String nume, String prenume, String telefon,double salariu, double sold) {
        super(nume, prenume, telefon,salariu);
        this.sold = sold;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Date autentificare invalide");
        }
        System.out.println("Autentificare Persoana Juridica reusita");
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
    public boolean trimiteSMS(String mesaj) {
        if (mesaj == null || mesaj.isEmpty()) return false;
        if (telefon == null || telefon.isEmpty()) return false;

        smsTrimise.add(mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }




}
