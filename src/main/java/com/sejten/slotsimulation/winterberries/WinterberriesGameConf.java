package com.sejten.slotsimulation.winterberries;

import com.sejten.slotsimulation.slot.GameConf;
import com.sejten.slotsimulation.slot.Payline;
import com.sejten.slotsimulation.slot.Reel;
import com.sejten.slotsimulation.slot.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by piotr.s on 2016-04-20.
 */
public class WinterberriesGameConf extends GameConf {
    final List<Reel> bonusReels = new ArrayList<>();

    public WinterberriesGameConf() {
        numberOfRows = 3;
        symbols.put("HIGH1", new Symbol("HIGH1", (x) -> x == 3 ? 15 : x == 4 ? 40 : x == 5 ? 500 : 0));
        symbols.put("HIGH2", new Symbol("HIGH2", (x) -> x == 3 ? 12 : x == 4 ? 20 : x == 5 ? 150 : 0));
        symbols.put("HIGH3", new Symbol("HIGH3", (x) -> x == 3 ? 8 : x == 4 ? 15 : x == 5 ? 75 : 0));
        symbols.put("LOW1", new Symbol("LOW1", (x) -> x == 3 ? 5 : x == 4 ? 12 : x == 5 ? 50 : 0));
        symbols.put("LOW2", new Symbol("LOW2", (x) -> x == 3 ? 3 : x == 4 ? 12 : x == 5 ? 50 : 0));
        symbols.put("LOW3", new Symbol("LOW3", (x) -> x == 3 ? 3 : x == 4 ? 10 : x == 5 ? 25 : 0));
        symbols.put("LOW4", new Symbol("LOW4", (x) -> x == 3 ? 3 : x == 4 ? 10 : x == 5 ? 25 : 0));

        Symbol sym3 = symbols.get("HIGH1");
        Symbol sym4 = symbols.get("HIGH2");
        Symbol sym5 = symbols.get("HIGH3");
        Symbol sym6 = symbols.get("LOW1");
        Symbol sym7 = symbols.get("LOW2");
        Symbol sym8 = symbols.get("LOW3");
        Symbol sym9 = symbols.get("LOW4");

        reels.add(new Reel("Reel_1", Arrays.asList(sym3, sym3, sym3, sym6, sym6, sym6, sym3, sym3, sym7, sym7, sym9, sym9, sym5, sym5, sym4, sym4, sym3, sym3, sym3, sym6, sym6, sym6, sym7, sym7, sym7, sym9, sym9, sym7, sym7, sym6, sym6, sym6, sym7, sym7, sym9, sym9, sym9, sym5, sym5, sym8, sym8, sym8, sym8, sym4, sym4, sym8, sym8, sym8, sym5, sym5, sym4, sym4, sym8, sym8, sym8, sym6, sym6, sym8, sym8, sym5, sym5, sym5, sym8, sym8)));
        reels.add(new Reel("Reel_2", Arrays.asList(sym6, sym6, sym8, sym8, sym4, sym4, sym4, sym9, sym9, sym8, sym8, sym4, sym4, sym6, sym6, sym6, sym6, sym8, sym8, sym8, sym9, sym9, sym6, sym6, sym6, sym8, sym8, sym9, sym9, sym9, sym3, sym3, sym7, sym7, sym7, sym3, sym3, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym3, sym3, sym7, sym7, sym5, sym5, sym5, sym5)));
        reels.add(new Reel("Reel_3", Arrays.asList(sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym7, sym7, sym7, sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym5, sym5, sym8, sym8, sym8, sym7, sym7, sym7, sym8, sym8, sym8, sym5, sym5, sym9, sym9, sym3, sym3, sym4, sym4, sym9, sym9, sym9, sym4, sym4, sym9, sym9, sym6, sym6, sym6, sym4, sym4, sym9, sym9, sym9, sym6, sym6, sym6, sym4, sym4, sym9, sym9, sym6, sym6, sym6)));
        reels.add(new Reel("Reel_4", Arrays.asList(sym4, sym4, sym5, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym3, sym3, sym3, sym5, sym5, sym5, sym5, sym4, sym4, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym9, sym9, sym9, sym9, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym9, sym9)));
        reels.add(new Reel("Reel_5", Arrays.asList(sym3, sym3, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym5, sym5, sym6, sym6, sym7, sym7, sym3, sym3, sym8, sym8, sym8, sym8, sym9, sym9, sym7, sym7, sym4, sym4, sym4, sym4, sym5, sym5, sym8, sym8, sym9, sym9, sym9, sym9, sym4, sym4, sym8, sym8, sym8, sym8, sym3, sym3, sym9, sym9, sym9, sym9)));

        bonusReels.add(new Reel("Bonus_Reel_1", Arrays.asList(sym3, sym3, sym3, sym3, sym6, sym6, sym6, sym7, sym7, sym7, sym9, sym9, sym9, sym3, sym3, sym3, sym3, sym6, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym9, sym9, sym9, sym3, sym3, sym3, sym3, sym6, sym6, sym6, sym4, sym4, sym4, sym7, sym7, sym7, sym7, sym6, sym6, sym6, sym3, sym3, sym3, sym3, sym7, sym7, sym7, sym9, sym9, sym9, sym9, sym4, sym4, sym4, sym8, sym8, sym8, sym8, sym4, sym4, sym5, sym5, sym5, sym5, sym9, sym9, sym9, sym9, sym4, sym4, sym4)));
        bonusReels.add(new Reel("Bonus_Reel_2", Arrays.asList(sym6, sym6, sym6, sym8, sym8, sym8, sym8, sym8, sym4, sym4, sym4, sym4, sym9, sym9, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym6, sym6, sym6, sym3, sym3, sym3, sym3, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym6, sym6, sym6, sym6, sym6, sym8, sym8, sym8, sym8, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym9, sym9, sym9, sym3, sym3, sym3, sym9, sym9, sym9, sym9, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym3, sym3, sym3, sym3, sym9, sym9, sym9, sym5, sym5, sym5, sym5)));
        bonusReels.add(new Reel("Bonus_Reel_3", Arrays.asList(sym5, sym5, sym5, sym5, sym5, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym5, sym5, sym5, sym5, sym5, sym3, sym3, sym3, sym5, sym5, sym5, sym5, sym8, sym8, sym8, sym8, sym7, sym7, sym7, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym9, sym9, sym9, sym9, sym4, sym4, sym9, sym9, sym9, sym9, sym4, sym4, sym4, sym4, sym9, sym9, sym9, sym9, sym6, sym6, sym6, sym6, sym6, sym4, sym4, sym4, sym9, sym9, sym9, sym9)));
        bonusReels.add(new Reel("Bonus_Reel_4", Arrays.asList(sym4, sym4, sym5, sym5, sym5, sym6, sym6, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym8, sym8, sym8, sym8, sym4, sym4, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym7, sym7, sym7, sym7, sym7, sym9, sym9, sym9, sym5, sym5, sym5, sym5, sym5, sym6, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym5, sym5, sym5, sym5, sym6, sym6, sym6, sym6, sym5, sym5, sym5, sym5, sym8, sym8, sym7, sym7, sym7, sym7)));
        bonusReels.add(new Reel("Bonus_Reel_5", Arrays.asList(sym8, sym8, sym8, sym8, sym4, sym4, sym4, sym4, sym4, sym5, sym5, sym5, sym5, sym8, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym9, sym3, sym3, sym3, sym8, sym8, sym8, sym8, sym8, sym6, sym6, sym6, sym7, sym7, sym7, sym7, sym9, sym9, sym9, sym9, sym6, sym6, sym6, sym8, sym8, sym8, sym8, sym4, sym4, sym4, sym5, sym5, sym5, sym8, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym8, sym8, sym8, sym9, sym9, sym9, sym9, sym8, sym8, sym8, sym8, sym6, sym6, sym6, sym7, sym7, sym9, sym9, sym9, sym9)));

        paylines.add(new Payline(0, 0, 0, 0, 0));
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

    public List<Reel> getBonusReels() {
        return bonusReels;
    }
}
