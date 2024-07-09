package Obsluga;

import Enums.Abonament;
import Enums.Platnosc;
import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;
import Paczki.Custom;
import Paczki.Paczka;
import Paczki.PaczkaDeletable;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Klient {
    String name;
    private double saldo;
    //Znacznik abonamenta
    private static final boolean NIE = false;
    private static final boolean TAK = true;
    private boolean ABONAMENT;
    private final ListaZamowien listaZamowien;
    private final Koszyk koszyk;
    private final HistoriaZamowien historiaZamowien;
    private final KoszykHistoria koszykHistoria;

    public Klient(String name, double saldo, Abonament abonament) {
        this.name = name;
        this.saldo = saldo;
        switch (abonament) {
            case TAK -> ABONAMENT = TAK;
            case NIE -> ABONAMENT = NIE;
            default -> System.out.println("Wrong data type for Abonament field. Choose: TAK or NIE");
        }
        this.listaZamowien = ListaZamowien.pobierz(name);
        this.listaZamowien.setName(name);
        this.koszyk = Koszyk.pobierz(name);
        this.koszyk.setName(name);
        this.historiaZamowien = HistoriaZamowien.pobierz(name);
        this.historiaZamowien.setName(name);
        this.koszykHistoria = Obsluga.KoszykHistoria.pobierz(name);
        this.koszykHistoria.setName(name);


    }

    public void dodaj(Paczka paczka) {
        if (this.ABONAMENT == TAK) {
            this.listaZamowien.dodaj50procent(paczka);
        } else {
            this.listaZamowien.dodaj(paczka);
        }
    }

    public void przepakuj() {
        this.koszyk.przepakuj(this.listaZamowien);
    }

    public Koszyk pobierzKoszyk() {
        return this.koszyk;
    }

    public ListaZamowien pobierzListeZamowien() {
        return this.listaZamowien;
    }

    public HistoriaZamowien pobierzHistorieZamowien() {
        return this.historiaZamowien;
    }

    public String pobierzPortfel() {
        double res = this.saldo;
        DecimalFormat df = new DecimalFormat(".00");
        return String.valueOf(df.format(res));
    }


    public void zaplac(Platnosc platnosc) {

        try {
            Koszyk koszyk = Koszyk.pobierz(name);
            double doZaplaty = 0;
            Stream<Map.Entry<Paczka, Double>> kSorted =
                    koszyk.entrySet().stream()
                            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
            LinkedHashMap<Paczka, Double> koszykSorted = new LinkedHashMap<>();
            kSorted.forEach((n) -> {
                koszykSorted.put(n.getKey(), n.getValue());
            });
            double prowizja = 0;
            double temp = doZaplaty;
            int iloscKey = 0;
            for (Map.Entry<Paczka, Double> entry : koszykSorted.entrySet()) {
                PaczkaDeletable key = new PaczkaDeletable(entry.getKey());
                for (int i = 1; i <= entry.getKey().getIlosc(); i++) {
                    temp = doZaplaty;
                    doZaplaty += entry.getValue();
                    if (platnosc == Platnosc.KARTA) {
                        prowizja = doZaplaty * 0.01;
                    }
                    if (doZaplaty <= saldo) {
                        key.setIlosc(entry.getKey().getIlosc());
                        iloscKey++;
                    }
                    if (doZaplaty > saldo) {
                        doZaplaty = temp;
                        break;
                    }
                }
                key.setIlosc(iloscKey);
                historiaZamowien.dodaj(key, entry.getValue());
                Paczka k = entry.getKey();
                Custom customPaczka = new Custom(k.getRozmiarPaczki(), k.getTyp(), iloscKey, k.getRodzajDostawy());
                koszykHistoria.dodaj(customPaczka, entry.getValue());
                iloscKey = 0;
                if (doZaplaty == temp) break;
            }
            if (platnosc == Platnosc.KARTA) {
                doZaplaty += prowizja;
            }
            saldo -= doZaplaty;


            for (PaczkaDeletable pd : historiaZamowien) {
                int iloscH = pd.getIlosc();

                for (Map.Entry<Paczka, Double> entryK : koszykSorted.entrySet()) {

                    Paczka keyK = entryK.getKey();
                    if (pd.myCompareToPaczka(keyK) == 1) {

                        int oldVal = keyK.getIlosc();
                        keyK.setIlosc(oldVal - iloscH);

                        if (keyK.getIlosc() == 0) {
                            koszyk.usun(keyK);
                        }
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






    public void zwroc(RozmiarPaczki rozmiarPaczki, String typ, int ilosc, RodzajDostawy rodzajDostawy) {
        Custom paczkaZwrotCustom = new Custom(rozmiarPaczki, typ, ilosc, rodzajDostawy);
        double zwrotKwota=0;
        boolean czyjest = false;
        Paczka oldKey = null;
        for (Paczka paczka : koszykHistoria) {
            if (paczka.myCompareToPaczka(paczkaZwrotCustom) == 1 && paczkaZwrotCustom.myCopareIlosc(paczka) >= 0) {
                zwrotKwota = paczkaZwrotCustom.getIlosc() * Cennik.pobierzCennik().znajdzCene(paczkaZwrotCustom);
                koszyk.dodaj(paczkaZwrotCustom, Cennik.pobierzCennik().znajdzCene(paczkaZwrotCustom));
                saldo += zwrotKwota;
                oldKey = paczka;
                czyjest = true;
            }
        }
        if (czyjest) {
            //modyfikacja koszykHistoria
            int oldIlosc = oldKey.getIlosc();
            int subIlosc = paczkaZwrotCustom.getIlosc();
            int newIlosc = oldIlosc - subIlosc;
            oldKey.setIlosc(newIlosc);



        }

    }
}
