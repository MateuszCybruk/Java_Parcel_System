package Obsluga;

import Paczki.Custom;
import Paczki.Paczka;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KoszykHistoria extends Zamowienia<Custom>{
    //zawiera obiekty Paczka powstale na podstawie koszyka.

    private static final Map<String, KoszykHistoria> instances = new HashMap<>();
    private final String name;

    private KoszykHistoria(String name) {
        this.name = name;
        this.zamowienia = new HashMap<>();
    }

    public static KoszykHistoria pobierz(String klientName) {
        return instances.computeIfAbsent(klientName, KoszykHistoria::new);
    }

    public void zapiszKoszykHistoria(Koszyk koszyk){
        for (Map.Entry<Paczka, Double> entryK : koszyk.entrySet()) {
            Paczka k =entryK.getKey();
            Custom customPaczka = new Custom(k.getRozmiarPaczki(),k.getTyp(),k.getIlosc(),k.getRodzajDostawy());
            this.zamowienia.put(customPaczka,entryK.getValue());
        }
    }

    @Override
    public void usun(Custom paczka) {
        super.usun(paczka);
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }





    public void dodaj(Custom paczka,Double val){
        this.zamowienia.put(paczka,val);
    }



    public Set<Map.Entry<Custom, Double>> entrySet() {
        return this.zamowienia.entrySet();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
