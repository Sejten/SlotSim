package com.sejten.slotsimulation.slot;

/**
 * Created by piotr.s
 */
public class Symbol {
    private String name = "";
    private final Prize prize;

    public Symbol(String n, Prize pa) {
        name = n;
        prize = pa;
    }

    public boolean equals(Symbol other) {
        return this.name.equals(other.name);
    }

    public String getName() {
        return name;
    }

    public int getPrize(int occurrences){
        return prize.evaluate(occurrences);
    }

    @Override
    public String toString() {
        return name;
    }
}
