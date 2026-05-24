package com.pao.laboratory10.exercise3;
import com.pao.laboratory10.exercise1.TipTranzactie;

public class TranzactieExtinsa {
    private final int id;
    private final double suma;
    private final String data;
    private final TipTranzactie tip;
    private final String contSursa;

    public TranzactieExtinsa(int id, double suma, String data, TipTranzactie tip, String contSursa) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
        this.contSursa = contSursa;
    }

    public int getId() { return id; }
    public double getSuma() { return suma; }
    public String getData() { return data; }
    public TipTranzactie getTip() { return tip; }
    public String getContSursa() { return contSursa; }

    public String getLuna() {
        return data.substring(0, 7);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s: %.2f RON (Cont: %s)", id, data, tip, suma, contSursa);
    }
}
