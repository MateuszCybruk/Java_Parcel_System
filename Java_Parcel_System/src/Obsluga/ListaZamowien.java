package Obsluga;

import Paczki.Paczka;

import java.util.HashMap;

public class ListaZamowien extends Zamowienia<Paczka> {
    private static final HashMap<String, ListaZamowien> instances = new HashMap<>();


    private ListaZamowien(String name){
        this.name=name;
        this.zamowienia=new HashMap<>();
    }


    public static ListaZamowien pobierz(String klientName) {
        return instances.computeIfAbsent(klientName, ListaZamowien::new);
    }

    public void dodaj(Paczka paczka) {
        Cennik cennik = Cennik.pobierzCennik();
        double cenaPaczki = cennik.znajdzCene(paczka);
        this.zamowienia.put(paczka,cenaPaczki);
        this.count++;
    }
    public void dodaj50procent(Paczka paczka) {
        Cennik cennik = Cennik.pobierzCennik();
        double cenaPaczki = cennik.znajdzCene(paczka)/2;
        this.zamowienia.put(paczka,cenaPaczki);
        this.count++;
    }
    @Override
    public void usun(Paczka paczka){
        super.usun(paczka);
    }
    @Override
    public void setName(String name){
        super.setName(name);
    }
    @Override
    public Double getVal(Paczka paczka){
       return super.getVal(paczka);
    }
    @Override
    public String toString() {
        return super.toString();
    }

}

