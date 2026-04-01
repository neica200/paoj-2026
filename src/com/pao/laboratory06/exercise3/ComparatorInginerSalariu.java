package com.pao.laboratory06.exercise3;

import java.util.Comparator;

public class ComparatorInginerSalariu implements Comparator<Inginer> {
    @Override
    public int compare(Inginer inginer1, Inginer inginer2) {
        return Double.compare(inginer2.getSalariu(), inginer1.getSalariu());
    }
}
