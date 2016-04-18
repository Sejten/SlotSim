package com.sejten.slotsimulation.slot;

import java.util.*;

/**
 * Created by piotr.s
 */
public class GameConf {
    private final List<Reel> reels = new ArrayList<>();
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final List<Payline> paylines = new ArrayList<>();
    private final int numberOfRows = 3;

    public GameConf() {
        symbols.put("SYM3", new Symbol("SYM3", (x) -> x == 3 ? 15 : x == 4 ? 40 : x == 5 ? 500 : 0));
        symbols.put("SYM4", new Symbol("SYM4", (x) -> x == 3 ? 12 : x == 4 ? 20 : x == 5 ? 150 : 0));
        symbols.put("SYM5", new Symbol("SYM5", (x) -> x == 3 ? 8 : x == 4 ? 15 : x == 5 ? 75 : 0));
        symbols.put("SYM6", new Symbol("SYM6", (x) -> x == 3 ? 5 : x == 4 ? 12 : x == 5 ? 50 : 0));
        symbols.put("SYM7", new Symbol("SYM7", (x) -> x == 3 ? 3 : x == 4 ? 12 : x == 5 ? 50 : 0));
        symbols.put("SYM8", new Symbol("SYM8", (x) -> x == 3 ? 3 : x == 4 ? 10 : x == 5 ? 25 : 0));
        symbols.put("SYM9", new Symbol("SYM9", (x) -> x == 3 ? 3 : x == 4 ? 10 : x == 5 ? 25 : 0));

        Symbol sym3 = symbols.get("SYM3");
        Symbol sym4 = symbols.get("SYM4");
        Symbol sym5 = symbols.get("SYM5");
        Symbol sym6 = symbols.get("SYM6");
        Symbol sym7 = symbols.get("SYM7");
        Symbol sym8 = symbols.get("SYM8");
        Symbol sym9 = symbols.get("SYM9");

        reels.add(new Reel("Reel_1", Arrays.asList(sym3, sym3, sym3, sym6, sym6, sym6, sym3, sym3, sym7, sym7, sym9, sym9, sym5, sym5, sym4, sym4, sym3, sym3, sym3, sym6, sym6, sym6, sym7, sym7, sym7, sym9, sym9, sym7, sym7, sym6, sym6, sym6, sym7, sym7, sym9, sym9, sym9, sym5, sym5, sym8, sym8, sym8, sym8, sym4, sym4, sym8, sym8, sym8, sym5, sym5, sym4, sym4, sym8, sym8, sym8, sym6, sym6, sym8, sym8, sym5, sym5, sym5, sym8, sym8)));
        reels.add(new Reel("Reel_2", Arrays.asList(sym6, sym6, sym8, sym8, sym4, sym4, sym4, sym9, sym9, sym8, sym8, sym4, sym4, sym6, sym6, sym6, sym6, sym8, sym8, sym8, sym9, sym9, sym6, sym6, sym6, sym8, sym8, sym9, sym9, sym9, sym3, sym3, sym7, sym7, sym7, sym3, sym3, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym3, sym3, sym7, sym7, sym5, sym5, sym5, sym5)));
        reels.add(new Reel("Reel_3", Arrays.asList(sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym7, sym7, sym7, sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym5, sym5, sym8, sym8, sym8, sym7, sym7, sym7, sym8, sym8, sym8, sym5, sym5, sym9, sym9, sym3, sym3, sym4, sym4, sym9, sym9, sym9, sym4, sym4, sym9, sym9, sym6, sym6, sym6, sym4, sym4, sym9, sym9, sym9, sym6, sym6, sym6, sym4, sym4, sym9, sym9, sym6, sym6, sym6)));
        reels.add(new Reel("Reel_4", Arrays.asList(sym4, sym4, sym5, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym3, sym3, sym3, sym5, sym5, sym5, sym5, sym4, sym4, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym9, sym9, sym9, sym9, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym9, sym9)));
        reels.add(new Reel("Reel_5", Arrays.asList(sym3, sym3, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym5, sym5, sym6, sym6, sym7, sym7, sym3, sym3, sym8, sym8, sym8, sym8, sym9, sym9, sym7, sym7, sym4, sym4, sym4, sym4, sym5, sym5, sym8, sym8, sym9, sym9, sym9, sym9, sym4, sym4, sym8, sym8, sym8, sym8, sym3, sym3, sym9, sym9, sym9, sym9)));

        paylines.add(new Payline(0, 0, 0,0,0));
        paylines.add(new Payline(1, 1, 1, 1, 1));
        paylines.add(new Payline(2, 2, 2, 2, 2));
        paylines.add(new Payline(0, 1, 2, 1, 0));
        paylines.add(new Payline(2, 1, 0, 1, 2));
        paylines.add(new Payline(0, 0, 1, 0, 0));
        paylines.add(new Payline(2, 2, 1, 2, 2));
        paylines.add(new Payline(0, 0, 2, 0, 0));
        paylines.add(new Payline(2, 2, 0, 2, 2));
        paylines.add(new Payline(0, 1, 1, 1, 0));
        paylines.add(new Payline(2, 1, 1, 1, 2));
        paylines.add(new Payline(0, 2, 2, 2, 0));
        paylines.add(new Payline(2, 0, 0, 0, 2));
        paylines.add(new Payline(0, 1, 0, 1, 0));
        paylines.add(new Payline(2, 1, 2, 1, 2));
        paylines.add(new Payline(0, 2, 0, 2, 0));
        paylines.add(new Payline(2, 0, 2, 0, 2));
        paylines.add(new Payline(1, 1, 0, 1, 1));
        paylines.add(new Payline(1, 1, 2, 1, 1));
        paylines.add(new Payline(1, 0, 0, 0, 1));
        paylines.add(new Payline(1, 2, 2, 2, 1));
        paylines.add(new Payline(1, 0, 1, 0, 1));
        paylines.add(new Payline(1, 2, 1, 2, 1));
        paylines.add(new Payline(0, 2, 1, 2, 0));
        paylines.add(new Payline(2, 0, 1, 0, 2));
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public List<Reel> getReels() {
        return reels;
    }

    public List<Payline> getPaylines() {
        return paylines;
    }
}
