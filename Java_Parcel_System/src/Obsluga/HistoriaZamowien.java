package Obsluga;

import Paczki.PaczkaDeletable;

import java.util.HashMap;
import java.util.Map;

public class HistoriaZamowien extends Zamowienia<PaczkaDeletable> {

    private static final Map<String, HistoriaZamowien> instances = new HashMap<>();


    private HistoriaZamowien() {
        this.zamowienia = new HashMap<>();
    }

    private HistoriaZamowien(String name) {
        this.name = name;
        this.zamowienia = new HashMap<>();
    }

    public static HistoriaZamowien pobierz(String klientName) {
        return instances.computeIfAbsent(klientName, HistoriaZamowien::new);
    }

    public void dodaj(PaczkaDeletable paczka, Double val) {
        this.zamowienia.put(paczka, val);
    }

    @Override
    public void usun(PaczkaDeletable paczka) {
        super.usun(paczka);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
