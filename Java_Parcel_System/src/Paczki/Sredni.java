package Paczki;

import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;

public class Sredni extends Paczka{
    public Sredni(String typ, int ilosc, RodzajDostawy rodzajDostawy) {
        super(typ, ilosc, rodzajDostawy);
        super.rozmiarPaczki = RozmiarPaczki.SREDNI;
    }
}
