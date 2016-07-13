package com.sejten.slotsimulation.winterberries;

import com.sejten.slotsimulation.slot.GameConf;
import com.sejten.slotsimulation.slot.Payline;
import com.sejten.slotsimulation.slot.Reel;
import com.sejten.slotsimulation.slot.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by piotr.s on 2016-07-13.
 */
public class VikingsGameConf extends GameConf {
    final List<Reel> bonusReels = new ArrayList<>();

    public VikingsGameConf() {
        numberOfRows = 3;
        Symbol h1 = new Symbol("HIGH1", (x) -> x == 3 ? 30 : x == 4 ? 100 : x == 5 ? 250 : 0);
        Symbol h2 = new Symbol("HIGH2", (x) -> x == 3 ? 30 : x == 4 ? 100 : x == 5 ? 225 : 0);
        Symbol h3 = new Symbol("HIGH3", (x) -> x == 3 ? 25 : x == 4 ? 75 : x == 5 ? 200 : 0);
        Symbol h4 = new Symbol("HIGH4", (x) -> x == 3 ? 20 : x == 4 ? 75 : x == 5 ? 175 : 0);
        Symbol l1 = new Symbol("LOW1", (x) -> x == 3 ? 6 : x == 4 ? 30 : x == 5 ? 80 : 0);
        Symbol l2 = new Symbol("LOW2", (x) -> x == 3 ? 6 : x == 4 ? 30 : x == 5 ? 70 : 0);
        Symbol l3 = new Symbol("LOW3", (x) -> x == 3 ? 5 : x == 4 ? 25 : x == 5 ? 60 : 0);
        Symbol l4 = new Symbol("LOW4", (x) -> x == 3 ? 5 : x == 4 ? 25 : x == 5 ? 50 : 0);
        Symbol b0 = new Symbol("BONUS1");
        Symbol b1 = new Symbol("BONUS2");
        Symbol b2 = new Symbol("BONUS3");

        symbols.put(h1.getName(), h1);
        symbols.put(h2.getName(), h2);
        symbols.put(h3.getName(), h3);
        symbols.put(h4.getName(), h4);
        symbols.put(l1.getName(), l1);
        symbols.put(l2.getName(), l2);
        symbols.put(l3.getName(), l3);
        symbols.put(l4.getName(), l4);

        reels.add(new Reel("Reel_1", Arrays.asList(h1, h1, h3, h3, h3, l2, l2, h2, h2, h2, l4, l4, l2, l2, l2, b0, l3, l3, l3, b1, l4, l4, l4, h2, h2, h4, h4, h4, h4, b0, l3, l3, l1, l1, l1, l1, l4, l4, b1, h1, h1, l1, l1, l1, l3, l3, l3, h4, h4, l4, l4, l4, l1, l1, l1, l4, l4, h1, h2, h2, h4, h4, h4, h4, l3, l3, l1, l1, l2, l2, l2, h3, h3, l1, l1, l3, l3, b1, l4, l4, l1, l1)));
        reels.add(new Reel("Reel_2", Arrays.asList(h1, h1, h4, h4, h3, h3, h2, h2, l3, l3, h4, h4, l1, l1, l1, h3, h3, h3, l4, l4, b1, l2, l2, l2, l4, l4, l3, l3, l3, b1, l4, l4, l4, l1, l1, l1, l1, b0, h1, h1, h3, h3, h3, l4, l4, l4, l2, l2, l2, h3, h3, l2, l2, l2, h3, h3, l3, l3, l2, l2, l1, l1, b1, l4, l4, l1, l1)));
        reels.add(new Reel("Reel_3", Arrays.asList(l2, l2, h3, h3, h3, l4, l4, l4, l4, h3, h3, l2, l2, b0, l3, l3, l3, l3, b1, l2, l2, l2, l4, l4, l4, l2, l2, l2, l1, l1, l1, l2, l2, l2, h2, h2, b1, h4, h4, h4, h4, l2, l2, l3, l3, l3, l3, l2, l2, h2, h1, b0, l3, l3, l2, l2, l1, l1)));
        reels.add(new Reel("Reel_4", Arrays.asList(h1, h1, h4, h4, h4, h4, l4, l4, l4, l2, l2, h2, h2, l3, l3, l3, l4, l4, l4, l1, l1, l1, h1, h1, h2, h2, h2, h3, h3, h3, b1, l2, l2, l2, l2, b0, h2, h2, h2, h3, h3, h3, h4, h4, l3, l3, l3, b1, l4, l4, l4, l1, l1, l2, l2, l3, l3, b1, l4, l4, l1, l1)));
        reels.add(new Reel("Reel_5", Arrays.asList(h1, h1, h2, h2, h2, h3, h3, h4, h4, h4, h4, h3, h3, h3, h4, h4, h4, h4, h1, h1, l2, l2, l2, l2, b0, l4, l4, l4, l4, b2, l1, l3, l3, l1, l1, l1, h1, h1, h2, h2, h2, l1, l1, l1, b1, l1, l1, l1, h4, h4, l4, l4, h1, h1, h3, h3, h3, h4, h4, h4, h4, l2, l2, l2, h1, h1, h2, h2, h2, h4, h4, h3, h3, h3, h4, h4, h4, h4, h1, h1, l2, l4, l4, l2, l2, l2, h3, h3, l2, l2, l2, l2, b1, l3, l3, l3, l1, l1, l1, b1, l4, l4, l4, l1, l1, l1, h1, h1, h1, h2, h2, l4, l4, l4, l4, h3, h3, l4, l4, l4, b1, b1, h2, h2, h2, l1, l1, l1, l3, l3, h3, h3, h3, h4, h4, b0, l3, l3, b1, b1, h3, h3, h3, h1, h1, h1, h4, h4, h4, h4, h1, h1, l4, l4, b0, h4, h4, h4, h4, l2, l2, l2, h1, h1, l3, l3, l3, l1, l1, l1, b1, b1, l4, l4, l4, l1, l1, l1, h2, h2, l1, l1, h2, h2)));

        bonusReels.add(new Reel("Bonus_Reel_1", Arrays.asList(
                l1, l1, l1, h4, h4, l3, l3, l2, l2, l2, h2, h2, l4, l4, l3, l3, l3, h1, h1, l4, l4, l4, l3, l3, l2, l2, l2, l2, l1, l1, h4, h4, l1, l1, l1, l1, l2, l2, h3, h3, l4, l4, l3, l3, h2, h2, l3, l3, l4, l4, h1, h1
        )));
        bonusReels.add(new Reel("Bonus_Reel_2", Arrays.asList(
                l1, l1, l1, h1, h1, h4, h4, l1, l1, l2, l2, l2, h2, h2, l4, l4, l3, l3, l3, l3, l3, h1, h1, l4, l4, l2, l2, h3, h3, l4, l4, l4, l3, l3, l3, l3, h2, h2, l1, l1, l4, l4, l2, l2, l4, l4, l1, l1, h4, h4, l2, l2, l3, l3, l1, l1, l2, l2, l4, l4
        )));
        bonusReels.add(new Reel("Bonus_Reel_3", Arrays.asList(
                l1, l1, l1, h4, h4, l4, l4, l2, l2, l2, h2, h2, l1, l1, h4, h4, l2, l2, l3, l3, l3, h1, h1, l1, l1, l4, l4, l2, l2, l4, l4, l3, l3, l3, l3, h3, h3, l4, l4, h1, h1, l1, l1, l3, l3, h2, h2, l4, l4, l4, l2, l2, l1, l1, l2, l2
        )));
        bonusReels.add(new Reel("Bonus_Reel_4", Arrays.asList(
                l4, l4, l4, h1, h1, l2, l2, h3, h3, h3, l3, l3, l3, l4, l4, l4, h3, h3, h3, l3, l3, l3, l1, l1, l1, l4, l4, l4, h2, h2, h2, l4, l4, l4, l3, l3, l3, l4, l4, h4, h4, h4, l2, l2, l2, h4, h4, l3, l3, l3, l4, l4, l4, l4, l2, l2, l2, h3, h3, h3, l4, l4, l3, l3, l4, l1, l1, l1, l2, l2, h4, h4, h4, l3, l3, l3, h4, h4, h4, l1, l1, l1, l2, l2, l3, l3, l3, l2, l2, l1, l1, h2, h2, h2, l4, l4, l3, l3, h2, h2, l2, l2, l2, l1, l1, l1
        )));
        bonusReels.add(new Reel("Bonus_Reel_5", Arrays.asList(
                l2, l2, l2, h1, h1, l4, l4, l4, l1, l1, l2, l2, l2, h4, h4, h4, l2, l2, l2, l1, l1, l2, h2, h2, h2, l3, l3, l3, l3, h1, l2, l2, h1, b2, l3, l3, l3, l2, l2, h3, h3, l2, l2, l3, l3, h1, h1, h1, l2, l1, l1, h4, h4, l3, l3, l2, l2, l3, l3, h3, h3, h3, l4, l4, l4, l3, l3, l3, b2, l2, l2, l2, l2, b2, l1, l1, l1, l4, l4, l4, h1, h1, l4, l4, l3, l3, h3, h3, h3, l1, l1, l1, l3, l3, h3, l4, l4, l2, l2, h3, h3, l1, l1, h3, l3, l3, l3, l3, h3, l3, l3, l2, l2, h3, h3, h4, h4, h4, l3, l1, l1, l2, l2, l3, l3, l3, h2, h2, h2, l2, l2, l4, l4, l2, l2, l4, l4, l2, l2, l2, h2, h2, h2, h2, l4, l1, l1, l4, b2, l2, l2, l2, h4, h4, l4, l4, l1, l1, h4
        )));

        paylines.add(new Payline(0, 0, 0, 0, 0));
        paylines.add(new Payline(1, 1, 1, 1, 1));
        paylines.add(new Payline(2, 2, 2, 2, 2));
        paylines.add(new Payline(3, 3, 3, 3, 3));
        paylines.add(new Payline(0, 1, 2, 1, 0));
        paylines.add(new Payline(1, 2, 3, 2, 1));
        paylines.add(new Payline(2, 1, 0, 1, 2));
        paylines.add(new Payline(3, 2, 1, 2, 3));
        paylines.add(new Payline(0, 1, 1, 1, 0));
        paylines.add(new Payline(1, 2, 2, 2, 1));
        paylines.add(new Payline(2, 3, 3, 3, 2));
        paylines.add(new Payline(3, 2, 2, 2, 3));
        paylines.add(new Payline(2, 1, 1, 1, 2));
        paylines.add(new Payline(1, 0, 0, 0, 1));
        paylines.add(new Payline(0, 1, 0, 1, 0));
        paylines.add(new Payline(1, 2, 1, 2, 1));
        paylines.add(new Payline(2, 3, 2, 3, 2));
        paylines.add(new Payline(3, 2, 3, 2, 3));
        paylines.add(new Payline(2, 1, 2, 1, 2));
        paylines.add(new Payline(1, 0, 1, 0, 1));
        paylines.add(new Payline(0, 0, 1, 0, 0));
        paylines.add(new Payline(1, 1, 2, 1, 1));
        paylines.add(new Payline(1, 1, 0, 1, 1));
        paylines.add(new Payline(2, 2, 1, 2, 2));
        paylines.add(new Payline(3, 3, 2, 3, 3));
    }
}
