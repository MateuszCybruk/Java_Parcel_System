package Obsluga;
import Enums.Abonament;
import Enums.RozmiarPaczki;
import Paczki.CenaPaczki;
import Paczki.Paczka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Cennik {
    private static Cennik instance = null;
    protected HashMap<CenaPaczki, ArrayList<Double>> cennik;

    private Cennik() {
        this.cennik = new HashMap<>();
    }

    public static Cennik pobierzCennik() {
        if (instance == null)
            instance = new Cennik();
        return instance;
    }


//Metoda do manualnego dodania ceny.
    public void dodaj(
            RozmiarPaczki rozmiarPaczki,
            String typ,
            double cenaAutomat, double cenaPunkt, double cenaKurier,
            Abonament dlaAbonamentow) {
        CenaPaczki key = new CenaPaczki(rozmiarPaczki, typ, dlaAbonamentow);
        ArrayList<Double> val = new ArrayList<>();
        val.add(cenaPunkt);
        val.add(cenaAutomat);
        val.add(cenaKurier);
        this.cennik.put(key, val);
    }

    public void dodaj(
            RozmiarPaczki rozmiarPaczki,
            String typ,
            double cenaAutomat, double cenaPunkt, double cenaKurier) {
        Abonament dlaAbonamentow = Abonament.NIE;
        CenaPaczki key = new CenaPaczki(rozmiarPaczki, typ, dlaAbonamentow);
        ArrayList<Double> val = new ArrayList<>();
        val.add(cenaPunkt);
        val.add(cenaAutomat);
        val.add(cenaKurier);
        this.cennik.put(key, val);
    }

    public void dodaj(
            RozmiarPaczki rozmiarPaczki,
            String typ,
            double cenaPunkt, double cenaKurier) {
        Abonament dlaAbonamentow = Abonament.NIE;
        CenaPaczki key = new CenaPaczki(rozmiarPaczki, typ, dlaAbonamentow);
        ArrayList<Double> val = new ArrayList<>();
        val.add(cenaPunkt);
        double cenaAutomat = 0;
        val.add(cenaAutomat);
        val.add(cenaKurier);
        this.cennik.put(key, val);
    }

    public void dodaj(
            RozmiarPaczki rozmiarPaczki,
            String typ,
            double cenaKurier) {
        Abonament dlaAbonamentow = Abonament.TAK;
        CenaPaczki key = new CenaPaczki(rozmiarPaczki, typ, dlaAbonamentow);
        ArrayList<Double> val = new ArrayList<>();
        double cenaPunkt = 0;
        val.add(cenaPunkt);
        double cenaAutomat = 0;
        val.add(cenaAutomat);
        val.add(cenaKurier);
        this.cennik.put(key, val);
    }

    public double znajdzCene(Paczka paczka) {
        double cena = 0;//jesli cena to 0, oznacza to, ze danej paczki NIE MA w cenniku
        for (Map.Entry<CenaPaczki, ArrayList<Double>> entry : cennik.entrySet()) {
            CenaPaczki key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            if (key.compareTypRozm(paczka) == 0) {
                switch (paczka.getRodzajDostawy()) {
                    case PUNKT -> cena = value.get(0);
                    case AUTOMAT -> cena = value.get(1);
                    case KURIER -> cena = value.get(2);
                }
                return cena;
            }
        }
        return cena;
    }

    @Override
    public String toString() {
        return "Cennik{" +
                "cennik=" + cennik +
                '}';
    }
}