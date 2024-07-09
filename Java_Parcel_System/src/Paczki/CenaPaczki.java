package Paczki;

import Enums.Abonament;
import Enums.RozmiarPaczki;

import java.util.Objects;

public class CenaPaczki {
    //klasa-klucz do cennika
    protected RozmiarPaczki rozmiarPaczki;
    protected String typ;
    protected Abonament dlaAbonamentow;

    public CenaPaczki(RozmiarPaczki rozmiarPaczki, String typ, Abonament dlaAbonamentow) {
        this.rozmiarPaczki = rozmiarPaczki;
        this.typ = typ;
        this.dlaAbonamentow=dlaAbonamentow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rozmiarPaczki, typ);
    }

    @Override
    public String toString() {
        return   rozmiarPaczki + typ;
    }

    public int compareTypRozm(Paczka o) {
        if(Objects.equals(this.typ, o.typ) && this.rozmiarPaczki == o.rozmiarPaczki)
            return 0;
        else
            return 1;
    }

}
