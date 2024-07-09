package Paczki;

import Enums.RodzajDostawy;
import Enums.RozmiarPaczki;

public class Custom extends Paczka{
    public Custom(RozmiarPaczki rozmiarPaczki, String typPaczki, int ilosc, RodzajDostawy rodzajDostawy) {
        super(rozmiarPaczki,typPaczki, ilosc, rodzajDostawy);

    }
}
