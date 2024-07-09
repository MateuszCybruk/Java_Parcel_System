package Paczki;

import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;

import java.util.Objects;

public class PaczkaDeletable {
    protected RozmiarPaczki rozmiarPaczki;
    protected String typ;
    protected int ilosc;
    protected RodzajDostawy rodzajDostawy;

    public PaczkaDeletable(Paczka paczka) {
        this.rozmiarPaczki = paczka.getRozmiarPaczki();
        this.typ = paczka.getTyp();
        this.ilosc = paczka.getIlosc();
        this.rodzajDostawy = paczka.getRodzajDostawy();
    }

    public PaczkaDeletable(RozmiarPaczki rozmiarPaczki, String typ, int ilosc, RodzajDostawy rodzajDostawy) {
        this.rozmiarPaczki = rozmiarPaczki;
        this.typ = typ;
        this.ilosc = ilosc;
        this.rodzajDostawy = rodzajDostawy;
    }


    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public RodzajDostawy getRodzajDostawy() {
        return rodzajDostawy;
    }

    public void setRodzajDostawy(RodzajDostawy rodzajDostawy) {
        this.rodzajDostawy = rodzajDostawy;
    }

    public int myCompareToPaczka(Paczka o) {
        if (o.getRozmiarPaczki() == this.rozmiarPaczki
                &&
                o.getRodzajDostawy() == this.rodzajDostawy
                &&
                Objects.equals(o.getTyp(), this.typ))
            return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return "PaczkaDeletable{" +
                " " + rozmiarPaczki +
                ", '" + typ + '\'' +
                ", " + ilosc +
                ", " + rodzajDostawy +
                '}';
    }
}
