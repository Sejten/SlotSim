package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by piotr.s
 */
public class Reel {
    private final List<Symbol> reel;
    private String name = "";

    public Reel(String n, List<Symbol> r) {
        name = n;
        reel = r;
    }

    public Reel(Reel r) {
        reel = new ArrayList<>();
        for (int i = 0; i < r.getReelLength(); i++) {
            reel.add(r.getSymbolAt(i));
        }
    }

    public Symbol getSymbolAt(int position) {
        return reel.get(position % reel.size());
    }

    public Reel getSymbolAt(int position, int windowSize) {
        List<Symbol> reelCut = new ArrayList<>();
        for (int i = position; i < position + windowSize; i++) {
            reelCut.add(getSymbolAt(i));
        }
        return new Reel(name + "_cut", reelCut);
    }

    public int getReelLength() {
        return reel.size();
    }

    public String toString() {
        String reelString = "";
        for (Symbol sym : reel) {
            reelString += sym.getName() + " ";
        }
        reelString = reelString.trim();
        return reelString;
    }

    public int countSymbols(Symbol sym) {
        int counter = 0;
        for (Symbol s : reel)
            if (sym.equals(s) || (sym.getType() != SymbolType.NORMAL && sym.getType() == s.getType()))
                counter++;
        return counter;
    }

    public int countSymbols(SymbolType stype) {
        int counter = 0;
        for (Symbol s : reel)
            if (s.getType() == stype)
                counter++;
        return counter;
    }

    public void setSymbolAt(Symbol sym, int row) {
        reel.set(row, sym);
    }

}
