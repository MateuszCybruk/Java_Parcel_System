package Obsluga;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Zamowienia<T> implements Iterable<T> {


    protected HashMap<T, Double> zamowienia;
    protected String name;
    protected int count;

    public Zamowienia() {
        this.zamowienia = new HashMap<>();
        this.count = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Iterator<Map.Entry<T, Double>> iter = zamowienia.entrySet().iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                return iter.next().getKey();
            }
        };
    }



    public void usun(T paczka) {
        try {
            if (!this.zamowienia.isEmpty()) {
                this.zamowienia.remove(paczka);
                this.count--;
            }
        } catch (Exception e) {
            throw new RuntimeException("ListaZamowien does not contain " + paczka);
        }
    }



    public void wyczysc() {
        this.zamowienia.clear();
    }

    public Double getVal(T paczka) {
        Double val = this.zamowienia.get(paczka);
        return val != null ? val : 0;
    }





    public void setName(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        if (this.zamowienia.isEmpty()) {
            return name+" --pusto";
        } else {
            StringBuilder res = new StringBuilder();
            res.append("\n");
            for (Map.Entry<T, Double> entry : this.zamowienia.entrySet()) {
                res.append(entry.toString()).append(']' + "\n");
            }
            return name + res;
        }
    }


}
