package com.sejten.slotsimulation.slot;

import java.util.Optional;

/**
 * Created by piotr.s
 */
public class Symbol {
    private String name = "";
    // lambda for function that calculates prizeCondition based on symbol occurrences
    private PrizeCondition prizeCondition = null;
    private SymbolType type = SymbolType.NORMAL;

    public Symbol(String n, PrizeCondition pa) {
        name = n;
        prizeCondition = pa;
    }

    public Symbol(String n, SymbolType t, PrizeCondition pa) {
        name = n;
        prizeCondition = pa;
        type = t;
    }

    public boolean equals(Symbol other) {
        return this.name.equals(other.name) || getType() == SymbolType.WILD || other.getType() == SymbolType.WILD;
    }

    public String getName() {
        return name;
    }

    public Optional<Prize> getPrize(int occurrences) {
        return prizeCondition.evaluate(occurrences);
    }

    @Override
    public String toString() {
        return name;
    }

    public SymbolType getType() {
        return type;
    }

    public boolean isBiggerThan(Symbol otherSymbol) {
        if (getPrize(5).get().amount > otherSymbol.getPrize(5).get().amount)
            return true;
        return false;
    }
}
