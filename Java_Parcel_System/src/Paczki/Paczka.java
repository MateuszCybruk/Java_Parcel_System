package Paczki;

import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;

import java.util.Objects;


public abstract class Paczka {

    protected RozmiarPaczki rozmiarPaczki;
    protected String typ;
    protected int ilosc;
    protected RodzajDostawy rodzajDostawy;


    public Paczka(String typPaczki, int ilosc, RodzajDostawy rodzajDostawy) {
        this.typ = typPaczki;
        this.ilosc = ilosc;
        this.rodzajDostawy = rodzajDostawy;
    }

    public Paczka(RozmiarPaczki rozmiarPaczki, String typPaczki, int ilosc, RodzajDostawy rodzajDostawy) {
        this.rozmiarPaczki = rozmiarPaczki;
        this.typ = typPaczki;
        this.ilosc = ilosc;
        this.rodzajDostawy = rodzajDostawy;
    }

    public int compareTypRozm(CenaPaczki o) {
        //Objects.equals is null safe
        if (Objects.equals(this.typ, o.typ) && this.rozmiarPaczki == o.rozmiarPaczki)
            return 0;
        else
            return 1;
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

    public int myCopareIlosc(Paczka o) {

        return o.ilosc - this.ilosc;
        //return Integer.compare(o.ilosc, this.ilosc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typ, rozmiarPaczki);
    }

    public RodzajDostawy getRodzajDostawy() {
        return rodzajDostawy;
    }

    public RozmiarPaczki getRozmiarPaczki() {
        return rozmiarPaczki;
    }

    public int getIlosc() {
        return ilosc;
    }

    public String getTyp() {
        return typ;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return
                "[" + rozmiarPaczki +
                        ", " + typ +
                        ", " + ilosc +
                        ", " + rodzajDostawy;
    }

}




