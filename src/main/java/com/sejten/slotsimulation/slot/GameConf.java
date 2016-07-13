package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piotr.s
 */
public abstract class GameConf {
    protected final List<Reel> reels = new ArrayList<>();
    protected final Map<String, Symbol> symbols = new HashMap<>();
    protected final List<Payline> paylines = new ArrayList<>();
    protected int numberOfRows = 0;

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return reels.size();
    }

    public List<Reel> getReels() {
        return reels;
    }

    public List<Payline> getPaylines() {
        return paylines;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}
