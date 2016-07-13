package com.sejten.slotsimulation.slot;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by piotr.s
 */
public class WinningPayline {
    private Payline payline;
    private final Set<Integer> winningColumnPositions = new TreeSet<>();
    private Symbol symbol;

    public WinningPayline(Payline p) {
        payline = p;
    }

    public int getWinningSymbolCount() {
        return winningColumnPositions.size();
    }

    public WinningPayline addWinningColumnPosition(Integer... pos) {
        Collections.addAll(winningColumnPositions, pos);
        return this;
    }

    public WinningPayline setPayline(Payline p) {
        payline = p;
        return this;
    }

    public Payline getPayline() {
        return payline;
    }

    public Set<Integer> getWinningColumnPositions() {
        return winningColumnPositions;
    }

    public Integer getWinningRowPosition(int columnNumber) {
        return payline.getRow(columnNumber);
    }

    @Override
    public boolean equals(Object other) {
        return this.toString().equals(other.toString());
    }

    public String toString() {
        return "Winning line " + getPayline() + " with " + getWinningSymbolCount() + " occurences(" + winningColumnPositions + ") of " + symbol + ".";
    }

    public WinningPayline setSymbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
