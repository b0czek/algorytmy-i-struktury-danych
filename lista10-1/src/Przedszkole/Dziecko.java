package Przedszkole;

import Sety.DisjointSet;
import Sety.ForestDisjointSet;

import java.util.function.Supplier;

public class Dziecko {
    private static Supplier<DisjointSet> setSupplier = () -> new ForestDisjointSet();

    private DisjointSet ref;
    private String name;



    public String getName() {
        return name;
    }

    public Dziecko(String name) {
        this.name = name;
        this.ref = setSupplier.get();
    }

    public void zapoznaj(Dziecko dziecko) {
        ref.union(dziecko.ref);
    }

    public boolean czyZna(Dziecko dziecko) {
        return dziecko.ref.findSet() == ref.findSet();
    }



}
