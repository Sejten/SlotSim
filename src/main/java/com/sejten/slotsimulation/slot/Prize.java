package com.sejten.slotsimulation.slot;

/**
 * Created by piotr.s on 2016-07-19.
 */
public class Prize {
    public double amount;
    public PrizeType type;

    public Prize(int a, PrizeType pt) {
        amount = a;
        type = pt;
    }

    public boolean equals(Prize other) {
        return amount == other.amount && type == other.type;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = (int) (37 * amount + type.ordinal());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        return o.equals(toString());
    }

    public String toString() {
        return amount + " " + type.toString();
    }
}
