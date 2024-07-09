package Paczki;

import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;

public class Maxi extends Paczka {
    public Maxi(String typ, int ilosc, RodzajDostawy rodzajDostawy) {
        super(typ, ilosc, rodzajDostawy);
        super.rozmiarPaczki = RozmiarPaczki.MAXI;
    }
}
