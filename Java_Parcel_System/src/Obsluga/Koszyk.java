package Obsluga;

import Paczki.Paczka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Koszyk extends Zamowienia<Paczka> {
    private static final Map<String, Koszyk> instances = new HashMap<>();
    private final String name;

    private Koszyk(String name) {
        this.name = name;
        this.zamowienia = new HashMap<>();
    }

    public static Koszyk pobierz(String klientName) {
        return instances.computeIfAbsent(klientName, Koszyk::new);
    }

    public void przepakuj(ListaZamowien listaZamowien) {
        ArrayList<Paczka> toDelete = new ArrayList<>();
        for (Paczka entry : listaZamowien) {
            double value = listaZamowien.getVal(entry);
            if (value > 0) {
                this.zamowienia.put(entry, value);
                toDelete.add(entry);
                this.count++;
            }
        }
        for (Paczka p : toDelete) {
            listaZamowien.usun(p);
        }
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public double sumaKoszyka() {
        double suma = 0;
        for (Map.Entry<Paczka, Double> entry : this.zamowienia.entrySet()) {
            suma += entry.getKey().getIlosc() * entry.getValue();
        }
        return suma;
    }

    @Override
    public Double getVal(Paczka paczka) {
        return super.getVal(paczka);
    }

    public void dodaj(Paczka paczka, Double val){
        this.zamowienia.put(paczka,val);
    }

    @Override
    public void usun(Paczka paczka) {
        super.usun(paczka);
    }

    public Set<Map.Entry<Paczka, Double>> entrySet() {
        return this.zamowienia.entrySet();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}






