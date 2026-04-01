package com.pao.laboratory05.angajati;

import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati;

    private AngajatService() {
        angajati = new Angajat[0];
    }
    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    public void addAngajat(Angajat angajat) {
        Angajat[] copie =  new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, copie, 0, angajati.length);
        copie[copie.length-1] = angajat;
        angajati = copie;
        System.out.println("Angajat adaugat: "+angajat.getNume());
    }

    public void printAll(){
        for(Angajat angajat : angajati){
            System.out.println(angajat);
        }
    }

    public void listBySalary(){
        Angajat[] copie = angajati.clone();
        Arrays.sort(copie);
        for(Angajat angajat : copie){
            System.out.println(angajat);
        }
    }

    public void findByDepartment(String numeDept){
        boolean gasit = false;

        System.out.println(" Angajati în departamentul " + numeDept);

        for (Angajat angajat : angajati){
            if(angajat.getDepartament().nume().equalsIgnoreCase(numeDept)){
                System.out.println(angajat);
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }

    }


}
