package com.sejten.slotsimulation.slot;

import java.util.*;

/**
 * Created by piotr.s
 */
public abstract class GameConf {
    protected final Map<String, List<Reel>> reels = new HashMap<>();
    protected final Map<String, Symbol> symbols = new HashMap<>();
    protected final Map<String, Symbol> scatterSymbols = new HashMap<>();
    protected final List<Payline> paylines = new ArrayList<>();
    protected int numberOfRows = 0;

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns(String name) {
        return reels.get(name).size();
    }

    public List<Reel> getReels(String name) {
        return reels.get(name);
    }

    public List<Payline> getPaylines() {
        return paylines;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Symbol getHighestSymbol() {
        final Comparator<Symbol> comp = (s1, s2) -> Double.compare(s1.getPrize(5).get().amount, s2.getPrize(5).get().amount);
        return symbols.values().stream().max(comp).get();
    }
}
