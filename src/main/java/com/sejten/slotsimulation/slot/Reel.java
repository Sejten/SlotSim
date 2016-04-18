package com.sejten.slotsimulation.slot;

import java.util.ArrayList;
import java.util.List;

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
        return reelString;
    }

    public String getName() {
        return name;
    }
}
