package Paczki;

import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;

public class Mini extends Paczka{
    public Mini(String typ, int ilosc, RodzajDostawy rodzajDostawy) {
        super(typ, ilosc, rodzajDostawy);
        super.rozmiarPaczki = RozmiarPaczki.MINI;
    }


}
